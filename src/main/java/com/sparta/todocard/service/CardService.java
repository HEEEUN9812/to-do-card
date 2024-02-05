package com.sparta.todocard.service;


import com.sparta.todocard.dto.*;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.CardRepository;
import com.sparta.todocard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CommentRepository commentRepository;


    public CardResponseDto createCard(CardRequestDto requestDto, User user) {
        Card card = cardRepository.save(new Card(requestDto, user));

        return new CardResponseDto(card, user);
    }

    public List<CardCommentResponseDto> getCardList() {
        return cardRepository.findAll().stream().map(e -> new CardCommentResponseDto(e, e.getUser(),commentRepository.findAllByCard(e).stream().map(f -> new CommentResponseDto(f, f.getUser())).toList())).toList();
    }

    public CardCommentResponseDto getCard(Long id) {
        Card card = findCard(id);
        List<CommentResponseDto> commentList = commentRepository.findAllByCard(card).stream().map(e -> new CommentResponseDto(e, e.getUser())).toList();
        return new CardCommentResponseDto(card, card.getUser(), commentList);
    }


    @Transactional
    public CardResponseDto updateCard(Long id, CardRequestDto requestDto, User user) {
        Card card = findCard(id);
        card.update(requestDto);
        return new CardResponseDto(card, user);
    }

    @Transactional
    public Long deleteCard(Long id, User user) {
        Card card = findCard(id);
        cardRepository.delete(card);
        return id;
    }


    public Card findCard(Long id) {
        return cardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("no " + id));
    }
    //    @Transactional
//    public CardResponseDto completeCard(Long id, CardCompleteRequestDto requestDto) {
//        Card card = findCard(id);
//        card.complete(requestDto);
//        return new CardResponseDto(card, card.getUser());
//    }
}
