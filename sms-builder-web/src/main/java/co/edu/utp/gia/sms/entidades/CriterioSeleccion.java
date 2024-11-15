package co.edu.utp.gia.sms.entidades;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CriterioSeleccion implements Entidad<String>{
    @Setter
    @NotNull
    private String id = UUID.randomUUID().toString();
    private String nombre;
    private String descripcion;
    private boolean paraExclusion;

    public CriterioSeleccion(String nombre, String descripcion, boolean paraExclusion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.paraExclusion = paraExclusion;
    }
}
