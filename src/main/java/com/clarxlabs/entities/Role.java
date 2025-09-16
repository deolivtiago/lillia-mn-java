package com.clarxlabs.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.DataType;
import io.micronaut.security.annotation.CreatedBy;
import io.micronaut.security.annotation.UpdatedBy;
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
@MappedEntity(value = "roles", alias = "r")
public record Role(
        @Id @NotBlank
        @NonNull String id,

        @NotNull @TypeDef(type = DataType.STRING_ARRAY)
        @NonNull Set<String> permissions,

        @UpdatedBy @JsonProperty("updated_by")
        @Nullable String updatedBy,

        @DateUpdated @JsonProperty("updated_at")
        @Nullable ZonedDateTime updatedAt,

        @CreatedBy @JsonProperty("created_by")
        @Nullable String createdBy,

        @DateCreated @JsonProperty("created_at")
        @Nullable ZonedDateTime createdAt
) implements RoleWither {
}
