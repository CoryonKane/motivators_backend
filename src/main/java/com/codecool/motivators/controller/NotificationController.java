package com.codecool.motivators.controller;

import com.codecool.motivators.dto.NotificationDto;
import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.service.NotificationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notification/")
public class NotificationController {
    private final NotificationService service;

    public NotificationController(@Lazy NotificationService service) {
        this.service = service;
    }

    @GetMapping("received/{userId}")
    public List<NotificationDto> getReceivedNotifications (@PathVariable("userId") Long userId) {
        return service.getReceivedNotifications(userId);
    }

    @GetMapping("sent/{userId}")
    public List<NotificationDto> getSentNotifications (@PathVariable("userId") Long userId) {
        return service.getSentNotifications(userId);
    }

    @PostMapping("new/{senderId}/{receiverId}")
    public void newInvite (@PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId, @RequestBody QuestionGroupDto questionGroupDto) {
        service.newInvite(senderId, receiverId, questionGroupDto);
    }

    @PutMapping("accept")
    public QuestionGroupDto acceptInvite (@RequestBody NotificationDto notificationDto) {
        return service.acceptInvite (notificationDto);
    }

    @DeleteMapping("decline")
    public void declineInvite (@RequestBody NotificationDto notificationDto) {
        service.declineInvitation(notificationDto);
    }
}
