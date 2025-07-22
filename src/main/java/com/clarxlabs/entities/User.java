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

import java.time.ZonedDateTime;
import java.util.UUID;

@Serdeable
@Introspected
@MappedEntity(value = "users", alias = "us")
public record User(
        @Nullable @Id @AutoPopulated
        UUID id,

        @NonNull @NotBlank @Email
        String email,

        @NonNull @NotBlank
        String password,

        @NonNull @NotBlank @Size(min = 2, max = 255)
        String fullName,

        @Nullable @GeneratedValue @Size(max = 255)
        String avatarUrl,

        @Nullable @GeneratedValue
        Boolean isVerified,

        @Nullable @DateUpdated
        ZonedDateTime updatedAt,

        @Nullable @DateCreated
        ZonedDateTime createdAt
) {
    @Creator
    public User(UUID id, String email, String password, String fullName, ZonedDateTime updatedAt, ZonedDateTime createdAt) {
        this(id, email, password, fullName, "", false, updatedAt, createdAt);
    }
}
