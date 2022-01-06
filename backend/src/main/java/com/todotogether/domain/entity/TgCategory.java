package com.todotogether.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class TgCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tgcId;

    @Column(length = 10, nullable = false)

    private String tgcType;

    @OneToMany(mappedBy = "tgCategory")
    private List<Together> togethers;
  
}
