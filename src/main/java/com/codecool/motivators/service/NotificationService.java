package com.codecool.motivators.service;

import com.codecool.motivators.dto.NotificationDto;
import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.model.Notification;
import com.codecool.motivators.repository.NotificationRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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

    public NotificationDto newNotification (NotificationDto notificationDto) {
        Notification notification = Notification.builder()
                .owner(userService.getUserById(notificationDto.getOwnerId()))
                .sender(userService.getUserById(notificationDto.getSenderId()))
                .questionGroup(questionGroupService.getQuestionGroupById(notificationDto.getGroupId()))
                .build();
        return converter.convertNotification(repository.save(notification));
    }

    public void deleteNotificationById () {}
}
