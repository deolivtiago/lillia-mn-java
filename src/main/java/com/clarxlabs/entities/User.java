package com.clarxlabs.entities;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.*;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Serdeable
@Introspected
@Builder(toBuilder = true)
@MappedEntity(value = "users", alias = "us")
public record User(
        @Id @AutoPopulated
        @Nullable UUID id,

        @NotBlank @Email
        @NonNull String email,

        @NotBlank
        @NonNull String password,

        @NotBlank @Size(min = 2, max = 255)
        @NonNull String fullName,

        @GeneratedValue @Size(max = 255)
        @Nullable String avatarUrl,

        @GeneratedValue
        @Nullable Boolean isVerified,

        @DateUpdated
        @Nullable ZonedDateTime updatedAt,

        @DateCreated
        @Nullable ZonedDateTime createdAt
) {
    @Creator
    public User(
            @Nullable UUID id,
            @NonNull String email,
            @NonNull String password,
            @NonNull String fullName,
            @Nullable ZonedDateTime updatedAt,
            @Nullable ZonedDateTime createdAt
    ) {
        this(id, email, password, fullName, "", false, updatedAt, createdAt);
    }
}
