package com.sparta.todocard.repository;

import com.sparta.todocard.entity.Comment;
import com.sparta.todocard.entity.Todo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByTodo(Todo todo);

    Optional<Comment> findByTodoIdAndId(Long todoId, Long commentId);

}
