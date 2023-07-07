package com.demo.demoproj.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class UserItem {

    @Id
    private String id;

    private String username;

    private String password;


    public UserItem() {
        this.password = "";
    }

    public UserItem(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserItem(String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    // getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

    public void setId(String id) {
        this.id = id;
    }
}