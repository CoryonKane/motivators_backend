package com.codecool.motivators.service;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.model.CardList;
import com.codecool.motivators.repository.CardListRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CardListService {
    private final DtoConverterService converter;
    private final CardListRepository repository;

    public CardListService(@Lazy DtoConverterService converter, CardListRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public List<CardDto> getCardList(Long id) {
        CardList list = repository.getOne(id);
        return converter.convertCardList(list);
    }

    public CardList createCardList(CardList cardList) {
        cardList.setCreatedOn(new Date());
        return repository.save(cardList);
    }
}
