package co.edu.utp.gia.sms.dtos;

import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public record EvaluacionCalidadDTO(
        @NotNull String id,
        @NotBlank String referencia,
        @NotBlank String atributoCalidad,
        @NotNull EvaluacionCualitativa evaluacionCualitativa
) {
    public EvaluacionCalidadDTO {
        id = Objects.requireNonNullElse(id, UUID.randomUUID().toString());
    }
}
