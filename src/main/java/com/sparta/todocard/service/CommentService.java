package com.sparta.todocard.service;

import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.User;

public interface CommentService {

    CommentResponseDto createComment(Long todoId, User user, CommentRequestDto requestDto);

    CommentResponseDto updateComment(Long todoId, Long commentId,
        CommentRequestDto commentRequestDto, User user);

    Long deleteComment(Long todoId, Long commentId, User user);

}
