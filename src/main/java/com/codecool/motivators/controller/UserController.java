package com.codecool.motivators.controller;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.dto.UserDto;
import com.codecool.motivators.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/")
@CrossOrigin(origins = {"https://development-codecool-moving-motivators.netlify.app/", "https://codecool-moving-motivators.netlify.app/"})
public class UserController {
    private final UserService service;

    public UserController(@Lazy UserService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public UserDto getUser (@PathVariable("id") Long id) {
        return service.getUserDtoById(id);
    }

    @PutMapping("save-default")
    public List<CardDto> saveDefault (@RequestBody List<CardDto> list) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.addDefault(sessionUserEmail, list);
    }

    @GetMapping("search")
    public List<UserDto> searchUser (@RequestParam String name) {
        return service.searchUser(name);
    }

    @PutMapping("")
    public UserDto editUser (@RequestBody UserDto userDto) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.editUser(userDto, sessionUserEmail);
    }

    @GetMapping("invited")
    public List<QuestionGroupDto> getInvited () {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getInvited(sessionUserEmail);
    }

    @DeleteMapping("")
    public void deleteUser () {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        service.deleteUser(sessionUserEmail);
    }
}
