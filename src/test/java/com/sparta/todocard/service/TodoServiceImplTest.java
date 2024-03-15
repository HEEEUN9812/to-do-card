package com.sparta.todocard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.dto.TodoCommentResponseDto;
import com.sparta.todocard.dto.TodoRequestDto;
import com.sparta.todocard.dto.TodoResponseDto;
import com.sparta.todocard.entity.Comment;
import com.sparta.todocard.entity.Todo;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.CommentRepository;
import com.sparta.todocard.repository.TodoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {

    @Mock
    TodoRepository todoRepository;

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    TodoService todoService;

    @Test
    @DisplayName("todo 카드 생성 성공")
    void createCard() {
        //given
        TodoRequestDto todoRequestDto = new TodoRequestDto("title", "content");
        User user = new User();
        given(todoRepository.save(any(Todo.class))).willAnswer(
            invocation -> invocation.<Todo>getArgument(0));
        //when
        TodoResponseDto todoResponseDto = todoService.createTodo(todoRequestDto, user);

        //then
        assertEquals(todoRequestDto.getTitle(), todoResponseDto.getTitle());
        assertEquals(todoRequestDto.getContent(), todoResponseDto.getContent());
    }

    @Test
    @DisplayName("todo 카드 특정 조회 성공")
    void getCard() {
        //given
        Long cardId = 1L;
        TodoRequestDto todoRequestDto = new TodoRequestDto("title", "content");
        User user = new User();
        Todo todo = new Todo(todoRequestDto, user);

        List<Comment> comments = new ArrayList<>();
        CommentRequestDto commentRequestDto = new CommentRequestDto("댓글");
        Comment comment = new Comment(commentRequestDto, todo, user);
        comments.add(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        commentResponseDtos.add(commentResponseDto);

        given(todoRepository.findById(cardId)).willReturn(Optional.of(todo));
        given(commentRepository.findAllByTodo(todo)).willReturn(comments);

        //when
        TodoCommentResponseDto todoCommentResponseDto = todoService.getTodo(cardId);

        //then
        assertEquals(todoRequestDto.getTitle(), todoCommentResponseDto.getTitle());
        assertEquals(todoRequestDto.getContent(), todoCommentResponseDto.getContent());
        assertEquals(commentResponseDtos.get(0).getContent(),
            todoCommentResponseDto.getCommentList().get(0).getContent());
    }

    @Test
    @DisplayName("todo 카드 업데이트 성공")
    void updateCard() {
        //given
        Long cardId = 1L;
        TodoRequestDto todoRequestDto = new TodoRequestDto("title", "content");
        User user = new User();
        user.setId(1L);
        TodoRequestDto todoRequestDtoChange = new TodoRequestDto("title 수정", "content 수정");
        Todo todo = new Todo(todoRequestDto, user);

        given(todoRepository.findById(cardId)).willReturn(Optional.of(todo));

        //when
        TodoResponseDto todoResponseDto = todoService.updateCard(cardId, todoRequestDtoChange,
            user);

        //then
        assertEquals(todoRequestDtoChange.getTitle(), todoResponseDto.getTitle());
        assertEquals(todoRequestDtoChange.getContent(), todoResponseDto.getContent());
    }

    @Test
    @DisplayName("todo 카드 삭제 성공")
    void deleteCard() {
        //given
        Long cardId = 1L;
        TodoRequestDto todoRequestDto = new TodoRequestDto("title", "content");
        User user = new User();
        user.setId(1L);
        Todo todo = new Todo(todoRequestDto, user);

        given(todoRepository.findById(cardId)).willReturn(Optional.of(todo));

        //when
        Long resultId = todoService.deleteCard(cardId, user);

        //then
        assertEquals(cardId, resultId);
    }

    @Test
    @DisplayName("todo 카드 완료 버튼 성공")
    void complete() {
        //given
        Long cardId = 1L;
        TodoRequestDto todoRequestDto = new TodoRequestDto("title", "content");
        User user = new User();
        user.setId(1L);
        Todo todo = new Todo(todoRequestDto, user);

        given(todoRepository.findById(cardId)).willReturn(Optional.of(todo));

        //when
        TodoResponseDto todoResponseDto = todoService.completeTodo(cardId, user);

        //then
        assertEquals(todoRequestDto.getTitle(), todoResponseDto.getTitle());
        assertEquals(todoRequestDto.getContent(), todoResponseDto.getContent());
    }
}