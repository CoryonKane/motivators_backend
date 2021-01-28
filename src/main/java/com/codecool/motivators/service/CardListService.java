package com.codecool.motivators.service;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.model.Card;
import com.codecool.motivators.model.CardList;
import com.codecool.motivators.repository.CardListRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardListService {
    private final DtoConverterService converter;
    private final CardListRepository repository;
    private final CardService cardService;

    public CardListService(
            @Lazy DtoConverterService converter,
            CardListRepository repository,
            @Lazy CardService cardService) {
        this.converter = converter;
        this.repository = repository;
        this.cardService = cardService;
    }

    public List<CardDto> getCardList(Long id) {
        CardList list = repository.getOne(id);
        return converter.convertCardList(list);
    }

    public CardList createCardList(List<CardDto> cards) {
        CardList cardList = CardList.builder()
                .cards(cards.stream().map(cardDto -> cardService.getOneById(cardDto.getId())).collect(Collectors.toList()))
                .build();
        cardList.setCreatedOn(new Date());
        return repository.save(cardList);
    }
}
