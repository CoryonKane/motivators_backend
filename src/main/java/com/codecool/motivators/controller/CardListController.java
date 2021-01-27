package com.codecool.motivators.controller;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.service.CardListService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("card-list")
public class CardListController {
    private final CardListService service;

    public CardListController(@Lazy CardListService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public List<CardDto> getCardList (@PathVariable("id") Long id) {
        return service.getCardList(id);
    }
}
