package co.edu.utp.gia.sms.dtos;

import jakarta.validation.constraints.NotNull;

public record RevisionDTO(@NotNull String nombre,@NotNull String descripcion) {
}
