package com.sparta.todocard.dto;

import com.sparta.todocard.entity.Card;
import com.sparta.todocard.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;

    public CardResponseDto(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
//        this.username = user.getUsername();
        this.createdAt = card.getCreatedAt();
    }
}
