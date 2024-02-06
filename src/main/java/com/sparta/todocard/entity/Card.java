package com.sparta.todocard.entity;

import com.sparta.todocard.dto.CardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Table(name = "to-do card")
@NoArgsConstructor
public class Card extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "complete", nullable = false)
    private boolean complete;

    public Card(CardRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
    }

    public void update(CardRequestDto requestDto) {
        if(requestDto.getTitle() != null){
            this.title = requestDto.getTitle();
        }
        if(requestDto.getContent() != null){
            this.content = requestDto.getContent();
        }
        if(!complete){
            this.complete = requestDto.isComplete();
        }
    }
}
