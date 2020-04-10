package co.edu.utp.gia.sms.beans.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.negocio.ObjetivoEJB;

@FacesConverter("objetivoConverter")
public class ObjetivoConverter implements Converter<Objetivo> {

	@Inject
	private ObjetivoEJB objetivoEJB;

	@Override
	public Objetivo getAsObject(FacesContext facesContext, UIComponent componente, String id) {

		Objetivo objetivo = null;
		if (id != null && !"".equals(id.trim())) {
			try {
				objetivo = objetivoEJB.obtener(Integer.parseInt(id));
			} catch (Exception e) {
				throw new ConverterException(
						new FacesMessage(componente.getClientId() + ":" + "Error al convertir el valor"));
			}
		}
		return objetivo;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, Objetivo objetivo) {
		return  objetivo != null ? objetivo.getId().toString() : "";
	}

}