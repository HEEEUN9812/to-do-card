package com.sparta.todocard.service;


import com.sparta.todocard.dto.CardRequestDto;
import com.sparta.todocard.dto.CardResponseDto;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;


    public CardResponseDto createCard(CardRequestDto requestDto) {
        Card card = cardRepository.save(new Card(requestDto));

        return new CardResponseDto(card);
    }

    public List<CardResponseDto> getCardList() {
        return cardRepository.findAll().stream().map(CardResponseDto::new).toList();
    }

    public CardResponseDto getCard(Long id) {
        Card card = findCard(id);

        return new CardResponseDto(card);
    }


    public Card findCard(Long id){
        return cardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("no " + id));
    }

    @Transactional
    public CardResponseDto updateCard(Long id, CardRequestDto requestDto) {
        Card card = findCard(id);
        card.update(requestDto);
        return new CardResponseDto(card);
    }

    @Transactional
    public Long deleteCard(Long id) {
        Card card = findCard(id);
        cardRepository.delete(card);
        return id;
    }
}
