package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;

/**
 * Define de forma general un Parser que convierte un texto a Referencia
 * 
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 20/06/2019
 *
 * @param <T> Tipo de elemento a procesar para crear la referencia
 */
public abstract class ReferenceParser<T> {
	/**
	 * Variable que representa el atributo fuente de la clase
	 */
	private final String fuente;
	private final String tipoFuente;

	/**
	 * Metodo que permite inicializar los elementos de la clase ReferenceParser
	 * 
	 * @param fuente Fuente de la que providene la referencia
	 * @param tipoFuente Tipo de fuente de la que proviene la refeerncia
	 */
	public ReferenceParser(String fuente,String tipoFuente) {
		this.fuente = fuente;
		this.tipoFuente = tipoFuente;
	}

	/**
	 * Permite crear una referencia a partir del source dado
	 * @param source Elemento base para obtener los datos de la referencia
	 * @return La {@link Referencia} creada
	 */
	public final Referencia parse(T source) {
		if(source == null){
			return null;
		}
//		if ("".equals(texto.trim())) {
//			return null;
//		}
		
		Referencia referencia = procesar(source);
		if( referencia != null ){
			referencia.addElement(TipoMetadato.FUENTE, fuente);
			referencia.addElement(TipoMetadato.TIPO_FUENTE, tipoFuente);
		}
		return referencia;
	}


	/**
	 * Permite procesar el source para completar los datos de una referencia
	 * @param source Elemento base para obtener los datos de la referencia
	 */
	protected abstract Referencia procesar(T source);
}
