package com.codecool.motivators.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false)
    @EqualsAndHashCode.Exclude
    private User sender;
    @ManyToOne(optional = false)
    @EqualsAndHashCode.Exclude
    private User owner;
    @ManyToOne(optional = false)
    @EqualsAndHashCode.Exclude
    private QuestionGroup questionGroup;
}
