package com.junseok.practice.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
// builder vs 생성자
@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void update(UserRequestDto userRequestDto) {
        this.email = userRequestDto.getEmail();
        this.password = userRequestDto.getPassword();
    }
}
