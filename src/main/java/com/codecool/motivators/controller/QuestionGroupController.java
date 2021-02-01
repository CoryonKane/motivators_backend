package com.codecool.motivators.controller;

import com.codecool.motivators.dto.QuestionDto;
import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.dto.UserDto;
import com.codecool.motivators.service.QuestionGroupService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question-group/")
public class QuestionGroupController {
    private final QuestionGroupService service;

    public QuestionGroupController(@Lazy QuestionGroupService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public QuestionGroupDto getQuestionGroup (@PathVariable("id") Long id) {
        return service.getQuestionGroupDtoById(id);
    }

    @PostMapping("")
    public QuestionGroupDto createQuestionGroup (@RequestBody QuestionGroupDto questionGroupDto) {
        return service.createQuestionGroup(questionGroupDto);
    }

    @GetMapping("{id}/invited")
    public List<UserDto> viewInvited (@PathVariable("id") Long id) {
        return service.viewInvited(id);
    }

    @PutMapping("{id}")
    public String editName (@PathVariable("id") Long id, @RequestBody String name) {
        return service.editName(id, name);
    }

    @DeleteMapping("{id}")
    public UserDto deleteQuestionGroup (@PathVariable("id") Long id) {
        return service.deleteQuestionGroup(id);
    }
}
