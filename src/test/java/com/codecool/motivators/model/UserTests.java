package com.codecool.motivators.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class UserTests {
    @Test
    void testUserAddGroup () {
        User u = new User();
        QuestionGroup q = new QuestionGroup();
        u.addGroup(q);
        assertFalse(u.getGroups().isEmpty());
    }

    @Test
    void testUserRemoveGroup () {
        User u = new User();
        QuestionGroup q = new QuestionGroup();
        u.addGroup(q);
        u.removeGroup(q);
        assertTrue(u.getGroups().isEmpty());
    }

    @Test
    void testUserAddDefault () {
        User u = new User();
        CardList l = new CardList();
        u.addDefault(l);
        assertFalse(u.getDefaultLists().isEmpty());
    }

    @Test
    void testUserRemoveDefault () {
        User u = new User();
        CardList l = new CardList();
        u.addDefault(l);
        u.removeDefault(l);
        assertTrue(u.getDefaultLists().isEmpty());
    }

    @Test
    void testUserGetNewestDefault () {
        User u = new User();
        CardList l = new CardList();
        CardList l2 = new CardList();
        u.addDefault(l);
        u.addDefault(l2);
        assertEquals(l2, u.getNewestDefault());
    }
}
