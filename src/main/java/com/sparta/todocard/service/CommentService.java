package com.sparta.todocard.service;


import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.entity.Comment;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;


    public CommentResponseDto addComment(CommentRequestDto requestDto, Card card, User user) {
        Comment comment = commentRepository.save(new Comment(requestDto, card, user));
        return new CommentResponseDto(comment, user);
    }

    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto requestDto, Card card, Long commentId, User user) {
        verifyUser(user, card);
        List<Comment> commentList = commentRepository.findAllByCard(card);
        for (Comment comment : commentList) {
            if (comment.getId().equals(commentId)) {
                comment.update(requestDto);
                return new CommentResponseDto(comment, card.getUser());
            }
        }
        return null;
    }

    @Transactional
    public Long deleteComment(Card card, Long commentId, User user) {
        verifyUser(user, card);
        List<Comment> commentList = commentRepository.findAllByCard(card);
        for (Comment comment : commentList) {
            if (comment.getId().equals(commentId)) {
                commentRepository.delete(comment);
            }
        }
        return commentId;
    }

    public void verifyUser(User user, Card card){
        if(!user.getId().equals(card.getUser().getId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"작성자만 삭제/수정 할 수 있음");
        }
    }

}
