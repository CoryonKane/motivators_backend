package com.codecool.motivators.service;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.dto.QuestionDto;
import com.codecool.motivators.model.CardList;
import com.codecool.motivators.model.Question;
import com.codecool.motivators.model.User;
import com.codecool.motivators.repository.QuestionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    private final DtoConverterService converter;
    private final QuestionRepository repository;
    private final CardListService cardListService;
    private final QuestionGroupService questionGroupService;
    private final UserService userService;

    public QuestionService(
            @Lazy DtoConverterService converter,
            QuestionRepository repository,
            @Lazy CardListService cardListService,
            @Lazy QuestionGroupService questionGroupService,
            @Lazy UserService userService) {
        this.converter = converter;
        this.repository = repository;
        this.cardListService = cardListService;
        this.questionGroupService = questionGroupService;
        this.userService = userService;
    }

    public QuestionDto getQuestionDtoById(Long id, String email) {
        Question question = getQuestionById(id);
        User sessionUser = userService.getUserByEmail(email);
        if (sessionUser.equals(question.getGroup().getOwner()) || question.getGroup().hasInvited(sessionUser)) {
            return converter.convertQuestion(getQuestionById(id));
        } else throw new BadCredentialsException("Invalid user.");
    }

    public Question getQuestionById(Long id) {
        return repository.getOne(id);
    }

    public Question saveQuestion(Question question) {
        return repository.save(question);
    }

    public List<CardDto> setAnswer(Long questionId, List<CardDto> cards, String email) {
        Question question = getQuestionById(questionId);
        if (!question.isClosed() && userService.getUserByEmail(email).equals(question.getGroup().getOwner())) {
            CardList cardList = cardListService.createCardList(cards);
            question.setAnswer(cardList);
        }
        return converter.convertCardList(saveQuestion(question).getAnswer());
    }

    public String editNote(Long id, String note, String email) {
        Question question = getQuestionById(id);
        if (!question.isClosed() && userService.getUserByEmail(email).equals(question.getGroup().getOwner())) {
            question.setNote(note);
        }
        return saveQuestion(question).getNote();
    }

    public QuestionDto closeQuestion(Long id, String email) {
        Question question = getQuestionById(id);
        if (userService.getUserByEmail(email).equals(question.getGroup().getOwner())) {
            question.setClosed(true);
        }
        return converter.convertQuestion(saveQuestion(question));
    }

    public void deleteQuestion(Long id, String email) {
        if (userService.getUserByEmail(email).equals(repository.getOne(id).getGroup().getOwner())) {
            repository.deleteById(id);
        } else throw new BadCredentialsException("Invalid user.");
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
