package co.edu.utp.gia.sms.api.util;

import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.dtos.TopicoDTO;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.ObjetivoService;
import co.edu.utp.gia.sms.negocio.PreguntaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TopicoDTOParser implements ParseApiUtil<Topico, TopicoDTO> {

    private final PreguntaService preguntaService;

    @Inject
    public TopicoDTOParser(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    @Override
    public Topico parse(TopicoDTO topicoDTO) {
        Topico topico = new Topico();
        topico.setId(topicoDTO.id());
        topico.setDescripcion(topicoDTO.descripcion());
        topico.setPregunta(preguntaService.findOrThrow(topicoDTO.pregunta()));
        return topico;
    }
}
