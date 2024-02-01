package com.sparta.todocard.dto;

import com.sparta.todocard.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToDoCardResponseDto {
    private Long id;
    private String title;
    private String content;

    public ToDoCardResponseDto(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
    }
}
