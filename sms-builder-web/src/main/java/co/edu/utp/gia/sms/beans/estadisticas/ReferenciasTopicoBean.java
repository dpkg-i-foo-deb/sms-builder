package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Clase controladora de interfaz web que se encarga de presentar los datos estadísticos de los tópicos.
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
@Getter @Setter
public class ReferenciasTopicoBean extends EstaditicaDatoDTOBaseBean {
    private String codigo;
    private List<Topico> topicos;
    private List<Topico> topicosSeleccionados;

    public void inicializar() {
        System.out.println("INICAILIZANDO");
        setEjeX(getMessage(MessageConstants.TOPICOS));
        setEjeY("# "+getMessage(MessageConstants.SPS));
        setTitulo(getEjeY() + " - " + getEjeX());
        if (getRevision() != null) {
            onChangePregunta();
        }
    }

    public void onChangePregunta() {
        List<DatoDTO> datos;
        topicos = revisionService.getTopicos();
        topicosSeleccionados = topicosSeleccionados == null ? new ArrayList<>( topicos ): topicosSeleccionados;
        if (codigo != null) {
            datos = getEstadisticaService().obtenerReferenciasTopico(codigo);
        } else {
            datos = getEstadisticaService().obtenerReferenciasTopico();
        }
        actualizarTopicos(datos);
        Predicate<DatoDTO> filtro = dato->topicosSeleccionados.stream().anyMatch(
                t->dato.getEtiqueta().equalsIgnoreCase(t.getPregunta().getCodigo()+"-"+t.getDescripcion() )
        );
        datos = datos.stream().filter( filtro ).toList();
        setDatos(datos);
        crearModelo();
    }

    private void actualizarTopicos(List<DatoDTO> datos) {
        Predicate<Topico> filtroTopicos = topico -> datos.stream().anyMatch(
                dato->dato.getEtiqueta().equalsIgnoreCase( topico.getPregunta().getCodigo()+"-"+topico.getDescripcion() )
        );
        Predicate<Topico> filtroTopicosSeleccionados = topicos::contains;
        topicos = topicos.stream().filter(filtroTopicos).toList();
        topicosSeleccionados = new ArrayList<>(topicosSeleccionados.stream().filter(filtroTopicosSeleccionados).toList());
    }


}
