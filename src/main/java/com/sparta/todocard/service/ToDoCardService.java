package com.sparta.todocard.service;


import com.sparta.todocard.dto.ToDoCardRequestDto;
import com.sparta.todocard.dto.ToDoCardResponseDto;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.repository.ToDoCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ToDoCardService {

    private final ToDoCardRepository toDoCardRepository;


    public ToDoCardResponseDto createToDoCard(ToDoCardRequestDto requestDto) {
        Card card = toDoCardRepository.save(new Card(requestDto));

        return new ToDoCardResponseDto(card);
    }

    public List<ToDoCardResponseDto> getToDoCardList() {
        return toDoCardRepository.findAll().stream().map(ToDoCardResponseDto::new).toList();
    }

    public ToDoCardResponseDto getToDoCard(Long id) {
        Card card = findToDoCard(id);

        return new ToDoCardResponseDto(card);
    }

    public Card findToDoCard(Long id){
        return toDoCardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("no " + id));
    }
}
