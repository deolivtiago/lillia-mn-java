package com.clarxlabs.entities;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.DataType;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.Set;


@Serdeable
@Introspected
@MappedEntity(value = "roles", alias = "r")
public record Role(
        @NonNull @Id @NotBlank
        String id,

        @NonNull @NotNull @TypeDef(type = DataType.STRING_ARRAY)
        Set<String> permissions,

        @Nullable @DateUpdated
        ZonedDateTime updatedAt,

        @Nullable @DateCreated
        ZonedDateTime createdAt
) {
    public Role(String id, ZonedDateTime updatedAt, ZonedDateTime createdAt) {
        this(id, Set.of(), updatedAt, createdAt);
    }
}
