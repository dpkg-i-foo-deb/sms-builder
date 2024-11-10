package co.edu.utp.gia.sms.dtos;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record PreguntaDTO(String id, String codigo, String descripcion, List<String> objetivos) {
    public PreguntaDTO{
        id = Objects.requireNonNullElse(id, UUID.randomUUID().toString());
        objetivos = Objects.requireNonNullElse(objetivos, Collections.emptyList());
    }
}
