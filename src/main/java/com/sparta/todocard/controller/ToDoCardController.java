package com.sparta.todocard.controller;

import com.sparta.todocard.dto.ToDoCardRequestDto;
import com.sparta.todocard.dto.ToDoCardResponseDto;
import com.sparta.todocard.service.ToDoCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ToDoCardController {

    private final ToDoCardService toDoCardService;

    @PostMapping("/to-do")
    public ToDoCardResponseDto createToDoCard(@RequestBody ToDoCardRequestDto requestDto){
        return toDoCardService.createToDoCard(requestDto);
    }
}
