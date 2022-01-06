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
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cId;

    @ManyToOne
    @JoinColumn(name = "cUid")
    private Member cMember;

    @ManyToOne
    @JoinColumn(name = "cTgid")
    private Together cTogether;

    @Column(nullable = false, length = 200)
    private String cContent;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @ColumnDefault("0")
    private long cLev;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @ColumnDefault("0")
    private long cSeq;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean cStatus;
}
