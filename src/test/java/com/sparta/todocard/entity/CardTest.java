package com.sparta.todocard.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    Card card = new Card();
    @Test
    @DisplayName("Card 업데이트")
    void update(){
        //given
        String title = "title1";
        String content = "content1";

        //when
        card.update(title,content);

        //then
        assertEquals(title,card.getTitle());
        assertEquals(content,card.getContent());

    }

    @Test
    @DisplayName("완료 버튼")
    void complete() {
        // given
        boolean complete = false;

        //when
        card.complete();

        //then
        assertEquals(!complete,card.isComplete());
    }
}