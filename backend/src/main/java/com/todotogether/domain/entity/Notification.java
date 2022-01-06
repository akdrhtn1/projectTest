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
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ntId;

    @ManyToOne
    @JoinColumn(name = "ntUid", nullable = false)
    private Member nMember;

    @Column(length = 100)
    private String ntContent;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean ntStatus;

    @Column(nullable = false, length = 1)
    private byte ntType;
}
