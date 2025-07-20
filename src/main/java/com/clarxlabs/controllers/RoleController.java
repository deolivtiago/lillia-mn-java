package com.clarxlabs.controllers;

import com.clarxlabs.entities.Role;
import com.clarxlabs.repositories.RoleRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;
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
    public Mono<Page<Role>> index(final @Valid Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Mono<Role> create(final @Body @Valid Role data) {
        return roleRepository.save(data);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Mono<Role> show(final String id) {
        return roleRepository.findById(id);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(final String id) {
        return roleRepository.deleteById(id).then();
    }
}
