package com.sparta.todocard.dto;

import com.sparta.todocard.entity.Card;
import com.sparta.todocard.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private boolean complete;



    public CardResponseDto(Card card, User user) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.username = user.getUsername();
        this.createdAt = card.getCreatedAt();
        this.complete = card.isComplete();
    }
}
