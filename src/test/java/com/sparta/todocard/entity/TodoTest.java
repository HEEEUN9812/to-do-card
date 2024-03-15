package com.sparta.todocard.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TodoTest {

    Todo todo = new Todo();

    @Test
    @DisplayName("Card 업데이트")
    void update() {
        //given
        String title = "title1";
        String content = "content1";

        //when
        todo.update(title, content);

        //then
        assertEquals(title, todo.getTitle());
        assertEquals(content, todo.getContent());

    }

    @Test
    @DisplayName("완료 버튼")
    void complete() {
        // given
        boolean complete = false;

        //when
        todo.complete();

        //then
        assertEquals(!complete, todo.isComplete());
    }
}