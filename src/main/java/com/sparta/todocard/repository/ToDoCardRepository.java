package com.sparta.todocard.repository;

import com.sparta.todocard.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoCardRepository extends JpaRepository<Card, Long> {
}
