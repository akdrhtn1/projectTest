package com.todotogether.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aId;

    @ManyToOne
    @JoinColumn(name = "aRid")
    private Role role;

    @Column(nullable = false, length = 10)
    private String aLoginId;

    @Column(nullable = false, length = 70)
    private String aPassword;

    @Column(length = 40)
    private String aEmail;
}
