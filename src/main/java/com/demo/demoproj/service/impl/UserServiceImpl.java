package com.demo.demoproj.service.impl;

import java.util.Objects;

import com.demo.demoproj.repository.model.UserItem;
import com.demo.demoproj.service.exception.UserNotFoundException;
import com.demo.demoproj.service.model.UserItemDTO;
import com.demo.demoproj.repository.UserRepository;
import com.demo.demoproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConversionService conversionService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @Override
    public Flux<UserItemDTO> findAll() {
        return userRepository.findAll()
                .mapNotNull(s -> conversionService.convert(s, UserItemDTO.class));
    }

    @Override
    public Mono<UserItemDTO> findByPassword(String password) {
        return userRepository.findByPassword(password)
                .map(s -> conversionService.convert(s, UserItemDTO.class));
    }

    @Override
    public Mono<UserItemDTO> findById(String id) {
        return userRepository.findById(id)
                .mapNotNull(s -> conversionService.convert(s, UserItemDTO.class));
    }

    @Override
    public Mono<UserItemDTO> save(UserItemDTO userItemDTO) {
        return Mono.just(Objects.requireNonNull(conversionService.convert(
                userRepository.save(conversionService.convert(userItemDTO, UserItem.class)),
                UserItemDTO.class)));

    }

    @Override
    public Mono<UserItemDTO> update(String id, UserItemDTO userItemDTO) {
        return userRepository.findById(id)
                .flatMap(s -> {
                    s.setUsername(userItemDTO.getUsername());
                    s.setPassword(userItemDTO.getPassword());
                    return Mono.just(Objects.requireNonNull(
                            conversionService.convert(userRepository.save(s), UserItemDTO.class)));
                });
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAll() {
        return userRepository.deleteAll();
    }

    @Override
    public Mono<UserItemDTO> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(UserNotFoundException::new))
                .mapNotNull(s -> conversionService.convert(s, UserItemDTO.class));
    }
}