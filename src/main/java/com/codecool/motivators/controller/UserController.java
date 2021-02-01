package com.codecool.motivators.controller;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.dto.NotificationDto;
import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.dto.UserDto;
import com.codecool.motivators.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/")
public class UserController {
    private final UserService service;

    public UserController(@Lazy UserService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public UserDto getUser (@PathVariable("id") Long id) {
        return service.getUserDtoById(id);
    }

    @PutMapping("{id}/save-default")
    public List<CardDto> saveDefault (@PathVariable("id") Long id, @RequestBody List<CardDto> list) {
        return service.addDefault(id, list);
    }

    @GetMapping("/search")
    public List<UserDto> searchUser (@RequestParam String name) {
        return service.searchUser(name);
    }

    @PutMapping("")
    public UserDto editUser (@RequestBody UserDto userDto) {
        return service.editUser(userDto);
    }
}
