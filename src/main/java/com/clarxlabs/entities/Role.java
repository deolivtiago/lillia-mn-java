package com.clarxlabs.entities;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.DataType;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;


@Serdeable
@Introspected
@Builder(toBuilder = true)
@MappedEntity(value = "roles", alias = "r")
public record Role(
        @Id @NotBlank
        @NonNull String id,

        @NotNull @TypeDef(type = DataType.STRING_ARRAY)
        @NonNull Set<String> permissions,

        @DateUpdated
        @Nullable ZonedDateTime updatedAt,

        @DateCreated
        @Nullable ZonedDateTime createdAt
) {
    public Role {
        Objects.requireNonNull(id, "property :id is required");
        permissions = Set.copyOf(Objects.requireNonNull(permissions, "property :permissions is required"));
        Objects.requireNonNull(updatedAt, "property :updatedAt is required");
        Objects.requireNonNull(createdAt, "property :createdAt is required");
    }

    @Creator
    public Role(
            @NonNull String id,
            @Nullable ZonedDateTime updatedAt,
            @Nullable ZonedDateTime createdAt
    ) {
        this(id, Set.of(), updatedAt, createdAt);
    }
}
