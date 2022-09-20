package com.example.task.todo.mapper;



import com.example.task.todo.model.Todo;
import com.example.task.todo.resource.TodoWebRequestResource;
import com.example.task.todo.resource.TodoWebResponseResource;

import java.util.List;

public interface TodoMapper {
    Todo resourceToModel(final TodoWebRequestResource resource);
    TodoWebResponseResource modelToResource(final Todo todo);

    List<TodoWebResponseResource> modelToResource(final List<Todo> todo);
}
