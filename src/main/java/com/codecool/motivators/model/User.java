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
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<QuestionGroup> groups = new HashSet<>();
    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<CardList> defaultLists = new ArrayList<>();
    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Notification> sentNotification = new HashSet<>();
    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Notification> receivedNotification = new HashSet<>();
    @ElementCollection
    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<String> roles = new ArrayList<>();
    @ManyToMany
    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<QuestionGroup> invitedTo = new HashSet<>();

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
        return this.defaultLists.size() == 0 ? null : this.defaultLists.get(this.defaultLists.size() - 1);
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

    public void addInvitedTo (QuestionGroup questionGroup) {
        this.invitedTo.add(questionGroup);
    }

    public void removeInvitedTo(QuestionGroup questionGroup) {
        this.invitedTo.remove(questionGroup);
    }
}
