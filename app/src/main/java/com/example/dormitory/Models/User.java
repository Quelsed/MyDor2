package com.example.dormitory.Models;

public class User {

    private Role role;
    private String email;
    private String id;

    public User(Role role, String email, String id) {
        this.role = role;
        this.email = email;
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public enum Role {
        ADMIN,
        USER
    }
}
