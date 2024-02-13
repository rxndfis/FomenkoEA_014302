package com.hrdepartment.model;

import com.hrdepartment.model.enums.VacancyStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Vacancy {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Jobs job;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;
    private VacancyStatus status;

    public Vacancy(Jobs job, Users user) {
        this.job = job;
        this.user = user;
        this.status = VacancyStatus.WAITING;
    }
}
