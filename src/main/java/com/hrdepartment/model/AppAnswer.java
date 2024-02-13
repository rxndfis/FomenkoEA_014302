package com.hrdepartment.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AppAnswer {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 5000)
    private String text;
    private String date;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private Apps app;

    public AppAnswer(String text, String date, Users owner, Apps app) {
        this.text = text;
        this.date = date;
        this.owner = owner;
        this.app = app;
    }
}
