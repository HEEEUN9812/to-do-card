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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;
    private final TodoQueryRepository todoQueryRepository;

    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto requestDto, User user) {
        Todo todo = todoRepository.save(new Todo(requestDto, user));

        return new TodoResponseDto(todo, user);
    }

    public List<TodoCommentResponseDto> getTodoList() {
        return todoRepository.findAllByOrderByCompleteAscCreatedAtDesc().stream().map(e -> new TodoCommentResponseDto(e,
            commentRepository.findAllByTodo(e).stream()
                .map(CommentResponseDto::new).toList())).toList();
    }

    public TodoCommentResponseDto getTodo(Long id) {
        Todo todo = findTodo(id);
        List<CommentResponseDto> commentList = commentRepository.findAllByTodo(todo).stream()
            .map(CommentResponseDto::new).toList();
        return new TodoCommentResponseDto(todo, commentList);
    }


    public List<TodoResponseDto> searchTodo(String keyword, Pageable pageable) {
        return todoQueryRepository
            .findByKeywordPageable(keyword, pageable)
            .map(TodoResponseDto::new).getContent();
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
}
