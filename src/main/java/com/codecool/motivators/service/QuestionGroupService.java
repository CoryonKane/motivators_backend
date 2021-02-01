package com.codecool.motivators.service;

import com.codecool.motivators.dto.QuestionDto;
import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.dto.UserDto;
import com.codecool.motivators.model.QuestionGroup;
import com.codecool.motivators.model.User;
import com.codecool.motivators.repository.QuestionGroupRepository;
import org.springframework.context.annotation.Lazy;
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

    public QuestionGroupDto getQuestionGroupDtoById(Long id) {
        return converter.convertQuestionGroup(getQuestionGroupById(id));
    }

    public QuestionGroup getQuestionGroupById(Long id) {
        return repository.getOne(id);
    }

    public QuestionGroupDto createQuestionGroup(QuestionGroupDto questionGroupDto) {
        QuestionGroup questionGroup = QuestionGroup.builder()
                .owner(userService.getUserById(questionGroupDto.getOwnerId()))
                .build();
        repository.save(questionGroup);
        return converter.convertQuestionGroup(questionGroup);
    }

    public List<UserDto> viewInvited(Long id) {
        QuestionGroup questionGroup = repository.getOne(id);
        return questionGroup.getInvited().stream().map(converter::convertUser).collect(Collectors.toList());
    }

    public String editName(Long id, String name) {
        QuestionGroup questionGroup = repository.getOne(id);
        questionGroup.setValue(name);
        return repository.save(questionGroup).getValue();
    }

    public UserDto deleteQuestionGroup(Long id) {
        QuestionGroup questionGroup = repository.getOne(id);            // TODO: 2021. 01. 28. can be deleted after security
        User user = questionGroup.getOwner();
        repository.delete(questionGroup);
        return converter.convertUser(user);
    }
}
