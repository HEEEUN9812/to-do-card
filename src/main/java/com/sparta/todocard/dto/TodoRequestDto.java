package com.sparta.todocard.dto;

import lombok.Getter;

@Getter
public class TodoRequestDto {

    private String title;
    private String content;

    public TodoRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
