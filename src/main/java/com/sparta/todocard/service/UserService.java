package com.sparta.todocard.service;


import com.sparta.todocard.dto.SignupRequestDto;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
//        String password = requestDto.getPassword();

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("중복 사용자 존재");
        }

        User user = new User(username, password);
        userRepository.save(user);
    }
}
