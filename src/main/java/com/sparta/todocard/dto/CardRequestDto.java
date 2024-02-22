package com.sparta.todocard.dto;

import lombok.Getter;

@Getter
public class CardRequestDto {

    private String title;
    private String content;

    public CardRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
