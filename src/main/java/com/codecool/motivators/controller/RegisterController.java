package com.codecool.motivators.controller;

import com.codecool.motivators.dto.UserDto;
import com.codecool.motivators.model.User;
import com.codecool.motivators.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register/")
public class RegisterController {
    private final UserService service;

    public RegisterController(@Lazy UserService service) {
        this.service = service;
    }


    @PostMapping("")
    public UserDto registerUser (@RequestBody User user) {
        return service.registerUser(user);
    }
}
