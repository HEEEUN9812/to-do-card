package com.sparta.todocard.service;


import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Todo;
import com.sparta.todocard.entity.Comment;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;


    public CommentResponseDto addComment(CommentRequestDto requestDto, Todo todo, User user) {
        Comment comment = commentRepository.save(new Comment(requestDto, todo, user));
        return new CommentResponseDto(comment, user);
    }

    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto requestDto, Todo todo, Long commentId,
        User user) {
        verifyUser(user, commentId);
        Comment comment = commentRepository.findByCardIdAndId(todo.getId(), commentId)
            .orElseThrow(() -> new NullPointerException("해당 댓글은 존재하지 않습니다."));
        comment.update(requestDto);
        return new CommentResponseDto(comment, user);
    }

    @Transactional
    public Long deleteComment(Todo todo, Long commentId, User user) {
        verifyUser(user, commentId);
        Comment comment = commentRepository.findByCardIdAndId(todo.getId(), commentId)
            .orElseThrow(() -> new NullPointerException("해당 댓글은 존재하지 않습니다."));
        commentRepository.delete(comment);
        return commentId;
    }

    public void verifyUser(User user, Long id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new NullPointerException("해당 댓글이 존재하지 않습니다."));
        if (!user.getId().equals(comment.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자만 삭제/수정 할 수 있음");
        }
    }

}
