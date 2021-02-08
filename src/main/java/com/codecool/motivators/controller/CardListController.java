package com.codecool.motivators.controller;

import com.codecool.motivators.dto.CardDto;
import com.codecool.motivators.service.CardListService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("card-list")
@CrossOrigin(origins = {"https://development-codecool-moving-motivators.netlify.app/", "https://codecool-moving-motivators.netlify.app/"})
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
