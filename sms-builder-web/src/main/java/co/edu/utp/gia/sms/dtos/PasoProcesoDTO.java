package co.edu.utp.gia.sms.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public record PasoProcesoDTO(@NotNull String id, @NotBlank String paso) {
    public PasoProcesoDTO {
        id = Objects.requireNonNullElse(id, UUID.randomUUID().toString());
    }
}
