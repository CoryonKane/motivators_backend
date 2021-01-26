package com.codecool.motivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Card {
    private Long id;
    private CardType type;
    private int position;
    private CardValueType value;
    // TODO: 2021. 01. 26. private Question question;
    // TODO: 2021. 01. 26. private User owner;
}
