package com.codecool.motivators.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    @OneToOne
    @EqualsAndHashCode.Exclude
    private CardList answer;
    private String note;
    @Builder.Default
    private boolean closed = false;
    @ManyToOne(optional = false)
    @EqualsAndHashCode.Exclude
    private QuestionGroup group;
    private Date date;
}
