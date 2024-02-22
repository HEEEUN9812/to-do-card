package com.sparta.todocard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.sparta.todocard.dto.CardCommentResponseDto;
import com.sparta.todocard.dto.CardRequestDto;
import com.sparta.todocard.dto.CardResponseDto;
import com.sparta.todocard.dto.CommentRequestDto;
import com.sparta.todocard.dto.CommentResponseDto;
import com.sparta.todocard.entity.Card;
import com.sparta.todocard.entity.Comment;
import com.sparta.todocard.entity.User;
import com.sparta.todocard.repository.CardRepository;
import com.sparta.todocard.repository.CommentRepository;
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
class CardServiceTest {

    @Mock
    CardRepository cardRepository;

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CardService cardService;

    @Test
    @DisplayName("todo 카드 생성 성공")
    void createCard() {
        //given
        CardRequestDto cardRequestDto = new CardRequestDto("title", "content");
        User user = new User();
        given(cardRepository.save(any(Card.class))).willAnswer(
            invocation -> invocation.<Card>getArgument(0));
        //when
        CardResponseDto cardResponseDto = cardService.createCard(cardRequestDto, user);

        //then
        assertEquals(cardRequestDto.getTitle(), cardResponseDto.getTitle());
        assertEquals(cardRequestDto.getContent(), cardResponseDto.getContent());
    }

    @Test
    @DisplayName("todo 카드 특정 조회 성공")
    void getCard() {
        //given
        Long cardId = 1L;
        CardRequestDto cardRequestDto = new CardRequestDto("title", "content");
        User user = new User();
        Card card = new Card(cardRequestDto, user);

        List<Comment> comments = new ArrayList<>();
        CommentRequestDto commentRequestDto = new CommentRequestDto("댓글");
        Comment comment = new Comment(commentRequestDto, card, user);
        comments.add(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment, user);

        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        commentResponseDtos.add(commentResponseDto);

        given(cardRepository.findById(cardId)).willReturn(Optional.of(card));
        given(commentRepository.findAllByCard(card)).willReturn(comments);

        //when
        CardCommentResponseDto cardCommentResponseDto = cardService.getCard(cardId);

        //then
        assertEquals(cardRequestDto.getTitle(), cardCommentResponseDto.getTitle());
        assertEquals(cardRequestDto.getContent(), cardCommentResponseDto.getContent());
        assertEquals(commentResponseDtos.get(0).getContent(),
            cardCommentResponseDto.getCommentList().get(0).getContent());
    }

    @Test
    @DisplayName("todo 카드 업데이트 성공")
    void updateCard() {
        //given
        Long cardId = 1L;
        CardRequestDto cardRequestDto = new CardRequestDto("title", "content");
        User user = new User();
        user.setId(1L);
        CardRequestDto cardRequestDtoChange = new CardRequestDto("title 수정", "content 수정");
        Card card = new Card(cardRequestDto, user);

        given(cardRepository.findById(cardId)).willReturn(Optional.of(card));

        //when
        CardResponseDto cardResponseDto = cardService.updateCard(cardId, cardRequestDtoChange,
            user);

        //then
        assertEquals(cardRequestDtoChange.getTitle(), cardResponseDto.getTitle());
        assertEquals(cardRequestDtoChange.getContent(), cardResponseDto.getContent());
    }

    @Test
    @DisplayName("todo 카드 삭제 성공")
    void deleteCard() {
        //given
        Long cardId = 1L;
        CardRequestDto cardRequestDto = new CardRequestDto("title", "content");
        User user = new User();
        user.setId(1L);
        Card card = new Card(cardRequestDto, user);

        given(cardRepository.findById(cardId)).willReturn(Optional.of(card));

        //when
        Long resultId = cardService.deleteCard(cardId, user);

        //then
        assertEquals(cardId, resultId);
    }

    @Test
    @DisplayName("todo 카드 완료 버튼 성공")
    void complete() {
        //given
        Long cardId = 1L;
        CardRequestDto cardRequestDto = new CardRequestDto("title", "content");
        User user = new User();
        user.setId(1L);
        Card card = new Card(cardRequestDto, user);

        given(cardRepository.findById(cardId)).willReturn(Optional.of(card));

        //when
        CardResponseDto cardResponseDto = cardService.complete(cardId, user);

        //then
        assertEquals(cardRequestDto.getTitle(), cardResponseDto.getTitle());
        assertEquals(cardRequestDto.getContent(), cardResponseDto.getContent());
    }
}