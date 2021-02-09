package com.codecool.motivators.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Set<QuestionGroup> groups = new HashSet<>();
    @OneToMany
    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private List<CardList> defaultLists = new ArrayList<>();
    @OneToMany
    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Set<Notification> sentNotification = new HashSet<>();
    @OneToMany
    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Set<Notification> receivedNotification = new HashSet<>();
    @ElementCollection
    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private List<String> roles = new ArrayList<>();

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

    @JsonIgnore
    public CardList getNewestDefault () {
        this.defaultLists.sort(Comparator.comparing(CardList::getCreatedOn));
        return this.defaultLists.size() == 0 ? null : this.defaultLists.get(1);
    }

    public void addSentNotification (Notification notification) {
        this.sentNotification.add(notification);
    }

    public void removeSentNotification (Notification notification) {
        this.sentNotification.remove(notification);
    }

    public void addReceivedNotification (Notification notification) {
        this.receivedNotification.add(notification);
    }

    public void removeReceivedNotification (Notification notification) {
        this.receivedNotification.remove(notification);
    }
}
