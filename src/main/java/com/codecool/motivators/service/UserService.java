package com.codecool.motivators.service;

import com.codecool.motivators.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final DtoConverterService converter;
    private final UserRepository repository;

    public UserService(@Lazy DtoConverterService converter, UserRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }
}
