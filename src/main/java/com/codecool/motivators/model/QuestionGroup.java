package com.codecool.motivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class QuestionGroup {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String value;
    @OneToMany
    @Builder.Default
    private Set<Question> questions = new HashSet<>();
    @OneToMany
    @Builder.Default
    private Set<User> invited = new HashSet<>();
    @ManyToOne(optional = false)
    private User owner;

    public void addQuestion (Question question) {
        this.questions.add(question);
    }

    public void removeQuestion (Question question) {
        this.questions.remove(question);
    }

    public void addInvited (User user) {
        this.invited.add(user);
    }

    public void removeInvited (User user) {
        this.invited.remove(user);
    }

    public boolean hasInvited (User user) {
        return this.invited.contains(user);
    }
}
