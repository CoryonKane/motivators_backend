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
    private CardService cardService;

    @BeforeEach
    void setUp() {
        this.repository = mock(QuestionRepository.class);
        this.converter = mock(DtoConverterService.class);
        this.cardListService = mock(CardListService.class);
        this.cardService = mock(CardService.class);
        this.service = new QuestionService(converter, repository, cardListService, cardService);
    }

    @Test
    void getQuestionById() {
        Question q = new Question();

        when(converter.convertQuestion(q)).thenReturn(new QuestionDto());
        when(repository.getOne(1L)).thenReturn(q);

        assertEquals(new QuestionDto(), service.getQuestionDtoById(1L));
    }

    @Test
    void setAnswer() {
        List<CardDto> l = new ArrayList<>();
        Question q = new Question();

        when(repository.getOne(1L)).thenReturn(q);
        when(cardService.getOneById(1L)).thenReturn(null);
        when(cardListService.createCardList(new CardList())).thenReturn(null);

        assertEquals(l, service.setAnswer(1L, l));
    }

    @Test
    void editNote() {
        String note = "";
        Question q = new Question();

        when(repository.getOne(1L)).thenReturn(q);

        assertEquals(note, service.editNote(1L, note));
    }

    @Test
    void closeQuestion() {
        Question q = new Question();

        when(repository.getOne(1L)).thenReturn(q);
        when(converter.convertQuestion(q)).thenReturn(QuestionDto.builder().closed(q.isClosed()).build());

        assertFalse(service.closeQuestion(1L).isClosed());
    }

    @Test
    void testNotEditableQuestion () {
        Question q = new Question();
        String note1 = "1";

        q.setClosed(true);
        q.setNote("");
        when(repository.getOne(1L)).thenReturn(q);

        assertEquals("", service.editNote(1L, note1));
    }

    @Test
    void deleteQuestion() {
        Question q1 = new Question();

        q1.setId(1L);
        when(repository.getOne(1L)).thenReturn(q1);

        service.deleteQuestion(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}