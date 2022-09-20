package com.example.task.todo.web;


import com.example.task.todo.mapper.TodoMapper;
import com.example.task.todo.resource.TodoWebRequestResource;
import com.example.task.todo.resource.TodoWebResponseResource;
import com.example.task.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService service;
    private final TodoMapper mapper;

    public TodoController(TodoService service, TodoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<TodoWebResponseResource> create(Authentication authentication, @RequestBody TodoWebRequestResource resource) {
        TodoWebResponseResource webResponseResource = mapper.modelToResource(service.create(authentication, mapper.resourceToModel(resource)));
        UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/todo/{id}").buildAndExpand(webResponseResource.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(webResponseResource);
    }

    @GetMapping
    public ResponseEntity<List<TodoWebResponseResource>> findAll(Authentication authentication) {
        return ResponseEntity.ok(mapper.modelToResource(service.findAll(authentication)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoWebResponseResource> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(mapper.modelToResource(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoWebResponseResource> updateById(@PathVariable("id") UUID id, @RequestBody TodoWebRequestResource resource) {
        return ResponseEntity.ok(mapper.modelToResource(service.update(id, mapper.resourceToModel(resource))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoWebResponseResource> deleteById(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
