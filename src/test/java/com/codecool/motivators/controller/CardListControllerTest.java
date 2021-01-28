package com.codecool.motivators.controller;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.service.CardListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardListControllerTest {
    private CardListService service;
    private CardListController controller;

    @BeforeEach
    public void setUp () {
        service = mock(CardListService.class);
        controller = new CardListController(service);
    }

    @Test
    void testGetCardList () {
        List<CardDto> list = new ArrayList<>();
        when(service.getCardList(1L)).thenReturn(list);
        assertEquals(list, controller.getCardList(1L));
    }

}
