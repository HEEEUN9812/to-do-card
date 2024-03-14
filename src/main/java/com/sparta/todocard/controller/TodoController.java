package com.sparta.todocard.controller;

import com.sparta.todocard.dto.TodoCommentResponseDto;
import com.sparta.todocard.dto.TodoRequestDto;
import com.sparta.todocard.dto.TodoResponseDto;
import com.sparta.todocard.global.security.UserDetailsImpl;
import com.sparta.todocard.service.TodoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoResponseDto> createTodo(
        @RequestBody TodoRequestDto todoRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDto todoResponseDto = todoService.createTodo(todoRequestDto,
            userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(todoResponseDto);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoCommentResponseDto>> getAllTodos() {
        List<TodoCommentResponseDto> todoCommentResponseDtos = todoService.getTodoList();
        return ResponseEntity.status(HttpStatus.OK.value()).body(todoCommentResponseDtos);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodoCommentResponseDto> getTodo(@PathVariable Long id) {
        TodoCommentResponseDto todoCommentResponseDto = todoService.getTodo(id);
        return ResponseEntity.status(HttpStatus.OK.value()).body(todoCommentResponseDto);
    }

    @GetMapping("/todos/search")
    public ResponseEntity<List<TodoResponseDto>> searchTodo(
        @RequestParam String keyword,
        @RequestParam(required = false, defaultValue = "0", value = "page") int page,
        @RequestParam(required = false, defaultValue = "10", value = "size") int size) {
        List<TodoResponseDto> todoCommentResponseDtos = todoService.searchTodo(keyword, page, size );
        return ResponseEntity.status(HttpStatus.OK.value()).body(todoCommentResponseDtos);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
        @PathVariable Long id,
        @RequestBody TodoRequestDto todoRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDto todoResponseDto = todoService.updateCard(id, todoRequestDto,
            userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK.value()).body(todoResponseDto);
    }

    @PatchMapping("/todos/{id}")
    public ResponseEntity<TodoResponseDto> completeTodo(
        @PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDto todoResponseDto = todoService.completeTodo(id, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK.value()).body(todoResponseDto);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Long> deleteTodo(
        @PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long todoId = todoService.deleteCard(id, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK.value()).body(todoId);
    }
}
