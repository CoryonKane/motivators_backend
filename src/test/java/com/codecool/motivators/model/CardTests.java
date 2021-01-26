package com.codecool.motivators.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class CardTests {
    @Test
    void testCardTypeGetters () {
        CardType card = CardType.ACCEPTANCE;
        String name = card.getName();
        assertEquals("Acceptance", name);
    }
}
