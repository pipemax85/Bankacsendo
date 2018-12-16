package com.dto;

import java.util.Date;

/**
 * Este es un objeto de transferencia de datos de la entidad coin, donde se
 * guarda la denominaci�n y la cantidad de los billetes.
 *
 * El c�digo esta hecho en ingles, la interfaz gr�fica tiene las etiquetas en
 * espa�ol y los comentarios se dejan en espa�ol para facilidad de revisi�n.
 *
 * @author Cristian Felipe Ramos Lopez
 * @version 1.0
 * @since 2018-12-14
 */

public class Coin {

	private Long id;
	private Integer denomination;
	private Integer amount;
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDenomination() {
		return denomination;
	}

	public void setDenomination(Integer denomination) {
		this.denomination = denomination;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
