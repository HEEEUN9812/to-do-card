package com.sparta.todocard.repository;

import com.sparta.todocard.entity.Todo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    Optional<Todo> findById(Long id);

    List<Todo> findAllByOrderByCompleteAscCreatedAtDesc();
}
