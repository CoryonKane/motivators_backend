package com.codecool.motivators.service;

import com.codecool.motivators.dto.QuestionDto;
import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.dto.UserDto;
import com.codecool.motivators.model.QuestionGroup;
import com.codecool.motivators.model.User;
import com.codecool.motivators.repository.QuestionGroupRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionGroupService {
    private final DtoConverterService converter;
    private final QuestionGroupRepository repository;
    private final QuestionService questionService;
    private final UserService userService;

    public QuestionGroupService(
            @Lazy DtoConverterService converter,
            QuestionGroupRepository repository,
            @Lazy QuestionService questionService,
            @Lazy UserService userService) {
        this.converter = converter;
        this.repository = repository;
        this.questionService = questionService;
        this.userService = userService;
    }

    public QuestionGroupDto getQuestionGroupDtoById(Long id, String email) {
        QuestionGroup questionGroup = getQuestionGroupById(id);
        User sessionUser = userService.getUserByEmail(email);
        if (sessionUser.equals(questionGroup.getOwner()) || questionGroup.hasInvited(sessionUser)) {
            return converter.convertQuestionGroup(getQuestionGroupById(id));
        } else throw new BadCredentialsException("Invalid user.");
    }

    public QuestionGroup getQuestionGroupById(Long id) {
        return repository.getOne(id);
    }

    public QuestionGroupDto createQuestionGroup(String name, String email) {
        QuestionGroup questionGroup = QuestionGroup.builder()
                .value(name)
                .owner(userService.getUserByEmail(email))
                .build();
        repository.save(questionGroup);
        return converter.convertQuestionGroup(questionGroup);
    }

    public List<UserDto> viewInvited(Long id, String email) {
        QuestionGroup questionGroup = repository.getOne(id);
        User sessionUser = userService.getUserByEmail(email);
        if (sessionUser.equals(questionGroup.getOwner())) {
            return questionGroup.getInvited().stream().map(converter::convertUser).collect(Collectors.toList());
        } else throw new BadCredentialsException("Invalid user.");
    }

    public String editName(Long id, String name, String email) {
        QuestionGroup questionGroup = repository.getOne(id);
        if (userService.getUserByEmail(email).equals(questionGroup.getOwner())) {
            questionGroup.setValue(name);
            return repository.save(questionGroup).getValue();
        } else throw new BadCredentialsException("Invalid user.");
    }

    public UserDto deleteQuestionGroup(Long id, String email) {
        User sessionUser = userService.getUserByEmail(email);
        if (sessionUser.equals(getQuestionGroupById(id).getOwner())) {
            repository.deleteById(id);
            return converter.convertUser(sessionUser);
        } else throw new BadCredentialsException("Invalid user.");
    }
}
