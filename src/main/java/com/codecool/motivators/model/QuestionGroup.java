package com.codecool.motivators.model;

import lombok.*;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import java.util.ArrayList;
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
    @EqualsAndHashCode.Exclude
    private Set<Question> questions = new HashSet<>();
    @ManyToMany(mappedBy = "invitedTo")
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Set<User> invited = new HashSet<>();
    @ManyToOne(optional = false)
    @EqualsAndHashCode.Exclude
    private User owner;
    @OneToMany
    @EqualsAndHashCode.Exclude
    private List<Notification> notifications = new ArrayList<>();

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
