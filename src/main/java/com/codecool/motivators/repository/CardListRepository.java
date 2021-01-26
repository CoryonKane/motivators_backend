package com.codecool.motivators.repository;

import com.codecool.motivators.model.CardList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardListRepository extends JpaRepository<CardList, Long> {
}
