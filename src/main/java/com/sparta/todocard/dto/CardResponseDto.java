package com.sparta.todocard.dto;

import com.sparta.todocard.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CardResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public CardResponseDto(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.createdAt = card.getCreatedAt();
    }
}
