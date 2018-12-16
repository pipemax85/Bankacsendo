package com.ui;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Se creo un método base de los que extenderán otras clases "user interface" 
 * este contendrá todo el código que lógicamente se podría usar en cualquiera
 * de ellas, actualmente solo tiene el manejos de mensajes y errores. 
 *
 * El código esta hecho en ingles, la interfaz gráfica tiene las etiquetas en
 * español y los comentarios se dejan en español para facilidad de revisión
 *
 * @author Cristian Felipe Ramos Lopez
 * @version 1.0
 * @since 2018-12-14
 */

public class BasicUI {

	protected void addInfo(String message) {
		addMessage(FacesMessage.SEVERITY_INFO, "Procedimiento exitoso", message);
	}

	protected void addWarn(String message) {
		addMessage(FacesMessage.SEVERITY_WARN, "Procedimiento fallido", message);
	}

	protected void addError(String message) {
		addMessage(FacesMessage.SEVERITY_ERROR, "Error", message);
	}

	private void addMessage(Severity severity, String title, String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(severity, title, message));
	}

	protected void error(Exception e) {
		e.printStackTrace();
		addError(e.getMessage());
	}

}