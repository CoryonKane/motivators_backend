package com.codecool.motivators.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class QuestionTests {
    @Test
    void testQuestionGroupAddQuestion () {
        Question q = new Question();
        QuestionGroup questionGroup = new QuestionGroup();
        questionGroup.addQuestion(q);
        assertFalse(questionGroup.getQuestions().isEmpty());
    }

    @Test
    void testQuestionGroupRemoveQuestion () {
        Question q = new Question();
        QuestionGroup questionGroup = new QuestionGroup();
        questionGroup.addQuestion(q);
        questionGroup.removeQuestion(q);
        assertTrue(questionGroup.getQuestions().isEmpty());
    }

    @Test
    void testQuestionGroupAddInvited () {
        User u = new User();
        QuestionGroup q = new QuestionGroup();
        q.addInvited(u);
        assertFalse(q.getInvited().isEmpty());
    }

    @Test
    void testQuestionGroupRemoveInvited () {
        User u = new User();
        QuestionGroup q = new QuestionGroup();
        q.addInvited(u);
        q.removeInvited(u);
        assertTrue(q.getInvited().isEmpty());
    }

    @Test
    void testQuestionGroupHasInvited () {
        User u = new User();
        QuestionGroup q = new QuestionGroup();
        q.addInvited(u);
        assertTrue(q.hasInvited(u));
    }
}
