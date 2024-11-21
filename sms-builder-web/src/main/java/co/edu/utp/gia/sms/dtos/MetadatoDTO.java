package co.edu.utp.gia.sms.dtos;

import co.edu.utp.gia.sms.entidades.TipoMetadato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public record MetadatoDTO(@NotNull String id, @NotNull TipoMetadato identifier, String value, @NotBlank String referencia) {
    public MetadatoDTO {
        id = Objects.requireNonNullElse(id, UUID.randomUUID().toString());
    }
}
