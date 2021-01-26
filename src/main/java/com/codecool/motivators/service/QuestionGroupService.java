package com.codecool.motivators.service;

import com.codecool.motivators.repository.QuestionGroupRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class QuestionGroupService {
    private final DtoConverterService converter;
    private final QuestionGroupRepository repository;

    public QuestionGroupService(@Lazy DtoConverterService converter, QuestionGroupRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }
}
