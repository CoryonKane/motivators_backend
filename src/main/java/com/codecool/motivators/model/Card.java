package com.codecool.motivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Card {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CardType type;
    @Column(nullable = false)
    private int position;
    @Enumerated
    @Builder.Default
    private CardValueType value = CardValueType.NEUTRAL;
    @ManyToOne(optional = false)
    private Question question;
    @ManyToOne(optional = false)
    private User owner;
}
