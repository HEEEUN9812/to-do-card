package com.sparta.todocard.dto;

import com.sparta.todocard.entity.Todo;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class TodoCommentResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentList;

    public TodoCommentResponseDto(Todo todo, List<CommentResponseDto> commentList) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.username = todo.getUser().getUsername();
        this.createdAt = todo.getCreatedAt();
        this.commentList = commentList;
    }
}
