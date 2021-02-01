package com.codecool.motivators.service;

import com.codecool.motivators.dto.NotificationDto;
import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.model.Notification;
import com.codecool.motivators.model.QuestionGroup;
import com.codecool.motivators.model.User;
import com.codecool.motivators.repository.NotificationRepository;
import org.springframework.context.annotation.Lazy;
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

    public NotificationDto getNotificationDtoById (Long id) {
        return converter.convertNotification(getNotificationById(id));
    }

    public void deleteNotificationById (Long id) {
        repository.deleteById(id);
    }

    private Notification saveNotification(Notification notification) {
        return repository.save(notification);
    }

    public void newInvite(Long senderId, Long receiverId, QuestionGroupDto questionGroupDto) {
        Notification notification = Notification.builder()
                .sender(userService.getUserById(senderId))
                .owner(userService.getUserById(receiverId))
                .questionGroup(questionGroupService.getQuestionGroupById(questionGroupDto.getId()))
                .build();
        saveNotification(notification);
    }

    public List<NotificationDto> getReceivedNotifications(Long userId) {
        return userService
                .getUserById(userId)
                .getReceivedNotification()
                .stream()
                .map(converter::convertNotification)
                .collect(Collectors.toList());
    }

    public List<NotificationDto> getSentNotifications(Long userId) {
        return userService
                .getUserById(userId)
                .getSentNotification()
                .stream()
                .map(converter::convertNotification)
                .collect(Collectors.toList());
    }

    public QuestionGroupDto acceptInvite(NotificationDto notificationDto) {
        Notification notification = getNotificationById(notificationDto.getId());
        QuestionGroup questionGroup = notification.getQuestionGroup();
        questionGroup.addInvited(notification.getOwner());
        deleteNotificationById(notification.getId());
        return converter.convertQuestionGroup(questionGroup);
    }

    public void declineInvitation(NotificationDto notificationDto) {
        deleteNotificationById(notificationDto.getId());
    }
}
