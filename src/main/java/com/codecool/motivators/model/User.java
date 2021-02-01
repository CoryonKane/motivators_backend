package com.codecool.motivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "account")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String company;
    @OneToMany
    @Builder.Default
    private Set<QuestionGroup> groups = new HashSet<>();
    @OneToMany
    @Builder.Default
    private List<CardList> defaultLists = new ArrayList<>();
    @OneToMany
    @Builder.Default
    private Set<Notification> sentNotification = new HashSet<>();
    @OneToMany
    @Builder.Default
    private Set<Notification> receivedNotification = new HashSet<>();

    public void addGroup (QuestionGroup group) {
        this.groups.add(group);
    }

    public void removeGroup (QuestionGroup group) {
        this.groups.remove(group);
    }

    public void addDefault (CardList list) {
        this.defaultLists.add(list);
    }

    public void removeDefault (CardList list) {
        this.defaultLists.remove(list);
    }

    public CardList getNewestDefault () {
        this.defaultLists.sort(Comparator.comparing(CardList::getCreatedOn));
        return this.defaultLists.get(1);
    }
}
