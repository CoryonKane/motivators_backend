package com.codecool.motivators.service;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.dto.QuestionDto;
import com.codecool.motivators.model.CardList;
import com.codecool.motivators.model.Question;
import com.codecool.motivators.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

class QuestionServiceTest {
    private QuestionRepository repository;
    private QuestionService service;
    private DtoConverterService converter;
    private CardListService cardListService;
    private QuestionGroupService questionGroupService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        this.repository = mock(QuestionRepository.class);
        this.converter = mock(DtoConverterService.class);
        this.cardListService = mock(CardListService.class);
        this.questionGroupService = mock(QuestionGroupService.class);
        this.userService = mock(UserService.class);
        this.service = new QuestionService(converter, repository, cardListService, questionGroupService, userService);
    }

    @Test
    void getQuestionById() {
//        Question q = new Question();
//
//        when(converter.convertQuestion(q)).thenReturn(new QuestionDto());
//        when(repository.getOne(1L)).thenReturn(q);
//
//        assertEquals(new QuestionDto(), service.getQuestionDtoById(1L));
    }

    @Test
    void setAnswer() {
//        List<CardDto> l = new ArrayList<>();
//        Question q = new Question();
//        CardList cardList = new CardList();
//
//        when(service.getQuestionById(1L)).thenReturn(q);
//        when(cardListService.createCardList(l)).thenReturn(cardList);
//        when(service.saveQuestion(q)).thenReturn(q);
//
//        assertEquals(l, service.setAnswer(1L, l));
    }

    @Test
    void editNote() {
//        String note = "1";
//        Question q = new Question();
//
//        q.setClosed(false);
//        when(service.getQuestionById(1L)).thenReturn(q);
//        when(service.saveQuestion(q)).thenReturn(q);
//
//        assertEquals(note, service.editNote(1L, note));
    }

    @Test
    void closeQuestion() {
//        Question q = new Question();
//
//        when(service.getQuestionById(1L)).thenReturn(q);
//        when(converter.convertQuestion(q)).thenReturn(QuestionDto.builder().closed(q.isClosed()).build());
//        when(service.saveQuestion(q)).thenReturn(q);
//
//        assertFalse(service.closeQuestion(1L).isClosed());
    }

    @Test
    void testNotEditableQuestion () {
//        Question q = Question.builder()
//                .note("1")
//                .closed(true)
//                .build();
//        System.out.println(q);
//        when(service.getQuestionById(1L)).thenReturn(q);
//        when(service.saveQuestion(q)).thenReturn(q);
//
//        assertEquals("1", service.editNote(1L, "2"));
    }

    @Test
    void deleteQuestion() {
//        Question q1 = new Question();
//
//        q1.setId(1L);
//        when(repository.getOne(1L)).thenReturn(q1);
//
//        service.deleteQuestion(1L);
//
//        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void createQuestion() {
    }
}