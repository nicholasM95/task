package com.example.task.todo.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_profile")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;
    @Column(name = "user_name")
    private String name;

    @Column(name = "banned")
    private boolean banned;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
