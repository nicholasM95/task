package com.example.task.todo.service;


import com.example.task.core.exception.AuthenticationNotFoundException;
import com.example.task.todo.data.TodoRepository;
import com.example.task.todo.exception.TodoNotFoundException;
import com.example.task.todo.model.Todo;
import com.example.task.todo.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo create(Authentication authentication, Todo todo) {
        todo.setId(UUID.randomUUID());
        todo.setUser(getUserFromAuth(authentication));
        return repository.save(todo);
    }

    public List<Todo> findAll(Authentication authentication) {
        return repository.findAllByUser(getUserFromAuth(authentication));
    }

    public Todo findById(UUID id) {
        Optional<Todo> todo = repository.findById(id);
        if (todo.isEmpty()) {
            throw new TodoNotFoundException();
        }
        return todo.get();
    }

    public Todo update(UUID id, Todo todo) {
        Todo original = findById(id);
        original.setTask(todo.getTask());
        return repository.save(original);
    }

    public void delete(UUID id) {
        repository.delete(findById(id));
    }

    private User getUserFromAuth(Authentication authentication) {
        if (authentication != null) {
            Map<String, String> userDetails = (Map<String, String>) authentication.getDetails();

            User user = new User();
            user.setId(UUID.fromString(userDetails.get("id")));
            return user;
        }
        throw new AuthenticationNotFoundException();
    }
}
