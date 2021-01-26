package com.codecool.motivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Question {
    private String value;
    // TODO: 2021. 01. 26. private List<Card> answer;
    private String note;
    @Builder.Default
    private boolean closed = false;
    // TODO: 2021. 01. 26. private QuestionGroup group;
}
