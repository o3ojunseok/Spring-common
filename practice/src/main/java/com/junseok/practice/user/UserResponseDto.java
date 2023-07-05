package com.junseok.practice.user;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private String password;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
