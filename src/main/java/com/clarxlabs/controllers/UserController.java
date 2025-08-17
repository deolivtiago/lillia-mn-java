package com.clarxlabs.controllers;

import com.clarxlabs.entities.User;
import com.clarxlabs.repositories.UserRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Validated
@Controller("/users")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Get
    @Status(HttpStatus.OK)
    public Mono<Page<User>> index(final @Valid @NonNull Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Mono<User> create(final @Body @Valid @NonNull User input) {
        final var user = User.builder()
                .email(input.email().toLowerCase())
                .avatarUrl(input.avatarUrl())
                .fullName(input.fullName())
                .password(input.password())
                .build();

        return userRepository.save(input);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Mono<User> show(final @Valid @NonNull UUID id) {
        return userRepository.findById(id);
    }

    @Put("/{id}")
    public Mono<User> update(final @Valid @NonNull UUID id, final @Body @Valid @NonNull User input) {
        final var user = User.builder().id(id)
                .email(input.email().toLowerCase())
                .avatarUrl(input.avatarUrl())
                .fullName(input.fullName())
                .password(input.password())
                .build();

        return userRepository.update(user);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(final @Valid @NonNull UUID id) {
        return userRepository.deleteById(id).then();
    }

}
