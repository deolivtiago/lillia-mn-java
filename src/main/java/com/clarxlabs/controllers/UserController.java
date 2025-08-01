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
    private static final @NonNull Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final @NonNull UserRepository userRepository;

    public UserController(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Get
    @Status(HttpStatus.OK)
    public @NonNull Mono<Page<User>> index(final @Valid @NonNull Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Post
    @Status(HttpStatus.CREATED)
    public @NonNull Mono<User> create(final @Body @Valid @NonNull User input) {
        return userRepository.save(input.withEmail(input.email().toLowerCase()));
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public @NonNull Mono<User> show(final @Valid @NonNull UUID id) {
        return userRepository.findById(id);
    }

    @Put("/{id}")
    public @NonNull Mono<User> update(final @Valid @NonNull UUID id, final @Body @Valid @NonNull User input) {
        return userRepository.update(input.withEmail(input.email().toLowerCase()).withId(id));
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public @NonNull Mono<Void> delete(final @Valid @NonNull UUID id) {
        return userRepository.deleteById(id).then();
    }

}
