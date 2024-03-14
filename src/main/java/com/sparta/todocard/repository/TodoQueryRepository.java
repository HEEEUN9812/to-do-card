package com.sparta.todocard.repository;


import static com.sparta.todocard.entity.QTodo.todo;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.todocard.entity.Todo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<Todo> findByKeywordPageable(String keyword, Pageable pageable) {
        List<Todo> todos = jpaQueryFactory
            .selectFrom(todo)
            .where(
                todo.title.contains(keyword)
                    .or(todo.content.contains(keyword))
                    .or(todo.user.username.contains(keyword))
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(todo.createdAt.desc())
            .fetch();

        Long totalSize = jpaQueryFactory
            .select(Wildcard.count)
            .where(
                todo.title.contains(keyword)
                    .or(todo.content.contains(keyword))
                    .or(todo.user.username.contains(keyword))
            )
            .from(todo)
            .fetch().get(0);

        return PageableExecutionUtils.getPage(todos, pageable, () -> totalSize);
    }
}
