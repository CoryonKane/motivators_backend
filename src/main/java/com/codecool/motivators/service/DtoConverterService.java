package com.codecool.motivators.service;

import com.codecool.motivators.dto.*;
import com.codecool.motivators.model.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoConverterService {

    public CardDto convertCard (Card card) {
        return CardDto.builder()
                .id(card.getId())
                .listId(card.getList().getId())
                .position(card.getPosition())
                .type(card.getType())
                .value(card.getValue())
                .build();
    }

    public CardListDto convertCardList (CardList list) {
        return CardListDto.builder()
                .id(list.getId())
                .cardIds(list.getCards().stream().map(Card::getId).collect(Collectors.toList()))
                .build();
    }

    public QuestionDto convertQuestion (Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .answerId(question.getAnswer().getId())
                .closed(question.isClosed())
                .groupId(question.getGroup().getId())
                .note(question.getNote())
                .value(question.getValue())
                .build();
    }

    public QuestionGroupDto convertQuestionGroup (QuestionGroup questionGroup) {
        return QuestionGroupDto.builder()
                .id(questionGroup.getId())
                .ownerId(questionGroup.getOwner().getId())
                .value(questionGroup.getValue())
                .invitedIds(questionGroup.getInvited().stream().map(User::getId).collect(Collectors.toList()))
                .questionIds(questionGroup.getQuestions().stream().map(Question::getId).collect(Collectors.toList()))
                .build();
    }

    public UserDto convertUser (User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .position(user.getPosition())
                .defaultCardListId(user.getNewestDefault().getId())
                .cardListIds(user.getDefaults().keySet().stream().map(CardList::getId).collect(Collectors.toList()))
                .groupIds(user.getGroups().stream().map(QuestionGroup::getId).collect(Collectors.toList()))
                .build();
    }
}
