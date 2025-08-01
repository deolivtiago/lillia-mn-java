package com.clarxlabs.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.*;
import io.micronaut.security.annotation.CreatedBy;
import io.micronaut.security.annotation.UpdatedBy;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.sourcegen.annotations.Builder;
import io.micronaut.sourcegen.annotations.Wither;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.ZonedDateTime;
import java.util.UUID;

@Wither
@Builder
@Serdeable
@MappedEntity(value = "users", alias = "u")
public record User(
        @Id @AutoPopulated
        @Nullable UUID id,

        @NotBlank @Email
        @NonNull String username,

        @NotBlank @Size(min = 6, max = 72)
        @NonNull String password,

        @NotBlank @Size(min = 2, max = 255) @JsonProperty("full_name")
        @NonNull String fullName,

        @GeneratedValue @Size(max = 255) @JsonProperty("avatar_url")
        @Nullable String avatarUrl,

        @GeneratedValue @JsonProperty("is_verified")
        @Nullable Boolean isVerified,

        @UpdatedBy @JsonProperty("updated_by")
        @Nullable String updatedBy,

        @DateUpdated @JsonProperty("updated_at")
        @Nullable ZonedDateTime updatedAt,

        @CreatedBy @JsonProperty("created_by")
        @Nullable String createdBy,

        @DateCreated @JsonProperty("created_at")
        @Nullable ZonedDateTime createdAt
) implements UserWither {
}
