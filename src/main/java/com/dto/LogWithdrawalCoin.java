package com.dto;

import java.util.Date;

/**
 * Este es un objeto de transferencia de datos de la entidad logwithdrawalcoin, donde se
 * guarda el registro de las transacciones realizadas, aunque esto no se pidi� en la descripci�n 
 * de lo que tocaba hacer me pareci� �til para darle sentido a lo que se estaba haciendo. 
 *
 * El c�digo esta hecho en ingles, la interfaz gr�fica tiene las etiquetas en
 * espa�ol y los comentarios se dejan en espa�ol para facilidad de revisi�n.
 *
 * @author Cristian Felipe Ramos Lopez
 * @version 1.0
 * @since 2018-12-14
 */

public class LogWithdrawalCoin {

	private Long id;
	private Integer quantity;
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
