package co.edu.utp.gia.sms.dtos;

import co.edu.utp.gia.sms.entidades.EstadoUsuario;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public record UsuarioDTO (
        String id,
        String nombre,
        String nombreUsuario,
        String clave,
        String email,
        List<String> roles,
        String claveConfirmada,
        EstadoUsuario estado
) {
    public UsuarioDTO {
        id = Objects.requireNonNullElse(id, UUID.randomUUID().toString());
        roles = Objects.requireNonNullElse(roles, Collections.emptyList());
        estado = Objects.requireNonNullElse(estado, EstadoUsuario.ACTIVO);
    }
}