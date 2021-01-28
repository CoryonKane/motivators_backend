package com.codecool.motivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String value;
    @OneToOne(optional = false)
    private CardList answer;
    private String note;
    @Builder.Default
    private boolean closed = false;
    @ManyToOne(optional = false)
    private QuestionGroup group;
}
