package com.sparta.todocard.repository;


import static com.sparta.todocard.entity.QTodo.todo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.todocard.entity.Todo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Todo> findByKeyword(String keyword) {

        return jpaQueryFactory
            .selectFrom(todo)
            .where(
                todo.title.contains(keyword)
                    .or(todo.content.contains(keyword))
                    .or(todo.user.username.contains(keyword))
            )
            .fetch();
    }
}
