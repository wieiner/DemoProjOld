package com.demo.demoproj.service;

import java.util.Optional;
import com.demo.demoproj.model.UserItem;
import com.demo.demoproj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public Flux<UserItem> findAll() {
        return userRepository.findAll();
    }

    public Flux<UserItem> findByPassword(String password) {
        return userRepository.findByPassword(password);
    }

    public Mono<UserItem> findById(String id) {
        return userRepository.findById(id);
    }

    public Mono<UserItem> save(UserItem userItem) {
        return userRepository.save(userItem);
    }

    public Mono<UserItem> update(String id, UserItem userItem) {
        return userRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalUserItem -> {
                    if (optionalUserItem.isPresent()) {
                        userItem.setId(id);
                        return userRepository.save(userItem);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(String id) {
        return userRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return userRepository.deleteAll();
    }

    public Flux<UserItem> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}