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
public class Offers {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;
    private String offer;

    public Offers(Users user, String offer) {
        this.user = user;
        this.offer = offer;
    }
}
