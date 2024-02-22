package com.sparta.todocard.dto;

import com.sparta.todocard.entity.Comment;
import com.sparta.todocard.entity.User;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String content;
    private Long card_id;
    private String username;
    private LocalDateTime createdAt;


    public CommentResponseDto(Comment comment, User user) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.card_id = comment.getCard().getId();
        this.username = user.getUsername();
        this.createdAt = comment.getCreatedAt();
    }
}
