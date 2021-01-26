package com.codecool.motivators.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

public class CardTests {
    @Test
    void testCardTypeGetters () {
        CardType card = CardType.ACCEPTANCE;
        String name = card.getName();
        assertEquals("Acceptance", name);
    }

    @Test
    void testCardListAdd () {
        Card card = Card.builder()
                .owner(new User())
                .position(1)
                .question(new Question())
                .type(CardType.ACCEPTANCE)
                .build();
        CardList list = new CardList();
        list.addCard(card);
        list.addCard(card);
        assertEquals(1, list.getCards().size());
    }

    @Test
    void testCardListRemove () {
        Card card = Card.builder()
                .owner(new User())
                .position(1)
                .question(new Question())
                .type(CardType.ACCEPTANCE)
                .build();
        CardList list = new CardList();
        list.addCard(card);
        list.removeCard(card);
        assertEquals(0, list.getCards().size());
    }
}
