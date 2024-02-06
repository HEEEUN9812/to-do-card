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
@RequestMapping("/api")
public class CardController {

    private final CardService cardService;

    @PostMapping("/to-do")
    public CardResponseDto createCard(@RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.createCard(requestDto, userDetails.getUser());
    }

    @GetMapping("/to-do")
    public List<CardCommentResponseDto> getCardList() {
        return cardService.getCardList();
    }

    @GetMapping("/to-do/{id}")
    public CardCommentResponseDto getCard(@PathVariable Long id) {
        return cardService.getCard(id);
    }

    @PutMapping("/to-do/{id}")
    public CardResponseDto updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.updateCard(id, requestDto, userDetails.getUser());
    }

    @PatchMapping("/to-do/{id}")
    public CardResponseDto complete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.complete(id, userDetails.getUser());
    }

    @DeleteMapping("/to-do/{id}")
    public Long deleteCard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.deleteCard(id, userDetails.getUser());
    }

}
