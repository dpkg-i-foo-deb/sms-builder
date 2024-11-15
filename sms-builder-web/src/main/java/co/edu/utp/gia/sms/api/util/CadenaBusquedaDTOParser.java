package co.edu.utp.gia.sms.api.util;

import co.edu.utp.gia.sms.dtos.CadenaBusquedaDTO;
import co.edu.utp.gia.sms.entidades.CadenaBusqueda;
import co.edu.utp.gia.sms.negocio.FuenteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CadenaBusquedaDTOParser implements ParseApiUtil<CadenaBusqueda, CadenaBusquedaDTO> {

    private final FuenteService fuenteService;

    @Inject
    public CadenaBusquedaDTOParser(FuenteService fuenteService) {
        this.fuenteService = fuenteService;
    }

    @Override
    public CadenaBusqueda parse(CadenaBusquedaDTO cadenaBusquedaDTO) {
        CadenaBusqueda cadenaBusqueda = new CadenaBusqueda();
        cadenaBusqueda.setId(cadenaBusquedaDTO.id());
        cadenaBusqueda.setBaseDatos(fuenteService.findOrThrow(cadenaBusquedaDTO.fuente()));
        cadenaBusqueda.setConsulta(cadenaBusquedaDTO.consulta());
        cadenaBusqueda.setFechaConsulta(cadenaBusquedaDTO.fechaConsulta());
        cadenaBusqueda.setResultadoPreliminar(cadenaBusquedaDTO.resultadoPreliminar());
        cadenaBusqueda.setResultadoFinal(cadenaBusquedaDTO.resultadoFinal());
        return cadenaBusqueda;
    }
}
