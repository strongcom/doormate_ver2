package com.strongcom.doormate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Table(name="user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @JsonIgnore
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private Long kakaoId;

    @Column(name = "USERNAME", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "token", length = 200)
    private String token;

    @Column
    private String image_url;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reminder> reminders = new ArrayList<>();

    @Column(name = "target_token", length = 200, unique = true)
    private String targetToken;

    private String refreshToken;


    @Builder
    public User(Long kakaoId, String nickname, String targetToken, String refreshToken, String image_url) {
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.targetToken = targetToken;
        this.refreshToken = refreshToken;
        this.image_url = image_url;
    }

    public void setKakaoUser(String userName) {
        this.username = userName;
    }
}
