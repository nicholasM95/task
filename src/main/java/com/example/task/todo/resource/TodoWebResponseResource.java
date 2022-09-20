package com.example.task.todo.resource;

import java.util.UUID;

public class TodoWebResponseResource {
    private UUID id;
    private String task;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
