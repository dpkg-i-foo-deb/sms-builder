package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.importutil.FindReferenceCitation;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

/**
 * Clase controladora de interfaz web que se encarga de la aplicación de los criterios de evaluación.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@ViewScoped
@Log
public class AplicarCriteriosReferenciasBean extends GenericBean<ReferenciaDTO> {
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaService referenciaService;
    @Inject
    private ProcesoService procesoService;

    public void inicializar() {
        log.info("Aplicar criterios paso " + paso);
        referencias = referenciaService.findByPaso(getPasoAnterior().getId());
        log.info("Numero referecias " + referencias.size());
    }

    public void actualizarNota(ReferenciaDTO referencia) {
        referenciaService.updateNota(referencia.getId(), referencia.getNota());
    }

    public void adicionarResumen(ReferenciaDTO referencia) {
        String tranduccion = FindReferenceCitation.INSTANCE.findTranslate(referencia.getResumen());
        referencia.setNota(referencia.getNota() + "\n" + tranduccion);
        actualizarNota(referencia);
    }

    public void seleccionarReferencia(ReferenciaDTO referencia) {
        if (referencia.isSeleccionada()) {
            procesoService.addReferencia(getPasoActual().getId(),referencia.getId());
//            referenciaService.avanzarReferecias(getPasoActual().getId());
//            if( referencia.getFiltro() < paso ) {
//                referencia.setFiltro(paso);
//                referenciaService.actualizarFiltro(referencia.getId(), paso);
//            }
        } else {
            procesoService.removeReferencia(getPasoActual().getId(),referencia.getId());
//            referenciaService.avanzarReferecias(getPasoAnterior().getId());
//            if( referencia.getFiltro() >= paso ){
//                referencia.setFiltro(paso-1);
//                referenciaService.actualizarFiltro(referencia.getId(), paso-1);
//            }
        }
        //referenciaService.actualizarFiltro(referencia.getId(), referencia.getFiltro());
    }

    public void actualizarRelevancia(ReferenciaDTO referencia) {
        referenciaService.updateRelevancia(referencia.getId(), referencia.getRelevancia());
    }
}
