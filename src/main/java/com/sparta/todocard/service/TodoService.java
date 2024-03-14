package com.sparta.todocard.service;


import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.dto.TodoCommentResponseDto;
import com.sparta.todocard.dto.TodoRequestDto;
import com.sparta.todocard.dto.TodoResponseDto;
import com.sparta.todocard.entity.Todo;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.CommentRepository;
import com.sparta.todocard.repository.TodoQueryRepository;
import com.sparta.todocard.repository.TodoRepository;
import jakarta.validation.ValidationException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;
    private final TodoQueryRepository todoQueryRepository;

    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto requestDto, User user) {
        Todo todo = todoRepository.save(new Todo(requestDto, user));

        return new TodoResponseDto(todo, user);
    }

    public List<TodoCommentResponseDto> getTodoList() {
        return todoRepository.findAll().stream().map(e -> new TodoCommentResponseDto(e,
            commentRepository.findAllByTodo(e).stream()
                .map(f -> new CommentResponseDto()).toList())).toList();
    }

    public TodoCommentResponseDto getTodo(Long id) {
        Todo todo = findTodo(id);
        List<CommentResponseDto> commentList = commentRepository.findAllByTodo(todo).stream()
            .map(e -> new CommentResponseDto()).toList();
        return new TodoCommentResponseDto(todo, commentList);
    }


    @Transactional
    public TodoResponseDto updateCard(Long id, TodoRequestDto requestDto, User user) {
        Todo todo = findTodo(id);
        validateUser(user, todo);
        todo.update(requestDto.getTitle(), requestDto.getContent());
        return new TodoResponseDto(todo, user);
    }

    @Transactional
    public Long deleteCard(Long id, User user) {
        Todo todo = findTodo(id);
        validateUser(user, todo);
        todoRepository.delete(todo);
        return id;
    }

    @Transactional
    public TodoResponseDto completeTodo(Long id, User user) {
        Todo todo = findTodo(id);
        todo.complete();
        return new TodoResponseDto(todo, user);
    }


    public Todo findTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(
            () -> new NullPointerException("해당 게시물을 찾을 수 없습니다."));
    }

    public void validateUser(User user, Todo todo) {
        if (!user.getId().equals(todo.getUser().getId())) {
            throw new ValidationException("작성자만 수정/삭제 할 수 있습니다.");
        }
    }

    public List<TodoCommentResponseDto> searchTodo(String keyword) {
        List<Todo> todos = todoQueryRepository.findByKeyword(keyword);
        return todos.stream().map(e -> new TodoCommentResponseDto(e,
            commentRepository.findAllByTodo(e).stream()
                .map(f -> new CommentResponseDto()).toList())).toList();
    }
}
