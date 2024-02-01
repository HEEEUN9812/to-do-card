package com.sparta.todocard.service;


import com.sparta.todocard.dto.ToDoCardRequestDto;
import com.sparta.todocard.dto.ToDoCardResponseDto;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ToDoCardService {

    private final ToDoRepository toDoRepository;


    public ToDoCardResponseDto createToDoCard(ToDoCardRequestDto requestDto) {
        Card card = toDoRepository.save(new Card(requestDto));

        return new ToDoCardResponseDto(card);
    }

    public List<ToDoCardResponseDto> getToDoCard() {
        return toDoRepository.findAll().stream().map(ToDoCardResponseDto::new).toList();
    }
}
