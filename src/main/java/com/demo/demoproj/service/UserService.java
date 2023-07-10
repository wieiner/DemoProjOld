package com.demo.demoproj.service;

import com.demo.demoproj.service.model.UserItemDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<UserItemDTO> findAll();

    Mono<UserItemDTO> findByPassword(String password);

    Mono<UserItemDTO> findById(String id);

    Mono<UserItemDTO> save(UserItemDTO userItemDTO);

    Mono<UserItemDTO> update(String id, UserItemDTO userItemDTO);

    Mono<Void> deleteById(String id);

    Mono<Void> deleteAll();

    Mono<UserItemDTO> findByUsername(String username);
}
