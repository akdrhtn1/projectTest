package com.todotogether.domain.entity;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Todo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tId;

    @ManyToOne
    @JoinColumn(name = "tUid", nullable = false)
    private Member member;

    @Column(length = 300, nullable = false)
    private String tContent;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean tStatus;
}
