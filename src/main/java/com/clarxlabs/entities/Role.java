package com.clarxlabs.entities;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.DataType;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.sourcegen.annotations.Builder;
import io.micronaut.sourcegen.annotations.Wither;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.Set;


@Wither
@Builder
@Serdeable
@Introspected
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
) implements RoleWither {
}
