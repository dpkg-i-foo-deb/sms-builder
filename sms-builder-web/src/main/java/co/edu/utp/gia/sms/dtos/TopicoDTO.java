package co.edu.utp.gia.sms.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record TopicoDTO(String id, String descripcion, @NotBlank String pregunta, List<String> tags) {
    public TopicoDTO {
        id = Objects.requireNonNullElse(id, UUID.randomUUID().toString());
        tags = Objects.requireNonNullElse(tags, Collections.emptyList());
    }
}
