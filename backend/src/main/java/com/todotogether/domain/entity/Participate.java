package com.todotogether.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Participate extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pId;

    @ManyToOne
    @JoinColumn(name = "pTgid", nullable = false)
    private Together pTogether;

    @ManyToOne
    @JoinColumn(name = "pUid", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String pStartDate;

    @Column(nullable = false)
    private String pEndDate;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean pStatus;
}
