package com.sparta.todocard.service;


import com.sparta.todocard.dto.TodoCommentResponseDto;
import com.sparta.todocard.dto.TodoRequestDto;
import com.sparta.todocard.dto.TodoResponseDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Todo;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.TodoRepository;
import com.sparta.todocard.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;


    public TodoResponseDto createTodo(TodoRequestDto requestDto, User user) {
        Todo todo = todoRepository.save(new Todo(requestDto, user));

        return new TodoResponseDto(todo, user);
    }

    public List<TodoCommentResponseDto> getTodoList() {
        return todoRepository.findAll().stream().map(e -> new TodoCommentResponseDto(e,
            commentRepository.findAllByCard(e).stream()
                .map(f -> new CommentResponseDto(f, f.getUser())).toList())).toList();
    }

    public TodoCommentResponseDto getTodo(Long id) {
        Todo todo = findCard(id);
        List<CommentResponseDto> commentList = commentRepository.findAllByCard(todo).stream()
            .map(e -> new CommentResponseDto(e, e.getUser())).toList();
        return new TodoCommentResponseDto(todo, commentList);
    }


    @Transactional
    public TodoResponseDto updateCard(Long id, TodoRequestDto requestDto, User user) {
        Todo todo = findCard(id);
        verifyUser(user, todo);
        todo.update(requestDto.getTitle(), requestDto.getContent());
        return new TodoResponseDto(todo, user);
    }

    @Transactional
    public Long deleteCard(Long id, User user) {
        Todo todo = findCard(id);
        verifyUser(user, todo);
        todoRepository.delete(todo);
        return id;
    }

    @Transactional
    public TodoResponseDto completeTodo(Long id, User user) {
        Todo todo = findCard(id);
        todo.complete();
        return new TodoResponseDto(todo, user);
    }


    public Todo findCard(Long id) {
        return todoRepository.findById(id).orElseThrow(
            () -> new NullPointerException("no " + id));
    }

    public void verifyUser(User user, Todo todo) {
        if (!user.getId().equals(todo.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자만 삭제/수정 할 수 있음");
        }
    }
}
