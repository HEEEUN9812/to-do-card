package com.sparta.todocard.repository;

import com.sparta.todocard.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<Card, Long> {
}
