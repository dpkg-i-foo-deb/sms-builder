package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.TopicoService;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Clase utilitaria encargada de realizar conversiones de topicos (entidades) a elementos web
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigación en Redes Información y Distribución - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 05/09/2024
 */
@Named
@FacesConverter(value = "topicoConverter",managed = true)
public class TopicoConverter extends EntidadConverter<Topico> {

	@Inject
	private TopicoService topicoService;


	@Override
	protected Topico findById(String id) {
		return topicoService.find(id).orElse(null);
	}
}
