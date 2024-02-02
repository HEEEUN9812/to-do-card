package com.sparta.todocard.entity;


import com.sparta.todocard.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    public Comment(CommentRequestDto requestDto, Card card) {
        this.content = requestDto.getContent();
        this.card = card;
    }

}
