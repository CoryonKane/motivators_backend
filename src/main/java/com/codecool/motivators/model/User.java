package com.codecool.motivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    private String name;
    private String email;
    private String password;
    private String position;
    // TODO: 2021. 01. 26. private Set<QuestionGroup> groups;
    // TODO: 2021. 01. 26. private Map<List<Card>, Date> defaults;
}
