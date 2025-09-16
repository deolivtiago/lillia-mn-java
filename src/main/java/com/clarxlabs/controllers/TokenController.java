package com.clarxlabs.controllers;

import com.clarxlabs.entities.Token;
import com.clarxlabs.repositories.TokenRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Validated
@Controller("/tokens")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class TokenController {
    private final @NonNull TokenRepository tokenRepository;

    public TokenController(final @NonNull TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    @Get
    @Status(HttpStatus.OK)
    public @NonNull Flux<Token> index() {
        return tokenRepository.findAll();
    }

    @Post
    @Status(HttpStatus.CREATED)
    public @NonNull Mono<Token> create(final @Body @Valid @NonNull Token input) {
        return tokenRepository.save(input);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public @NonNull Mono<Token> show(final @Valid @NonNull UUID id) {
        return tokenRepository.findById(id);
    }


    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public @NonNull Mono<Void> delete(final @Valid @NonNull UUID id) {
        return tokenRepository.deleteById(id).then();
    }
}
