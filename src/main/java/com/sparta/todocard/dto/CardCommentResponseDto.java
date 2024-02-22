package com.sparta.todocard.dto;

import com.sparta.todocard.entity.Card;
import com.sparta.todocard.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@NoArgsConstructor
public class CardCommentResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentList;

    public CardCommentResponseDto(Card card, User user, List<CommentResponseDto> commentList) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.username = user.getUsername();
        this.createdAt = card.getCreatedAt();
        this.commentList = commentList;
    }
}
