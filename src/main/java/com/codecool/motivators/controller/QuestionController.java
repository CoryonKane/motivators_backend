package com.codecool.motivators.controller;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.dto.QuestionDto;
import com.codecool.motivators.service.QuestionService;
import org.hibernate.sql.Delete;
import org.springframework.context.annotation.Lazy;
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
        return service.getQuestionDtoById(id);
    }

    @PostMapping("{id}/answer")
    public List<CardDto> setAnswer (@PathVariable("id") Long questionId, @RequestBody List<CardDto> cards) {
        return service.setAnswer(questionId, cards);
    }

    @PutMapping("{id}/note")
    public String editNote (@PathVariable("id") Long id, @RequestBody String note) {
        return service.editNote(id, note);
    }

    @PutMapping("{id}/close")
    public QuestionDto closeQuestion (@PathVariable("id") Long id) {
        return service.closeQuestion(id);
    }

    @DeleteMapping("{id}")
    public void deleteQuestion (@PathVariable("id") Long id) {
        service.deleteQuestion(id);
    }

    @PostMapping("")
    public QuestionDto createQuestion (@RequestBody QuestionDto questionDto) {
        return service.createQuestion(questionDto);
    }
}
