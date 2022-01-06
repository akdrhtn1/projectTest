package com.todotogether.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class RpCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rpcId;

    @Column(nullable = false, length = 10)
    private String rpcType;
}
