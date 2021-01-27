package com.codecool.motivators.service;

import com.codecool.motivators.model.Card;
import com.codecool.motivators.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CardServiceTest {
    private DtoConverterService converter;
    private CardRepository repository;
    private CardService service;

    @BeforeEach
    void setUp() {
        converter = mock(DtoConverterService.class);
        repository = mock(CardRepository.class);
        service = new CardService(converter, repository);
    }

    @Test
    void getOneById() {
        Card c = new Card();

        when(repository.getOne(1L)).thenReturn(c);

        assertEquals(c, service.getOneById(1L));
    }
}