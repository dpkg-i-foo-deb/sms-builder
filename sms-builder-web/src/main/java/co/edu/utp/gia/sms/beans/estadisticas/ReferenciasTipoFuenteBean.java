package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import lombok.Getter;
import lombok.Setter;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serial;

/**
 * Clase controladora de interfaz web que se encarga de presentar los datos estadísticos de los tipos de fuente.
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
public class ReferenciasTipoFuenteBean extends EstaditicaDatoDTOBaseBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    @Serial
    private static final long serialVersionUID = -5273832765304254823L;
    @Getter
    @Setter
    private TipoFuente tipo;

    public void inicializar() {
        setEjeX(getMessage(MessageConstants.FUENTE));
        setEjeY("# "+getMessage(MessageConstants.SPS));
        setTitulo(getEjeY() + " - " + getEjeX());

        if (getRevision() != null) {
            setDatos(getEstadisticaService().obtenerReferenciasTipoFuente());

            crearModelo();

        }
    }


    public void onChangeTipoFuente() {
        getDatosSeries().clear();
        if (tipo == null) {
            setDatos(getEstadisticaService().obtenerReferenciasTipoFuente());
        } else {
            setDatos(getEstadisticaService().obtenerReferenciasTipoFuenteNombre(tipo));
        }
        crearModelo();
    }

    public TipoFuente[] getTiposFuente() {
        return TipoFuente.values();
    }

}
