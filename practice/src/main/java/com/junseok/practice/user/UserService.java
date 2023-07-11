package com.junseok.practice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User signUpUser(UserRequestDto userRequestDto) {
        User user = User.builder()
                .email(userRequestDto.getEmail())
                .password((userRequestDto.getPassword()))
                .build();
        return userRepository.save(user);
    }

    public UserResponseDto findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(UserResponseDto::new).orElse(null);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.update(userRequestDto);
            User updatedUser = userRepository.save(user);
            return new UserResponseDto(updatedUser);
        } else {
            return null;
        }
    }


    public boolean deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        } else {
            return false;
        }
    }
}
