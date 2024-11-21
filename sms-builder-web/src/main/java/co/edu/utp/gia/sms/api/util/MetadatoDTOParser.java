package co.edu.utp.gia.sms.api.util;

import co.edu.utp.gia.sms.dtos.MetadatoDTO;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MetadatoDTOParser implements ParseApiUtil<Metadato, MetadatoDTO> {
    private final ReferenciaService referenciaService;

    @Inject
    public MetadatoDTOParser(ReferenciaService referenciaService) {
        this.referenciaService = referenciaService;
    }

    @Override
    public Metadato parse(MetadatoDTO metadatoDTO) {
        Metadato metadato = new Metadato();
        metadato.setId(metadatoDTO.id());
        metadato.setReferencia( referenciaService.findOrThrow(metadatoDTO.referencia()) );
        metadato.setIdentifier(metadatoDTO.identifier());
        metadato.setValue(metadatoDTO.value());
        return metadato;
    }
}
