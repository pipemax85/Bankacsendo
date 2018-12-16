package com.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.dto.Coin;
import com.svc.Svc;

/**
 * Este es la clase de manejo de interfaz de la entidad coin, básicamente
 * solo contiene métodos de procesamiento de CRUD
 *
 * El código esta hecho en ingles, la interfaz gráfica tiene las etiquetas en
 * español y los comentarios se dejan en español para facilidad de revisión
 *
 * @author Cristian Felipe Ramos Lopez
 * @version 1.0
 * @since 2018-12-14
 */

@ViewScoped
@ManagedBean(name = "coinUI")
public class CoinUI extends BasicUI {

	List<Coin> coins = new ArrayList<Coin>();
	Coin actual = new Coin();
	Svc svc = new Svc();

	@PostConstruct
	public void init() {
		try {
			consult();
		} catch (Exception e) {
			error(e);
		}
	}

	public void consult() {
		try {
			coins = svc.consult(actual, "denomination");
			System.out.println(coins);
		} catch (Exception e) {
			error(e);
		}
	}

	public void save() {
		try {
			if (actual.getId() == null) {
				if (!validateSave()) {
					return;
				}
				actual.setId(new Date().getTime());
				actual.setCreatedDate(new Date());
				svc.save(actual);
			} else {
				svc.update(actual);
			}
			actual = new Coin();
			consult();
			addInfo("Se ha guardado el billete");
		} catch (Exception e) {
			error(e);
		}
	}

	private boolean validateSave() {
		for (Coin coin : coins) {
			if (coin.getDenomination().intValue() == actual.getDenomination().intValue()) {
				addWarn("No se puede registrar un nuevo billete con una denominación existente, si quiere editar la cantidad presione el botón de  editar en la tabla");
				return false;
			}
		}
		return true;
	}

	public void edit(Coin coin) {
		try {
			actual = coin;
			svc.search(coin);
			addInfo("Ahora puede editar la información del billete");
		} catch (Exception e) {
			error(e);
		}
	}

	public void delete(Coin actual) {
		try {
			svc.delete(actual);
			actual = new Coin();
			consult();
			addInfo("Se ha eliminado el billete");
		} catch (Exception e) {
			error(e);
		}
	}

	public Coin getActual() {
		return actual;
	}

	public void setActual(Coin actual) {
		this.actual = actual;
	}

	public List<Coin> getCoins() {
		return coins;
	}

	public void setCoins(List<Coin> coins) {
		this.coins = coins;
	}

}