package com.codecool.motivators.controller;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.dto.QuestionDto;
import com.codecool.motivators.model.Question;
import com.codecool.motivators.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionControllerTest {
    private QuestionService service;
    private QuestionController controller;

    @BeforeEach
    void setUp() {
        service = mock(QuestionService.class);
        controller = new QuestionController(service);
    }

    @Test
    void getQuestion() {
//        QuestionDto q = new QuestionDto();
//
//        when(service.getQuestionDtoById(1L)).thenReturn(q);
//
//        assertEquals(q, controller.getQuestion(1L));
    }

    @Test
    void setAnswer() {
//        List<CardDto> l = new ArrayList<>();
//
//        when(service.setAnswer(1L, l)).thenReturn(l);
//
//        assertEquals(l, controller.setAnswer(1L, l));
    }

    @Test
    void editNote() {
//        String note = "";
//
//        when(service.editNote(1L, note)).thenReturn(note);
//
//        assertEquals(note, controller.editNote(1L, note));
    }

    @Test
    void closeQuestion() {
//        QuestionDto q = new QuestionDto();
//
//        when(service.closeQuestion(1L)).thenReturn(q);
//
//        assertEquals(q, controller.closeQuestion(1L));
    }

    @Test
    void deleteQuestion() {
//        controller.deleteQuestion(1L);
//        verify(service).deleteQuestion(1L);
    }
}