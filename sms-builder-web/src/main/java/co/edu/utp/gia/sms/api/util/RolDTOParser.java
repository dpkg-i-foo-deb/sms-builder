package co.edu.utp.gia.sms.api.util;

import co.edu.utp.gia.sms.dtos.RolDTO;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.negocio.RecursoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RolDTOParser implements ParseApiUtil<Rol, RolDTO> {

    private final RecursoService recursoService;

    @Inject
    public RolDTOParser(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @Override
    public Rol parse(RolDTO rolDTO) {
        Rol rol = new Rol();
        rol.setId(rolDTO.id());
        rol.setNombre(rolDTO.nombre());
        rol.setRecursos(rolDTO.recursos().stream().map(recursoService::findByUrl).toList());
        return rol;
    }
}
