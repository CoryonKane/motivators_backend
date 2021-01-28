package com.codecool.motivators.service;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.model.Card;
import com.codecool.motivators.repository.CardRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final DtoConverterService converter;
    private final CardRepository repository;

    public CardService(@Lazy DtoConverterService converter, CardRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public Card getOneById(Long id) {
        return repository.getOne(id);
    }
}
