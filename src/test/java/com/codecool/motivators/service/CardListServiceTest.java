package com.codecool.motivators.service;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.model.CardList;
import com.codecool.motivators.repository.CardListRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Matchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CardListServiceTest {
    private CardListRepository repository;
    private CardListService service;
    private CardService cardService;
    private DtoConverterService converter;

    @BeforeEach
    public void setUp () {
        converter = mock(DtoConverterService.class);
        repository = mock(CardListRepository.class);
        cardService = mock(CardService.class);
        service = new CardListService(converter, repository, cardService);
    }

    @Test
    void testGetCardList () {
        CardList l = new CardList();
        when(repository.getOne(1L)).thenReturn(l);
        when(converter.convertCardList(l)).thenReturn(new ArrayList<>());

        assertEquals(new ArrayList<>(), service.getCardList(1L));
    }

    @Test
    void createCardList() {
        List<CardDto> list = new ArrayList<>();
        CardList cardList = new CardList();

        when(CardList.builder().build()).thenReturn(cardList);
        when(repository.save(ArgumentMatchers.any(CardList.class))).thenReturn(cardList);

        assertEquals(cardList, service.createCardList(list));
    }
}
