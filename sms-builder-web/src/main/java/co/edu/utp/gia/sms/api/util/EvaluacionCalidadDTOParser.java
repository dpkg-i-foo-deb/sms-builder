package co.edu.utp.gia.sms.api.util;

import co.edu.utp.gia.sms.dtos.EvaluacionCalidadDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadService;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EvaluacionCalidadDTOParser implements ParseApiUtil<EvaluacionCalidad, EvaluacionCalidadDTO> {

    private final ReferenciaService referenciaService;
    private final AtributoCalidadService atributoCalidadService;

    @Inject
    public EvaluacionCalidadDTOParser(ReferenciaService referenciaService, AtributoCalidadService atributoCalidadService) {
        this.referenciaService = referenciaService;
        this.atributoCalidadService = atributoCalidadService;
    }

    @Override
    public EvaluacionCalidad parse(EvaluacionCalidadDTO evaluacionCalidadDTO) {
        EvaluacionCalidad evaluacionCalidad = new EvaluacionCalidad();
        evaluacionCalidad.setId(evaluacionCalidadDTO.id());
        evaluacionCalidad.setReferencia( referenciaService.findOrThrow(evaluacionCalidadDTO.referencia()) );
        evaluacionCalidad.setAtributoCalidad( atributoCalidadService.findOrThrow(evaluacionCalidadDTO.atributoCalidad()) );
        evaluacionCalidad.evaluar( evaluacionCalidadDTO.evaluacionCualitativa() );
        return evaluacionCalidad;
    }
}
