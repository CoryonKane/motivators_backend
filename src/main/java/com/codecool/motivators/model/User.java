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
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String position;
    @OneToMany
    @Builder.Default
    private Set<QuestionGroup> groups = new HashSet<>();
    @ElementCollection
    @Builder.Default
    private Map<CardList, Date> defaults = new HashMap<>();

    public void addGroup (QuestionGroup group) {
        this.groups.add(group);
    }

    public void removeGroup (QuestionGroup group) {
        this.groups.remove(group);
    }

    public void addDefault (CardList list) {
        Date date = new Date();
        defaults.put(list, date);
    }

    public void removeDefault (CardList list) {
        this.defaults.remove(list);
    }

    public CardList getNewestDefault () {
        return Collections.max(this.defaults.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
