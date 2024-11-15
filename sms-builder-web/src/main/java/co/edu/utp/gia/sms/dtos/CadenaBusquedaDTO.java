package co.edu.utp.gia.sms.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public record CadenaBusquedaDTO(
        @NotNull String id,
        @NotBlank String fuente,
        @NotBlank String consulta,
        @NotNull Date fechaConsulta,
        @NotNull Integer resultadoPreliminar,
        @NotNull Integer resultadoFinal) {
    public CadenaBusquedaDTO {
        id = Objects.requireNonNullElse(id,UUID.randomUUID().toString());
    }
}
