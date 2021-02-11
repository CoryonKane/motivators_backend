package com.codecool.motivators.repository;

import com.codecool.motivators.model.QuestionGroup;
import com.codecool.motivators.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionGroupRepository extends JpaRepository<QuestionGroup, Long> {
    List<QuestionGroup> findAllByInvitedContains (User user);
}
