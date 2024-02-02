package com.sparta.todocard.service;


import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.entity.Comment;
import com.sparta.todocard.repository.CommentRepository;
import com.sparta.todocard.repository.ToDoCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final ToDoCardRepository toDoCardRepository;


    public CommentResponseDto addComment(CommentRequestDto requestDto, Card card) {
        Comment comment = commentRepository.save(new Comment(requestDto, card));
        return new CommentResponseDto(comment);
    }
}
