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
    public Mono<Page<User>> index(@NonNull @Valid Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Mono<User> create(final @Body @Valid User data) {
        return userRepository.save(data);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Mono<User> show(final UUID id) {
        return userRepository.findById(id);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(final UUID id) {
        return userRepository.deleteById(id).then();
    }

}
