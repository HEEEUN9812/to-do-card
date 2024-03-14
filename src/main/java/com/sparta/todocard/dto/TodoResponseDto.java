package com.sparta.todocard.dto;

import com.sparta.todocard.entity.Todo;
import com.sparta.todocard.entity.User;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private boolean complete;


    public TodoResponseDto(Todo todo, User user) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.username = user.getUsername();
        this.createdAt = todo.getCreatedAt();
        this.complete = todo.isComplete();
    }

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.username = todo.getUser().getUsername();
        this.createdAt = todo.getCreatedAt();
        this.complete = todo.isComplete();
    }
}
