package com.clarxlabs.repositories;

import com.clarxlabs.entities.Role;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface RoleRepository extends ReactorPageableRepository<Role, String> {
}
