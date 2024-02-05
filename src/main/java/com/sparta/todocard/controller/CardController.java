package com.sparta.todocard.controller;

import com.sparta.todocard.dto.CardRequestDto;
import com.sparta.todocard.dto.CardResponseDto;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.security.UserDetailsImpl;
import com.sparta.todocard.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CardController {

    private final CardService cardService;

    @PostMapping("/to-do")
    public CardResponseDto createCard(@RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return cardService.createCard(requestDto, userDetails.getUser());
    }

    @GetMapping("/to-do")
    public List<CardResponseDto> getCardList(){
        return cardService.getCardList();
    }

    @GetMapping("/to-do/{id}")
    public CardResponseDto getCard(@PathVariable Long id){
        return cardService.getCard(id);
    }

    @PutMapping("/to-do/{id}")
    public CardResponseDto updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto){
        return cardService.updateCard(id, requestDto);
    }

    @DeleteMapping("/to-do/{id}")
    public Long deleteCard(@PathVariable Long id){
        return cardService.deleteCard(id);
    }

}
