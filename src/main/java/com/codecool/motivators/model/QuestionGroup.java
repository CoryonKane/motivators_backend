package com.codecool.motivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class QuestionGroup {
    private String value;
    // TODO: 2021. 01. 26. private Question questions;
    // TODO: 2021. 01. 26. private List<User> invited;
    // TODO: 2021. 01. 26. private User owner;
}
