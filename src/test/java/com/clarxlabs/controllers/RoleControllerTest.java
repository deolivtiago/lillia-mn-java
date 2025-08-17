package com.clarxlabs.controllers;

import com.clarxlabs.controllers.roles.inputs.RoleInputBuilder;
import com.clarxlabs.entities.Role;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.type.Argument;
import io.micronaut.data.model.Page;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.reactor.http.client.ReactorHttpClient;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.Set;


@MicronautTest
class RoleControllerTest {
    @Inject
    @Client("/roles")
    @NonNull
    ReactorHttpClient httpClient;

    RoleControllerTest(@NonNull ReactorHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Test
    void shouldListAllRoles() {
        final var uri = UriBuilder.of("/")
                .queryParam("page", 1)
                .queryParam("size", 1)
                .queryParam("sort", "createdAt,asc")
                .build();

        StepVerifier
                .create(httpClient.retrieve(HttpRequest.GET(uri), Argument.of(Page.class, Role.class)))
                .assertNext(page -> Assertions.assertEquals(1, page.getContent().size()))
                .verifyComplete();
    }

    @Test
    void shouldGetRoleById() {
        final var uri = UriBuilder.of("/root").build();

        StepVerifier
                .create(httpClient.retrieve(HttpRequest.GET(uri), Role.class))
                .assertNext(role -> {
                    Assertions.assertEquals("root", role.id());
                    Assertions.assertEquals(Set.of(), role.permissions());
                })
                .verifyComplete();
    }

    @Test
    void shouldCreateNewRoleWithPermissions() {
        final var uri = UriBuilder.of("/").build();
        final var input = RoleInputBuilder.builder().id("hobbit")
                .permissions(Set.of("GET:roles"))
                .build();

        StepVerifier
                .create(httpClient.retrieve(HttpRequest.POST(uri, input), Role.class))
                .assertNext(role -> {
                    Assertions.assertEquals(input.id(), role.id());
                    Assertions.assertEquals(input.permissions(), role.permissions());
                })
                .verifyComplete();
    }

    @Test
    void shouldCreateNewRoleWithoutPermissions() {
        final var uri = UriBuilder.of("/").build();
        final var input = RoleInputBuilder.builder().id("wizard").build();

        StepVerifier
                .create(httpClient.retrieve(HttpRequest.POST(uri, input), Role.class))
                .assertNext(role -> {
                    Assertions.assertEquals(input.id(), role.id());
                    Assertions.assertEquals(Set.of(), role.permissions());
                })
                .verifyComplete();
    }
}
