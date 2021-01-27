package com.codecool.motivators.service;

import com.codecool.motivators.model.CardList;
import com.codecool.motivators.repository.CardListRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardListServiceTest {
    private CardListRepository repository;
    private CardListService service;
    private DtoConverterService converter;

    @BeforeEach
    public void setUp () {
        converter = mock(DtoConverterService.class);
        repository = mock(CardListRepository.class);
        service = new CardListService(converter, repository);
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
        CardList list = new CardList();
        assertNull(service.createCardList(list));
    }
}
