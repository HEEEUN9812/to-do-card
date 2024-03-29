package com.sparta.todocard.service;

import com.sparta.todocard.dto.TodoCommentResponseDto;
import com.sparta.todocard.dto.TodoRequestDto;
import com.sparta.todocard.dto.TodoResponseDto;
import com.sparta.todocard.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TodoService {

    TodoResponseDto createTodo(TodoRequestDto requestDto, User user);

    List<TodoCommentResponseDto> getTodoList();

    TodoCommentResponseDto getTodo(Long id);

    List<TodoResponseDto> searchTodo(String keyword, Pageable pageable);

    TodoResponseDto updateCard(Long id, TodoRequestDto requestDto, User user);

    Long deleteCard(Long id, User user);

    TodoResponseDto completeTodo(Long id, User user);

}
