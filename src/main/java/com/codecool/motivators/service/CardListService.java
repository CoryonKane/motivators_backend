package com.codecool.motivators.service;

import com.codecool.motivators.repository.CardListRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CardListService {
    private final DtoConverterService converter;
    private final CardListRepository repository;

    public CardListService(@Lazy DtoConverterService converter, CardListRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }
}
