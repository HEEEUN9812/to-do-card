package com.sparta.todocard.entity;


import com.sparta.todocard.dto.CommentRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(CommentRequestDto requestDto, Card card, User user) {
        this.content = requestDto.getContent();
        this.card = card;
        this.user = user;
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
