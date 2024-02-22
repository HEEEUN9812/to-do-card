package com.sparta.todocard.service;


import com.sparta.todocard.dto.CardCommentResponseDto;
import com.sparta.todocard.dto.CardRequestDto;
import com.sparta.todocard.dto.CardResponseDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.entity.Comment;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.CardRepository;
import com.sparta.todocard.repository.CommentRepository;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
        return cardRepository.findAll().stream().map(e -> new CardCommentResponseDto(e, commentRepository.findAllByCard(e).stream().map(f -> new CommentResponseDto(f, f.getUser())).toList())).toList();
    }

    public CardCommentResponseDto getCard(Long id) {
        Card card = findCard(id);
        List<CommentResponseDto> commentList = commentRepository.findAllByCard(card).stream()
            .map(e -> new CommentResponseDto(e, e.getUser())).toList();
        return new CardCommentResponseDto(card, commentList);
    }


    @Transactional
    public CardResponseDto updateCard(Long id, CardRequestDto requestDto, User user) {
        Card card = findCard(id);
        verifyUser(user, card);
        card.update(requestDto.getTitle(), requestDto.getContent());
        return new CardResponseDto(card, user);
    }

    @Transactional
    public Long deleteCard(Long id, User user) {
        Card card = findCard(id);
        verifyUser(user, card);
        cardRepository.delete(card);
        return id;
    }

    @Transactional
    public CardResponseDto complete(Long id, User user) {
        Card card = findCard(id);
        card.complete();
        return new CardResponseDto(card, user);
    }


    public Card findCard(Long id) {
        return cardRepository.findById(id).orElseThrow(
            () -> new NullPointerException("no " + id));
    }

    public void verifyUser(User user, Card card) {
        if (!user.getId().equals(card.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자만 삭제/수정 할 수 있음");
        }
    }
}
