package com.clarxlabs.controllers;

import com.clarxlabs.entities.Role;
import com.clarxlabs.repositories.RoleRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import reactor.core.publisher.Mono;

@Validated
@Controller("/roles")
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Get
    @Status(HttpStatus.OK)
    public Mono<Page<Role>> index(final @Valid @NonNull Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Mono<Role> create(final @Body @Valid @NonNull Role data) {
        return roleRepository.save(data);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Mono<Role> show(final @NotBlank @NonNull String id) {
        return roleRepository.findById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public Mono<Role> update(final @NotBlank @NonNull String id, final @Body @Valid @NonNull Role data) {
        final var role = Role.builder().id(id.toLowerCase())
                .permissions(data.permissions())
                .build();

        return roleRepository.update(role);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(final @NotBlank @NonNull String id) {
        return roleRepository.deleteById(id).then();
    }
}
