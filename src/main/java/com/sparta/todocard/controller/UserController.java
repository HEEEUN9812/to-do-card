package com.sparta.todocard.controller;


import com.sparta.todocard.dto.SignupRequestDto;
import com.sparta.todocard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j(topic = "유저 컨트롤러")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        log.info(requestDto.getUsername());
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body("회원가입 실패");
        }

        userService.signup(requestDto);

        return ResponseEntity.ok().body("회원가입 성공");
    }
}
