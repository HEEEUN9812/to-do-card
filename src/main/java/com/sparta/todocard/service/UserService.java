package com.sparta.todocard.service;


import com.sparta.todocard.dto.SignupRequestDto;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists.");
        }

        User user = new User(username, password);
        userRepository.save(user);
    }
}
