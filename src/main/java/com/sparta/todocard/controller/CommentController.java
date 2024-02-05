package com.sparta.todocard.controller;

import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.security.UserDetailsImpl;
import com.sparta.todocard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/to-do")
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;


    @PostMapping("/{id}/comment")
    public CommentResponseDto addComment(@RequestBody CommentRequestDto requestDto, Card card, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.addComment(requestDto, card, userDetails.getUser());
    }

    @PutMapping("/{id}/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId ,@RequestBody CommentRequestDto requestDto, Card card){
        return commentService.updateComment(requestDto, card, commentId);
    }

    @DeleteMapping("/{id}/comment/{commentId}")
    public Long deleteComment(Card card, @PathVariable Long commentId){
        return commentService.deleteComment(card, commentId);
    }
}
