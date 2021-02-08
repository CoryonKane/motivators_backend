package com.codecool.motivators.controller;

import com.codecool.motivators.dto.QuestionGroupDto;
import com.codecool.motivators.dto.UserDto;
import com.codecool.motivators.service.QuestionGroupService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question-group/")
@CrossOrigin(origins = {"https://development-codecool-moving-motivators.netlify.app/", "https://codecool-moving-motivators.netlify.app/"})
public class QuestionGroupController {
    private final QuestionGroupService service;

    public QuestionGroupController(@Lazy QuestionGroupService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public QuestionGroupDto getQuestionGroup (@PathVariable("id") Long id) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getQuestionGroupDtoById(id, sessionUserEmail);
    }

    @PostMapping("")
    public QuestionGroupDto createQuestionGroup (@RequestBody String name) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.createQuestionGroup(name, sessionUserEmail);
    }

    @GetMapping("{id}/invited")
    public List<UserDto> viewInvited (@PathVariable("id") Long id) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.viewInvited(id, sessionUserEmail);
    }

    @PutMapping("{id}")
    public String editName (@PathVariable("id") Long id, @RequestBody String name) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.editName(id, name, sessionUserEmail);
    }

    @DeleteMapping("{id}")
    public UserDto deleteQuestionGroup (@PathVariable("id") Long id) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.deleteQuestionGroup(id, sessionUserEmail);
    }
}
