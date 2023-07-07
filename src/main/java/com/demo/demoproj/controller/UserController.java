package com.demo.demoproj.controller;

import com.demo.demoproj.service.impl.UserService;
import com.demo.demoproj.model.UserItem;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.DeleteMapping;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.ResponseStatus;
        import org.springframework.web.bind.annotation.RestController;


        import reactor.core.publisher.Flux;
        import reactor.core.publisher.Mono;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
//@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Flux<UserItem> getAllUsers(@RequestParam(required = false) String username) {
        if (username == null)
            return userService.findAll();
        else
            return userService.findByUsername(username);
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserItem> getUserById(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserItem> createUserItem(@RequestBody UserItem userItem) {
        return userService.save(new UserItem(userItem.getUsername(), userItem.getPassword()));
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserItem> updateUserItem(@PathVariable("id") String id, @RequestBody UserItem userItem) {
        return userService.update(id, userItem);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteUserItem(@PathVariable("id") String id) {
        return userService.deleteById(id);
    }

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllUsers() {
        return userService.deleteAll();
    }

    @GetMapping("/users/nopswrds/")
    @ResponseStatus(HttpStatus.OK)
    public Flux<UserItem> findByPasswords() {
        return userService.findByPassword("");
    }
}
