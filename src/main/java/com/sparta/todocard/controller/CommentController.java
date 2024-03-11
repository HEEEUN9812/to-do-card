package com.sparta.todocard.controller;

import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Todo;
import com.sparta.todocard.security.UserDetailsImpl;
import com.sparta.todocard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;


    @PostMapping("/{id}/comment")
    public CommentResponseDto addComment(@RequestBody CommentRequestDto requestDto, Todo todo,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.addComment(requestDto, todo, userDetails.getUser());
    }

    @PutMapping("/{id}/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId,
        @RequestBody CommentRequestDto requestDto, Todo todo,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(requestDto, todo, commentId, userDetails.getUser());
    }

    @DeleteMapping("/{id}/comment/{commentId}")
    public Long deleteComment(Todo todo, @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(todo, commentId, userDetails.getUser());
    }
}
