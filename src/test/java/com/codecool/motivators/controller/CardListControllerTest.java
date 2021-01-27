package com.codecool.motivators.controller;

import com.codecool.motivators.service.CardListService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class CardListControllerTest {

    @Mock
    CardListService service;

    @InjectMocks
    CardListController controller;

    @Test
    void testGetCardList () {
        controller.getCardList((long) 1);
        verify(service).getCardList((long) 1);
    }

}
