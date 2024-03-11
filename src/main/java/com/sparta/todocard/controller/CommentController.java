package com.sparta.todocard.controller;

import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.security.UserDetailsImpl;
import com.sparta.todocard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;

    @PostMapping("/{todoId}/comment")
    public ResponseEntity<CommentResponseDto> createComment(
        @PathVariable Long todoId,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.createComment(todoId,
            userDetails.getUser(), commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
    }

    @PutMapping("/{todoId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
        @PathVariable Long todoId,
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.updateComment(todoId, commentId,
            commentRequestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
    }

    @DeleteMapping("/{todoId}/comments/{commentId}")
    public ResponseEntity<Long> deleteComment(
        @PathVariable Long todoId,
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long id = commentService.deleteComment(todoId, commentId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(commentId);
    }
}
