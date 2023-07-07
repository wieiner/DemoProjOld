package com.demo.demoproj.repository;

import com.demo.demoproj.model.UserItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserItem, String> {
    Flux<UserItem> findByUsername(String username);

    Flux<UserItem> findByPassword(String password);
}