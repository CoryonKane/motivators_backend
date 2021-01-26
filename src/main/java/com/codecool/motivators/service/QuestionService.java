package com.codecool.motivators.service;

import com.codecool.motivators.repository.QuestionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final DtoConverterService converter;
    private final QuestionRepository repository;

    public QuestionService(@Lazy DtoConverterService converter, QuestionRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }
}
