package com.junseok.practice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/check")
    public String healthCheck() {
        return "succeess";
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<UserResponseDto>> findAllArticles() {
        List<UserResponseDto> responseDto = userService.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/user/signup")
    public ResponseEntity<UserResponseDto> signUpUser(@RequestBody() UserRequestDto userRequestDto) {
        User createdUser = userService.signUpUser(userRequestDto);
        UserResponseDto response = new UserResponseDto(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
