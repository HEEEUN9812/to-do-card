package com.sparta.todocard.service;


import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.entity.Comment;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;


    public CommentResponseDto addComment(CommentRequestDto requestDto, Card card, User user) {
        Comment comment = commentRepository.save(new Comment(requestDto, card, user));
        return new CommentResponseDto(comment, user);
    }

    public List<CommentResponseDto> getComment(Card card) {
        return commentRepository.findAllByCard(card).stream().map(e -> new CommentResponseDto(e, e.getUser())).toList();

    }

    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto requestDto, Card card, Long commentId) {
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
    public Long deleteComment(Card card, Long commentId) {
        List<Comment> commentList = commentRepository.findAllByCard(card);
        for (Comment comment : commentList) {
            if (comment.getId().equals(commentId)) {
                commentRepository.delete(comment);
            }
        }
        return commentId;
    }

    public Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("no " + id));
    }

//    private Comment findComment(Card card, Long id){
//        try{
//         return commentRepository.deleteByCardAndId(card, id);
//        }
//        catch (Exception e){
//         new NullPointerException("no" + id);
//        }
//    }
}
