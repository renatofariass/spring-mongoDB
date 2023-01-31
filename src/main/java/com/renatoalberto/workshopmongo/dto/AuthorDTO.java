package com.renatoalberto.workshopmongo.dto;

import com.renatoalberto.workshopmongo.domain.User;

import java.io.Serializable;

public class AuthorDTO implements Serializable {
    private static final Long versionUID = 1L;

    private String id;
    private String name;

    public AuthorDTO() {

    }

    public AuthorDTO(User author) {
        id = author.getId();
        name = author.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
