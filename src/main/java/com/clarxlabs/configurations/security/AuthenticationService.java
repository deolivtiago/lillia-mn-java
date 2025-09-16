package com.clarxlabs.configurations.security;

import com.clarxlabs.entities.TokenBuilder;
import com.clarxlabs.repositories.TokenRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
public class AuthenticationService implements RefreshTokenPersistence {
    private static final @NonNull Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    private final @NonNull TokenRepository tokenRepository;

    public AuthenticationService(@NonNull TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void persistToken(@Nullable RefreshTokenGeneratedEvent event) {
        assert event != null && event.getAuthentication() != null && event.getRefreshToken() != null;

        final var id = UUID.fromString(event.getRefreshToken());
        final var username = event.getAuthentication().getName().toLowerCase();
        System.out.println(event.getAuthentication().getAttributes());

        final var token = TokenBuilder.builder()
                .id(id)
                .username(username)
                .isRevoked(false)
                .createdBy(username)
                .updatedBy(username)
                .build();

        tokenRepository.save(token).subscribe();
    }

    @Override
    public @NonNull Publisher<Authentication> getAuthentication(@NonNull String id) {
        return tokenRepository
                .findById(UUID.fromString(id))
                .flatMap(token -> Mono.just(Authentication.build(token.username())));
    }
}
