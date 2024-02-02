package com.sparta.todocard.controller;

import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/to-do")
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;


    @PostMapping("/{id}")
    public CommentResponseDto addComment(@RequestBody CommentRequestDto requestDto, Card card){
        return commentService.addComment(requestDto, card);
    }

    @GetMapping("/{id}/comment")
    public List<CommentResponseDto> getComment(Card card){
        return commentService.getComment(card);
    }
}
