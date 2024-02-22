package com.sparta.todocard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sparta.todocard.dto.CardCommentResponseDto;
import com.sparta.todocard.dto.CardRequestDto;
import com.sparta.todocard.dto.CardResponseDto;
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
public class CardServiceIntegrationTest {

    @Autowired
    CardService cardService;
    @Autowired
    UserRepository userRepository;
    User user;

    CardResponseDto cardResponseDto = null;

    @Test
    @Order(1)
    @DisplayName("todo 카드 신규 생성")
    void test1() {
        //given
        CardRequestDto cardRequestDto = new CardRequestDto("title", "content");
        user = userRepository.findById(1L).orElse(null);

        //when
        CardResponseDto cardResponseDto = cardService.createCard(cardRequestDto, user);

        //then
        assertNotNull(cardResponseDto.getId());
        assertEquals(cardRequestDto.getTitle(), cardResponseDto.getTitle());
        assertEquals(cardRequestDto.getContent(), cardResponseDto.getContent());
        this.cardResponseDto = cardResponseDto;
    }

    @Test
    @Order(2)
    @DisplayName("생성한 todo 카드 내용 변경")
    void test2() {
        //given
        Long cardId = this.cardResponseDto.getId();
        CardRequestDto cardRequestDto = new CardRequestDto("title 수정", "content 수정");

        //when
        cardResponseDto = cardService.updateCard(cardId, cardRequestDto, user);

        //then
        assertNotNull(cardResponseDto.getId());
        assertEquals(cardRequestDto.getTitle(), cardResponseDto.getTitle());
        assertEquals(cardRequestDto.getContent(), cardResponseDto.getContent());
    }

    @Test
    @Order(3)
    @DisplayName("생성한 todo 카드 조회")
    void test3() {
        //given
        Long cardId = cardResponseDto.getId();

        //when
        CardCommentResponseDto cardCommentResponseDto = cardService.getCard(cardId);

        //then
        assertEquals(this.cardResponseDto.getId(), cardCommentResponseDto.getId());
        assertEquals(this.cardResponseDto.getTitle(), cardCommentResponseDto.getTitle());
        assertEquals(this.cardResponseDto.getContent(), cardCommentResponseDto.getContent());

    }

    @Test
    @Order(4)
    @DisplayName("생성한 todo 카드 삭제")
    void test4() {
        //given
        Long cardId = cardResponseDto.getId();

        //when
        Long resultId = cardService.deleteCard(cardId, user);

        //then
        assertEquals(cardId, resultId);
    }
}
