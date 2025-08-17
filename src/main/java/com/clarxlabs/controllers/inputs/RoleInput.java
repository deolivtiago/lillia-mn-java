package com.clarxlabs.controllers.inputs;

import com.clarxlabs.controllers.roles.inputs.RoleInputWither;
import com.clarxlabs.entities.Role;
import com.clarxlabs.entities.RoleBuilder;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.sourcegen.annotations.Builder;
import io.micronaut.sourcegen.annotations.Wither;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Wither
@Builder
@Serdeable
@Introspected
public record RoleInput(

        @Id @NotBlank
        @NonNull String id,

        @NotNull
        @NonNull Set<String> permissions

) implements RoleInputWither {
    public Role toRole() {
        return RoleBuilder.builder().id(id)
                .permissions(permissions)
                .build();
    }
}
