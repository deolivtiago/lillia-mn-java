package com.clarxlabs.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.*;
import io.micronaut.security.annotation.CreatedBy;
import io.micronaut.security.annotation.UpdatedBy;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.sourcegen.annotations.Builder;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.UUID;

@Where("@.is_revoked = false")
@Builder
@Serdeable
@MappedEntity(value = "tokens", alias = "t")
public record Token(
        @Id @NotNull
        @NonNull UUID id,

        @NotNull
        @NonNull String username,

        @NotNull @JsonProperty("is_revoked")
        @NonNull Boolean isRevoked,

        @UpdatedBy @JsonProperty("updated_by")
        @Nullable String updatedBy,

        @DateUpdated @JsonProperty("updated_at")
        @Nullable ZonedDateTime updatedAt,

        @CreatedBy @JsonProperty("created_by")
        @Nullable String createdBy,

        @DateCreated @JsonProperty("created_at")
        @Nullable ZonedDateTime createdAt
) {
}
