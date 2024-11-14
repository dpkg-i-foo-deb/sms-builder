package co.edu.utp.gia.sms.api.util;

import co.edu.utp.gia.sms.dtos.PasoProcesoDTO;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.PasoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PasoProcesoDTOParser implements ParseApiUtil<PasoProceso, PasoProcesoDTO> {

    private final PasoService pasoService;

    @Inject
    public PasoProcesoDTOParser(PasoService pasoService) {
        this.pasoService = pasoService;
    }

    @Override
    public PasoProceso parse(PasoProcesoDTO pasoProcesoDTO) {
        PasoProceso paso = new PasoProceso();
        paso.setId( pasoProcesoDTO.id() );
        paso.setOrden( pasoProcesoDTO.orden() );
        paso.setPaso( pasoService.findOrThrow( pasoProcesoDTO.paso() ) );
        return paso;
    }
}
