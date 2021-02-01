package com.codecool.motivators.service;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.dto.QuestionDto;
import com.codecool.motivators.model.CardList;
import com.codecool.motivators.model.Question;
import com.codecool.motivators.repository.QuestionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    private final DtoConverterService converter;
    private final QuestionRepository repository;
    private final CardListService cardListService;
    private final QuestionGroupService questionGroupService;

    public QuestionService(
            @Lazy DtoConverterService converter,
            QuestionRepository repository,
            @Lazy CardListService cardListService,
            @Lazy QuestionGroupService questionGroupService) {
        this.converter = converter;
        this.repository = repository;
        this.cardListService = cardListService;
        this.questionGroupService = questionGroupService;
    }

    public QuestionDto getQuestionDtoById(Long id) {
        return converter.convertQuestion(getQuestionById(id));
    }

    public Question getQuestionById(Long id) {
        return repository.getOne(id);
    }

    public Question saveQuestion(Question question) {
        return repository.save(question);
    }

    public List<CardDto> setAnswer(Long questionId, List<CardDto> cards) {
        Question question = getQuestionById(questionId);
        if (!question.isClosed()) {
            CardList cardList = cardListService.createCardList(cards);
            question.setAnswer(cardList);
        }
        return converter.convertCardList(saveQuestion(question).getAnswer());
    }

    public String editNote(Long id, String note) {
        Question question = getQuestionById(id);
        if (!question.isClosed()) {
            question.setNote(note);
        }
        return saveQuestion(question).getNote();
    }

    public QuestionDto closeQuestion(Long id) {
        Question question = getQuestionById(id);
        question.setClosed(true);
        return converter.convertQuestion(saveQuestion(question));
    }

    public void deleteQuestion(Long id) {
        repository.deleteById(id);
    }

    public QuestionDto createQuestion (QuestionDto questionDto) {
        Question question = Question.builder()
                .group(questionGroupService.getQuestionGroupById(questionDto.getGroupId()))
                .value(questionDto.getValue())
                .date(new Date())
                .build();
        return converter.convertQuestion(saveQuestion(question));
    }
}
