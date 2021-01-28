package com.codecool.motivators.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardType {
    CURIOSITY("Curiosity", "I have plenty of things to investigate and to think about.", ""),
    HONOR("Honor", "I feel proud that my personal values are reflected in how I work.", ""),
    ACCEPTANCE("Acceptance", "The people around me approve of what I do and who I am.", ""),
    MASTERY("Mastery", "My work challenges my competence but it is still within my abilities.", ""),
    POWER("Power", "There’s enough room for me to influence what happens around me.", ""),
    FREEDOM("Freedom", "I am independent of others with my work and my responsibilities.", ""),
    RELATEDNESS("Relatedness", "I have good social contacts with the people in my work.", ""),
    ORDER("Order", "There are enough rules and policies for a stable environment.", ""),
    GOAL("Goal", "My purpose in life is reflected in the work that I do.", ""),
    STATUS("Status", "My position is good, and recognized by the people who work with me.", "");

    private final String name;
    private final String value;
    private  final String imgUrl;
}
