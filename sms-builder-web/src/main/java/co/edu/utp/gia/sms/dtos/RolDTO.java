package co.edu.utp.gia.sms.dtos;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record RolDTO(String id, String nombre, List<String> recursos) {
    public RolDTO {
        id = Objects.requireNonNullElse(id, UUID.randomUUID().toString());
        recursos = Objects.requireNonNullElse(recursos, Collections.emptyList());
    }
}
