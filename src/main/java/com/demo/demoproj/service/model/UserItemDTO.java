package com.demo.demoproj.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


@Getter
@Setter
@AllArgsConstructor
public class UserItemDTO {
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String password;
}