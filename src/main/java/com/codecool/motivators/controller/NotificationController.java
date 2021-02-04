package com.codecool.motivators.controller;

import com.codecool.motivators.dto.NotificationDto;
import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.service.NotificationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notification/")
public class NotificationController {
    private final NotificationService service;

    public NotificationController(@Lazy NotificationService service) {
        this.service = service;
    }

    @GetMapping("received/")
    public List<NotificationDto> getReceivedNotifications () {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getReceivedNotifications(sessionUserEmail);
    }

    @GetMapping("sent/")
    public List<NotificationDto> getSentNotifications () {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getSentNotifications(sessionUserEmail);
    }

    @PostMapping("new/{receiverId}")
    public void newInvite (@PathVariable("receiverId") Long receiverId, @RequestBody QuestionGroupDto questionGroupDto) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        service.newInvite(sessionUserEmail, receiverId, questionGroupDto);
    }

    @PutMapping("accept")
    public QuestionGroupDto acceptInvite (@RequestBody NotificationDto notificationDto) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.acceptInvite (notificationDto, sessionUserEmail);
    }

    @DeleteMapping("decline")
    public void declineInvite (@RequestBody NotificationDto notificationDto) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        service.declineInvitation(notificationDto, sessionUserEmail);
    }
}
