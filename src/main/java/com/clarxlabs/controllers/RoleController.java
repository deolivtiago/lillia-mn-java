package com.clarxlabs.controllers;

import com.clarxlabs.controllers.inputs.RoleInput;
import com.clarxlabs.entities.Role;
import com.clarxlabs.repositories.RoleRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import reactor.core.publisher.Mono;

@Validated
@Controller("/roles")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class RoleController {
    private final @NonNull RoleRepository roleRepository;

    public RoleController(final @NonNull RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Get("/info")
    @Produces(MediaType.TEXT_PLAIN)
    @Status(HttpStatus.OK)
    public @NonNull Mono<String> info(final Authentication auth) {
        return Mono.just(auth.getAttributes().toString());
    }

    @Get
    @Status(HttpStatus.OK)
    public @NonNull Mono<Page<Role>> index(final @Valid @NonNull Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Post
    @Status(HttpStatus.CREATED)
    @Secured({"ROLE_USER"})
    public @NonNull Mono<Role> create(final @Body @Valid @NonNull RoleInput input) {
        return roleRepository.save(input.toRole());
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public @NonNull Mono<Role> show(final @NotBlank @NonNull String id) {
        return roleRepository.findById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public @NonNull Mono<Role> update(final @NotBlank @NonNull String id, final @Body @Valid @NonNull RoleInput input) {
        return roleRepository.update(input.toRole().withId(id));
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public @NonNull Mono<Void> delete(final @NotBlank @NonNull String id) {
        return roleRepository.deleteById(id).then();
    }
}
