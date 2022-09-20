package com.example.task.todo.data;

import com.example.task.todo.model.Todo;
import com.example.task.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {
    List<Todo> findAllByUser(User user);
}
