package com.codecool.motivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    private User sender;
    @ManyToOne(optional = false)
    private User owner;
    @ManyToOne(optional = false)
    private QuestionGroup questionGroup;
}
