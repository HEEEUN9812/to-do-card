package com.sparta.todocard.service;


import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.entity.Comment;
import com.sparta.todocard.repository.CommentRepository;
import com.sparta.todocard.repository.ToDoCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final ToDoCardRepository toDoCardRepository;


    public CommentResponseDto addComment(CommentRequestDto requestDto, Card card) {
        Comment comment = commentRepository.save(new Comment(requestDto, card));
        return new CommentResponseDto(comment);
    }

    public List<CommentResponseDto> getComment(Card card) {
        return commentRepository.findAllByCard(card).stream().map(CommentResponseDto::new).toList();

    }

    @Transactional
    public Long deleteComment(Card card, Long commentId) {
        List<Comment> commentList = commentRepository.findAllByCard(card);
        for (Comment comment : commentList) {
            if (comment.getId().equals(commentId)){
             commentRepository.delete(comment);
            }
        }
        return commentId;
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
