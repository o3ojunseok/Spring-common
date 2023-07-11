package com.junseok.practice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto updatedUser = userService.updateUser(id, userRequestDto);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
