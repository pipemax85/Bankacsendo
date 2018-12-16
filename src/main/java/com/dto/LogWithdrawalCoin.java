package com.dto;

import java.util.Date;

/**
 * Este es un objeto de transferencia de datos de la entidad logwithdrawalcoin, donde se
 * guarda el registro de las transacciones realizadas, aunque esto no se pidió en la descripción 
 * de lo que tocaba hacer me pareció útil para darle sentido a lo que se estaba haciendo. 
 *
 * El código esta hecho en ingles, la interfaz gráfica tiene las etiquetas en
 * español y los comentarios se dejan en español para facilidad de revisión.
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
