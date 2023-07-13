package com.demo.demoproj.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@AllArgsConstructor
@Getter
@Setter
public class UserItem {
    @Id
    private String id;
    @Field("username")
    private String username;
    @Field("password")
    private String password;
}