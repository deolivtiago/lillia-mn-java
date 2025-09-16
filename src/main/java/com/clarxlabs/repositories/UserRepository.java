package com.clarxlabs.repositories;

import com.clarxlabs.entities.User;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Mono;

import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface UserRepository extends ReactorPageableRepository<@Valid @NotNull @NonNull User, @Valid @NotNull @NonNull UUID> {
    @NonNull
    Mono<@Valid @NonNull User> findByUsername(@NonNull @NotBlank @NotNull String username);
}
