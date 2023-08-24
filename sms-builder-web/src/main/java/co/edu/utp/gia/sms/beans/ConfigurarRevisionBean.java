package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.ProcesoEJB;
import co.edu.utp.gia.sms.negocio.RevisionService;
import lombok.Getter;
import lombok.Setter;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

/**
 * Clase controladora de interfaz web que se encarga de la configuración de una revision.
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
public class ConfigurarRevisionBean extends AbstractBean {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -6995163695300909108L;
    @Getter
    @Setter
    private Revision revision;
    @Getter
    private List<PasoProceso> pasos;
    @Inject
    private SeguridadBeanImpl seguridadBean;
    @Inject
    private ProcesoEJB procesoEJB;
    @Inject
    private RevisionService revisionService;

    @PostConstruct
    public void inicializar() {
        if (seguridadBean.isAutenticado()) {
            revision = (Revision) getFromSession("revision");
            pasos = procesoEJB.listar(revision.getId());
        }
    }

    public void actualizar() {
        revisionService.save(revision.getNombre(), revision.getDescripcion());
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

}
