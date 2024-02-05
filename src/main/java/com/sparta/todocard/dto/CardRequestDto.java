package com.sparta.todocard.dto;

import lombok.Getter;

@Getter
public class CardRequestDto {
    private String title;
    private String content;
    private boolean complete;

    public CardRequestDto(String title, String content, boolean complete) {
        this.title = title;
        this.content = content;
        this.complete = complete;
    }
}
