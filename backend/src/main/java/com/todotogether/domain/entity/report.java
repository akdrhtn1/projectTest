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
public class report extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rpId;

    @ManyToOne
    @JoinColumn(name = "rpUid", nullable = false)
    private Member member  ;

    @ManyToOne
    @JoinColumn(name = "rcCid", nullable = false)
    private RpCategory rpCategory;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean rpStatus;

    @Column(nullable = false)
    private long rpTarget;
}
