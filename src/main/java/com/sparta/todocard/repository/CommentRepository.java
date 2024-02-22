package com.sparta.todocard.repository;

import com.sparta.todocard.entity.Card;
import com.sparta.todocard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCard(Card card);

    Optional<Comment> findByCardIdAndId(Long id, Long commentId);

}
