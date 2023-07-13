package com.demo.demoproj.controller;

import com.demo.demoproj.service.impl.UserServiceImpl;
import com.demo.demoproj.service.model.UserItemDTO;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;


@RestController
@Log4j2
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users")
    public Flux<UserItemDTO> getAllUsers() {
        return userServiceImpl.findAll();
    }

    @GetMapping("/users/{username}")
    public Mono<UserItemDTO> findUsersByName(@PathVariable String username, Principal principal) {
        log.info("Request to find users by name details {}, from the user {}", username, principal);
        return userServiceImpl.findByUsername(username);
    }

    @GetMapping("/users/byid/{id}")
    public Mono<UserItemDTO> getUserById(@PathVariable("id") String id) {
        return userServiceImpl.findById(id);
    }

    @PostMapping("/users")
    public Mono<UserItemDTO> createUserItem(@RequestBody UserItemDTO userItemDTO) {
        return userServiceImpl.save(userItemDTO);
    }

    @PutMapping("/users/{id}")
    public Mono<UserItemDTO> updateUserItem(@PathVariable("id") String id, @RequestBody UserItemDTO userItemDTO) {
        return userServiceImpl.update(id, userItemDTO);
    }

    @DeleteMapping("/users/{id}")
    public Mono<Void> deleteUserItem(@PathVariable("id") String id) {
        return userServiceImpl.deleteById(id);
    }

    @DeleteMapping("/users")
    public Mono<Void> deleteAllUsers() {
        return userServiceImpl.deleteAll();
    }

    @GetMapping("/users/nopswrds/")
    public Mono<UserItemDTO> findByPasswords() {
        return userServiceImpl.findByPassword("");
    }
}
