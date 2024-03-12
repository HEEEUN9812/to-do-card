package com.sparta.todocard.service;


import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Todo;
import com.sparta.todocard.entity.Comment;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.CommentRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoService todoService;


    public CommentResponseDto createComment(Long todoId, User user, CommentRequestDto requestDto) {
        Todo todo = todoService.findTodo(todoId);
        Comment comment = commentRepository.save(new Comment(requestDto, todo, user));
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long todoId, Long commentId, CommentRequestDto commentRequestDto, User user) {
        validateUser(user, commentId);
        Comment comment = commentRepository.findByTodoIdAndId(todoId, commentId)
            .orElseThrow(() -> new NullPointerException("해당 댓글은 존재하지 않습니다."));
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public Long deleteComment(Long todoId, Long commentId, User user) {
        validateUser(user, commentId);
        Comment comment = commentRepository.findByTodoIdAndId(todoId, commentId)
            .orElseThrow(() -> new NullPointerException("해당 댓글은 존재하지 않습니다."));
        commentRepository.delete(comment);
        return commentId;
    }

    public void validateUser(User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new NullPointerException("해당 댓글이 존재하지 않습니다."));
        if (!user.getId().equals(comment.getUser().getId())) {
            throw new ValidationException("작성자만 수정/삭제 할 수 있습니다.");
        }
    }
}
