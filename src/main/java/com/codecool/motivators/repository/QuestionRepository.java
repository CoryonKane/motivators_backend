package com.codecool.motivators.repository;

import com.codecool.motivators.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
