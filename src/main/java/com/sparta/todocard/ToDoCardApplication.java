package com.sparta.todocard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ToDoCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoCardApplication.class, args);
    }

}
