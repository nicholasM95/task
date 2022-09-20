package com.example.task.todo.mapper;


import com.example.task.todo.model.Todo;
import com.example.task.todo.resource.TodoWebRequestResource;
import com.example.task.todo.resource.TodoWebResponseResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoMapperImpl implements TodoMapper{
    @Override
    public Todo resourceToModel(final TodoWebRequestResource resource) {
        Todo todo = new Todo();
        todo.setTask(resource.getTask());
        return todo;
    }

    @Override
    public TodoWebResponseResource modelToResource(final Todo todo) {
        TodoWebResponseResource resource = new TodoWebResponseResource();
        resource.setId(todo.getId());
        resource.setTask(todo.getTask());
        return resource;
    }

    @Override
    public List<TodoWebResponseResource> modelToResource(final List<Todo> todos) {
        List<TodoWebResponseResource> resources = new ArrayList<>();
        for(Todo todo : todos) {
            resources.add(modelToResource(todo));
        }
        return resources;
    }
}
