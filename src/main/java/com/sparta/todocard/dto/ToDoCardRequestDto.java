package com.sparta.todocard.dto;

import lombok.Getter;

@Getter
public class ToDoCardRequestDto {
    private String title;
    private String content;

    public ToDoCardRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
