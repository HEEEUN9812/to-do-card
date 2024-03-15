package com.sparta.todocard.global.aop;

import com.sparta.todocard.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUseTimeRepository extends JpaRepository<ApiUseTime, Long> {

    Optional<ApiUseTime> findByUser(User user);
}
