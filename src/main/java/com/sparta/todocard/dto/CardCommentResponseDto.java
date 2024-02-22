package com.sparta.todocard.dto;

import com.sparta.todocard.entity.Card;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CardCommentResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentList;

    public CardCommentResponseDto(Card card, List<CommentResponseDto> commentList) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.username = card.getUser().getUsername();
        this.createdAt = card.getCreatedAt();
        this.commentList = commentList;
    }
}
