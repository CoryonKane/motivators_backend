package com.codecool.motivators.controller;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.dto.QuestionDto;
import com.codecool.motivators.service.QuestionService;
import org.hibernate.sql.Delete;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question/")
public class QuestionController {
    private final QuestionService service;

    public QuestionController(@Lazy QuestionService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public QuestionDto getQuestion (@PathVariable("id") Long id) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getQuestionDtoById(id, sessionUserEmail);
    }

    @PostMapping("{id}/answer")
    public List<CardDto> setAnswer (@PathVariable("id") Long questionId, @RequestBody List<CardDto> cards) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.setAnswer(questionId, cards, sessionUserEmail);
    }

    @PutMapping("{id}/note")
    public String editNote (@PathVariable("id") Long id, @RequestBody String note) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.editNote(id, note, sessionUserEmail);
    }

    @PutMapping("{id}/close")
    public QuestionDto closeQuestion (@PathVariable("id") Long id) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.closeQuestion(id, sessionUserEmail);
    }

    @DeleteMapping("{id}")
    public void deleteQuestion (@PathVariable("id") Long id) {
        String sessionUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        service.deleteQuestion(id, sessionUserEmail);
    }

    @PostMapping("")
    public QuestionDto createQuestion (@RequestBody QuestionDto questionDto) {
        return service.createQuestion(questionDto);
    }
}
