package com.sparta.todocard.controller;

import com.sparta.todocard.dto.CardCommentResponseDto;
import com.sparta.todocard.dto.CardRequestDto;
import com.sparta.todocard.dto.CardResponseDto;
import com.sparta.todocard.security.UserDetailsImpl;
import com.sparta.todocard.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class CardController {

    private final CardService cardService;

    @PostMapping("/todo")
    public CardResponseDto createCard(@RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.createCard(requestDto, userDetails.getUser());
    }

    @GetMapping("/todo")
    public List<CardCommentResponseDto> getCardList() {
        return cardService.getCardList();
    }

    @GetMapping("/todo/{id}")
    public CardCommentResponseDto getCard(@PathVariable Long id) {
        return cardService.getCard(id);
    }

    @PutMapping("/todo/{id}")
    public CardResponseDto updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.updateCard(id, requestDto, userDetails.getUser());
    }

    @PatchMapping("/todo/{id}")
    public CardResponseDto complete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.complete(id, userDetails.getUser());
    }

    @DeleteMapping("/todo/{id}")
    public Long deleteCard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.deleteCard(id, userDetails.getUser());
    }

}
