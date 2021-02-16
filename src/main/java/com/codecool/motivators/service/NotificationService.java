package com.codecool.motivators.service;

import com.codecool.motivators.dto.NotificationDto;
import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.model.Notification;
import com.codecool.motivators.model.QuestionGroup;
import com.codecool.motivators.model.User;
import com.codecool.motivators.repository.NotificationRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationRepository repository;
    private final DtoConverterService converter;
    private final QuestionGroupService questionGroupService;
    private final UserService userService;

    public NotificationService(
            @Lazy DtoConverterService converter,
            NotificationRepository repository,
            @Lazy QuestionGroupService questionGroupService,
            @Lazy UserService userService) {
        this.repository = repository;
        this.converter = converter;
        this.questionGroupService = questionGroupService;
        this.userService = userService;
    }

    public Notification getNotificationById (Long id) {
        return repository.getOne(id);
    }

    public void deleteNotification(Notification notification) {
        notification.getOwner().removeReceivedNotification(notification);
        notification.getSender().removeSentNotification(notification);
        userService.saveUser(notification.getOwner());
        userService.saveUser(notification.getSender());
        repository.delete(notification);
    }

    private Notification saveNotification(Notification notification) {
        return repository.save(notification);
    }

    public void newInvite(String email, Long receiverId, QuestionGroupDto questionGroupDto) {
        User sessionUser = userService.getUserByEmail(email);
        User receiver = userService.getUserById(receiverId);
        QuestionGroup questionGroup = questionGroupService.getQuestionGroupById(questionGroupDto.getId());
        if (!questionGroup.hasInvited(receiver)) {
            Notification notification = Notification.builder()
                    .sender(sessionUser)
                    .owner(receiver)
                    .questionGroup(questionGroup)
                    .build();
            userService.getUserByEmail(email).addSentNotification(notification);
            userService.getUserById(receiverId).addReceivedNotification(notification);
            saveNotification(notification);
        }
    }

    public List<NotificationDto> getReceivedNotifications(String userEmail) {
        return userService
                .getUserByEmail(userEmail)
                .getReceivedNotification()
                .stream()
                .map(converter::convertNotification)
                .collect(Collectors.toList());
    }

    public List<NotificationDto> getSentNotifications(String userEmail) {
        return userService
                .getUserByEmail(userEmail)
                .getSentNotification()
                .stream()
                .map(converter::convertNotification)
                .collect(Collectors.toList());
    }

    public QuestionGroupDto acceptInvite(NotificationDto notificationDto, String email) {
        User receiver = userService.getUserByEmail(email);
        Notification notification = getNotificationById(notificationDto.getId());
        QuestionGroup questionGroup = notification.getQuestionGroup();
        if (receiver.equals(notification.getOwner())) {
            questionGroup.addInvited(receiver);
            receiver.addInvitedTo(questionGroup);
            questionGroupService.saveGroup(questionGroup);
            userService.saveUser(receiver);
            deleteNotification(notification);
            return converter.convertQuestionGroup(questionGroup);
        } else throw new BadCredentialsException("Invalid user.");
    }

    public void declineInvitation(NotificationDto notificationDto, String email) {
        Notification notification = getNotificationById(notificationDto.getId());
        User sessionUser = userService.getUserByEmail(email);
        User owner = notification.getOwner();
        User sender = notification.getSender();
        if (sessionUser.equals(owner) || sessionUser.equals(sender)) {
            deleteNotification(notification);
        } else throw new BadCredentialsException("Invalid user.");
    }
}
