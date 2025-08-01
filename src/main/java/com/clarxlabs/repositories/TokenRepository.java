package com.clarxlabs.repositories;

import com.clarxlabs.entities.Token;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;

import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface TokenRepository extends ReactorCrudRepository<@Valid @NotNull @NonNull Token, @Valid @NotNull @NonNull UUID> {
    @Query("select * from tokens where is_revoked = true")
    @NonNull
    Flux<@NonNull Token> findAllRevoked();
}
