package com.clarxlabs.configurations.security;

import com.clarxlabs.repositories.UserRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.ReactiveAuthenticationProvider;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Singleton
public class AuthenticationProvider<B> implements ReactiveAuthenticationProvider<HttpRequest<B>, String, String> {
    private final @NonNull UserRepository userRepository;

    public AuthenticationProvider(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @SingleResult
    public @NonNull Publisher<AuthenticationResponse> authenticate(HttpRequest<B> context, AuthenticationRequest<String, String> request) {
        String username = request.getIdentity();
        String password = request.getSecret();

        return userRepository.findByUsername(username)
                .flatMap(user ->
                        user.password().equals(password)
                                ? Mono.just(AuthenticationResponse.success(user.username()))
                                : Mono.just(AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)))
                .switchIfEmpty(Mono.just(AuthenticationResponse.failure(AuthenticationFailureReason.USER_NOT_FOUND)));
    }
}
