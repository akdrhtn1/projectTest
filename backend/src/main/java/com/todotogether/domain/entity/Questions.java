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
public class Questions extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long qId;

    @Column(length = 40, nullable = false)
    private String qEmail;

    @Column(length = 60, nullable = false)
    private String qSubject;

    @Column(length = 300, nullable = false)
    private String qContent;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean qStatus;
}
