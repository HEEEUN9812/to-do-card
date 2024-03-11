package com.sparta.todocard.repository;

import com.sparta.todocard.entity.Todo;
import com.sparta.todocard.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByCard(Todo todo);

    Optional<Comment> findByCardIdAndId(Long id, Long commentId);

}
