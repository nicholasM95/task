package com.example.task.todo.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;
    @Column(name = "task")
    private String task;
    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
