package co.edu.utp.gia.sms.api.util;

import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.negocio.ObjetivoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PreguntaDTOParser implements ParseApiUtil<Pregunta, PreguntaDTO> {

    private final ObjetivoService objetivoService;

    @Inject
    public PreguntaDTOParser(ObjetivoService objetivoService) {
        this.objetivoService = objetivoService;
    }

    @Override
    public Pregunta parse(PreguntaDTO preguntaDTO) {
        Pregunta pregunta = new Pregunta();
        pregunta.setId(preguntaDTO.id());
        pregunta.setDescripcion(preguntaDTO.descripcion());
        pregunta.setCodigo(preguntaDTO.codigo());
        pregunta.setObjetivos(objetivoService.findByCodigos(preguntaDTO.objetivos()));
        return pregunta;
    }
}
