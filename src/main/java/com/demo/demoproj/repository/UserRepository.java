package com.demo.demoproj.repository;

import com.demo.demoproj.repository.model.UserItem;
import com.demo.demoproj.service.model.UserItemDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserRepository extends ReactiveMongoRepository<UserItem, String> {
    Mono<UserItem> findByUsername(String username);
    Mono<UserItem> findByPassword(String password);
}