package com.ui;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Se creo un m�todo base de los que extender�n otras clases "user interface" 
 * este contendr� todo el c�digo que l�gicamente se podr�a usar en cualquiera
 * de ellas, actualmente solo tiene el manejos de mensajes y errores. 
 *
 * El c�digo esta hecho en ingles, la interfaz gr�fica tiene las etiquetas en
 * espa�ol y los comentarios se dejan en espa�ol para facilidad de revisi�n
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