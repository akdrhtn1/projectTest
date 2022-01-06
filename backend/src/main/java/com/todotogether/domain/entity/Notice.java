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
public class Notice extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long nId;

    @Column(nullable = false, length = 60)
    private String nSubject;

    @Column(nullable = false)
    private String nContent;

    @Column(nullable = false)
    @ColumnDefault("0")
    private long nCount;

    @Column(length = 70)
    private String nFile;

    @Column(length = 70)
    private String nOriginFile;
}
