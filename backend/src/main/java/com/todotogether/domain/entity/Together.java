package com.todotogether.domain.entity;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Together extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tgId;

    @ManyToOne
    @JoinColumn(name = "tgUid")
    private Member tMember;

    @ManyToOne
    @JoinColumn(name = "tgcId", nullable = false)
    private TgCategory tgCategory;

    @Column(length = 60, nullable = false)
    private String tgSubject;

    @Column(length = 300, nullable = false)
    private String tgContent;

    @Column(length = 30)
    private String tgImage;

    @ColumnDefault("true")
    @Column(nullable = false)
    private boolean tgStatus;

    @OneToMany(mappedBy = "pTogether", cascade = CascadeType.REMOVE)
    private List<Participate> participates;

    @OneToMany(mappedBy = "cTogether")
    private List<Comment> comments;
}
