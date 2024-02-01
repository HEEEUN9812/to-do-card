package com.sparta.todocard.controller;

import com.sparta.todocard.dto.ToDoCardRequestDto;
import com.sparta.todocard.dto.ToDoCardResponseDto;
import com.sparta.todocard.service.ToDoCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ToDoCardController {

    private final ToDoCardService toDoCardService;

    @PostMapping("/to-do")
    public ToDoCardResponseDto createToDoCard(@RequestBody ToDoCardRequestDto requestDto){
        return toDoCardService.createToDoCard(requestDto);
    }

    @GetMapping("/to-do")
    public List<ToDoCardResponseDto> getToDoCardList(){
        return toDoCardService.getToDoCardList();
    }

    @GetMapping("/to-do/{id}")
    public ToDoCardResponseDto getToDoCard (@PathVariable Long id){
        return toDoCardService.getToDoCard(id);
    }

    @PutMapping("/to-do/{id}")
    public ToDoCardResponseDto updateToDoCard(@PathVariable Long id, @RequestBody ToDoCardRequestDto requestDto){
        return toDoCardService.updateToDoCard(id, requestDto);
    }
}
