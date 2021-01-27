package com.codecool.motivators.service;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.dto.QuestionDto;
import com.codecool.motivators.model.CardList;
import com.codecool.motivators.model.Question;
import com.codecool.motivators.repository.QuestionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final DtoConverterService converter;
    private final QuestionRepository repository;
    private final CardListService cardListService;
    private final CardService cardService;

    public QuestionService(
            @Lazy DtoConverterService converter,
            QuestionRepository repository,
            @Lazy CardListService cardListService,
            @Lazy CardService cardService) {
        this.converter = converter;
        this.repository = repository;
        this.cardListService = cardListService;
        this.cardService = cardService;
    }

    public QuestionDto getQuestionDtoById(Long id) {
        return converter.convertQuestion(repository.getOne(id));
    }

    public List<CardDto> setAnswer(Long questionId, List<CardDto> cards) {
        Question question = repository.getOne(questionId);
        if (!question.isClosed()) {
            CardList cardList = CardList.builder()
                    .cards(cards.stream().map(cardDto -> cardService.getOneById(cardDto.getId())).collect(Collectors.toList()))
                    .build();
            question.setAnswer(cardList);
            cardListService.createCardList(cardList);
        }
        repository.save(question);
        return converter.convertCardList(repository.getOne(questionId).getAnswer());
    }

    public String editNote(Long id, String note) {
        Question question = repository.getOne(id);
        if (!question.isClosed()) {
            question.setNote(note);
            repository.save(question);
        }
        return repository.getOne(id).getNote();
    }

    public QuestionDto closeQuestion(Long id) {
        Question question = repository.getOne(id);
        question.setClosed(true);
        repository.save(question);
        return converter.convertQuestion(repository.getOne(id));
    }

    public void deleteQuestion(Long id) {
        repository.deleteById(id);
    }
}
