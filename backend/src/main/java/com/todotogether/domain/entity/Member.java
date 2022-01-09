package com.todotogether.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uId;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 70)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean enabled;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, length = 40)
    private String backupEmail;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int rpCount;

    @Column(length = 30)
    private String profile;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "nMember", cascade = CascadeType.REMOVE)
    private List<Notification> notifications;
}
