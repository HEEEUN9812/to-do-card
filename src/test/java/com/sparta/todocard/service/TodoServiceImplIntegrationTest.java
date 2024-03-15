package com.sparta.todocard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sparta.todocard.dto.TodoCommentResponseDto;
import com.sparta.todocard.dto.TodoRequestDto;
import com.sparta.todocard.dto.TodoResponseDto;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodoServiceImplIntegrationTest {

    @Autowired
    TodoServiceImpl todoServiceImpl;
    @Autowired
    UserRepository userRepository;
    User user;

    TodoResponseDto todoResponseDto = null;

    @Test
    @Order(1)
    @DisplayName("todo 카드 신규 생성")
    void test1() {
        //given
        TodoRequestDto todoRequestDto = new TodoRequestDto("title", "content");
        user = userRepository.findById(1L).orElse(null);

        //when
        TodoResponseDto todoResponseDto = todoServiceImpl.createTodo(todoRequestDto, user);

        //then
        assertNotNull(todoResponseDto.getId());
        assertEquals(todoRequestDto.getTitle(), todoResponseDto.getTitle());
        assertEquals(todoRequestDto.getContent(), todoResponseDto.getContent());
        this.todoResponseDto = todoResponseDto;
    }

    @Test
    @Order(2)
    @DisplayName("생성한 todo 카드 내용 변경")
    void test2() {
        //given
        Long cardId = this.todoResponseDto.getId();
        TodoRequestDto todoRequestDto = new TodoRequestDto("title 수정", "content 수정");

        //when
        todoResponseDto = todoServiceImpl.updateCard(cardId, todoRequestDto, user);

        //then
        assertNotNull(todoResponseDto.getId());
        assertEquals(todoRequestDto.getTitle(), todoResponseDto.getTitle());
        assertEquals(todoRequestDto.getContent(), todoResponseDto.getContent());
    }

    @Test
    @Order(3)
    @DisplayName("생성한 todo 카드 조회")
    void test3() {
        //given
        Long cardId = todoResponseDto.getId();

        //when
        TodoCommentResponseDto todoCommentResponseDto = todoServiceImpl.getTodo(cardId);

        //then
        assertEquals(this.todoResponseDto.getId(), todoCommentResponseDto.getId());
        assertEquals(this.todoResponseDto.getTitle(), todoCommentResponseDto.getTitle());
        assertEquals(this.todoResponseDto.getContent(), todoCommentResponseDto.getContent());

    }

    @Test
    @Order(4)
    @DisplayName("생성한 todo 카드 삭제")
    void test4() {
        //given
        Long cardId = todoResponseDto.getId();

        //when
        Long resultId = todoServiceImpl.deleteCard(cardId, user);

        //then
        assertEquals(cardId, resultId);
    }
}
