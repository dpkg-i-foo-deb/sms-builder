package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Objetivo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Objects;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Objetivo}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class ObjetivoService extends AbstractGenericService<Objetivo, String> {
	@Inject
	private PreguntaService preguntaService;

	public ObjetivoService() {
		super(DB.root.getProvider(Objetivo.class));
	}

	/**
	 * Permite registrar una objetivo
	 * 
	 * @param codigo      Código del Objetivo
	 * @param descripcion Descripción del Objetivo
	 *
	 * @return El Objetivo registrado
	 */
	public Objetivo save(String codigo, String descripcion) {
		return save(new Objetivo( codigo, descripcion));
	}

	/**
	 * Permite obtener los objetivo relacionados a una pregunta
	 * @param id Id de la pregunta
	 * @return List< Objetivo > relacionados con la preguna
	 */
	public List<Objetivo> findByPregunta(String id) {
		return preguntaService.findOrThrow(id).getObjetivos();
	}

	/**
	 * Permite obtener los objetivo relacionados a una pregunta
	 * @param codigo Codigo del objetivo que se desea obtener
	 * @return List< Objetivo > relacionados con el código dado
	 */
	public List<Objetivo> findByCodigo(String codigo) {
		Objects.requireNonNull(codigo);
		return this.dataProvider.get()
				.stream()
				.filter( objetivo -> codigo.equals(objetivo.getCodigo()) )
				.toList();
	}

	/**
	 * Permite obtener los objetivo relacionados a una pregunta
	 * @param codigos Codigos de los objetivos que se desea obtener
	 * @return List< Objetivo > relacionados con los códigos dado
	 */
	public List<Objetivo> findByCodigos(List<String> codigos) {
		Objects.requireNonNull(codigos);
		return codigos
				.stream()
				.map(this::findByCodigo)
				.flatMap(List::stream)
				.toList();
	}
}
