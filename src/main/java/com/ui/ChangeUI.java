package com.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.dto.Coin;
import com.dto.LogWithdrawalCoin;
import com.svc.Svc;

/**
 * En esta clase se realiza el manejo de interfaz de las transacciones
 * donde también se realiza el guardado del log.
 * 
 * Aquí también se creo el algoritmo principal del programa, a pesar de 
 * esta ubicación estoy consciente de que si se crearan otras interfaces
 * este algoritmo debería quedar en un servicio a parte, pero por el hecho
 * de que actualmente solo se usara en esta parte, decidí dejarlo así.  
 *
 * El código esta hecho en ingles, la interfaz gráfica tiene las etiquetas en
 * español y los comentarios se dejan en español para facilidad de revisión
 *
 * @author Cristian Felipe Ramos Lopez
 * @version 1.0
 * @since 2018-12-14
 */

@ViewScoped
@ManagedBean(name = "changeUI")
public class ChangeUI extends BasicUI {

	private Integer valuerequested;
	private Integer totalcoins = 0;
	private List<Integer> tempanswer;
	private List<Coin> returncoins;
	private Svc svc = new Svc();
	private List<Coin> coins;
	private boolean mostrarResultado = false;

	@PostConstruct
	public void init() {
		try {
			coins = svc.consult(new Coin(), "denomination");
		} catch (Exception e) {
			error(e);
		}
	}

	public void processRequest() {
		try {
			if (!validateOperation()) {
				return;
			}
			int[] nums = combine(coins);
//			for (int i : nums) {
//				System.out.println(i);
//			}
//			if (coins.size() > 0) {
//				// return;
//			}
			tempanswer = new ArrayList<Integer>();
			returncoins = new ArrayList<Coin>();
			try {
				totalcoins = minCount(nums, valuerequested, 0, 0, 0);
			} catch (Exception e) {
				addWarn("El cajero no puede realizar esta transacción");
				return;
			}
			// System.out.println(totalcoins);
			if (totalcoins <= 0) {
				System.out.println(totalcoins);
				valuerequested = 0;
				addWarn("El cajero no puede realizar esta transacción");
				return;
			}
			Map<Integer, Integer> indexCoins = new HashMap<Integer, Integer>();
			for (int i : tempanswer) {
				Integer ind = indexCoins.get(i);
				if (ind == null) {
					Coin coin = new Coin();
					coin.setDenomination(i);
					coin.setAmount(1);
					returncoins.add(coin);
					indexCoins.put(i, returncoins.size() - 1);
					continue;
				}
				returncoins.get(ind).setAmount(returncoins.get(ind).getAmount() + 1);
			}
			addInfo("Se ha calculado correctamente los billetes que se necesitan y su cantidad");

			LogWithdrawalCoin log = new LogWithdrawalCoin();
			log.setCreatedDate(new Date());
			log.setId(new Date().getTime());
			log.setQuantity(valuerequested);
			svc.save(log);

			Map<Integer, Coin> mapCoins = new HashMap<Integer, Coin>();
			for (Coin i : coins) {
				mapCoins.put(i.getDenomination(), i);
			}
			for (Coin i : returncoins) {
				Coin coinUpdate = mapCoins.get(i.getDenomination());
				coinUpdate.setAmount(coinUpdate.getAmount() - i.getAmount());
				svc.update(coinUpdate);
			}
			addInfo("La cantidad de los billetes disponibles ha sido actualizada y se ha guardado un registro del retiro");
			mostrarResultado = true;
		} catch (Exception e) {
			error(e);
		}
	}

	private boolean validateOperation() {
		if (valuerequested <= 0) {
			valuerequested = null;
			addWarn("El valor a retirar debe ser maor que 0");
			return false;
		}
		return true;
	}

	public void newTransaction() {
		try {
			coins = svc.consult(new Coin(), "denomination");
			valuerequested = null;
			mostrarResultado = false;
		} catch (Exception e) {
			error(e);
		}
	}

	private int minCount(int[] nums, int target, int sum, int current, int count) {
		if (current > nums.length)
			return -1;
		if (sum == target) {
			return count;
		}
		if (sum + nums[current] <= target) {
			tempanswer.add(nums[current]);
			return minCount(nums, target, sum + nums[current], current + 1, count + 1);
		} else {
			return minCount(nums, target, sum, current + 1, count);
		}
	}

	private int[] combine(List<Coin> coins) {
		try {
			int sum = 0;
			for (Coin coin : coins) {
				sum += coin.getAmount();
			}
			int[] returnArray = new int[sum];
			int returnArrayIndex = 0;
			// System.out.println(coins.size());
			for (int i = 0; i < coins.size(); i++) {
				int count = coins.get(i).getAmount();
				while (count != 0) {
					returnArray[returnArrayIndex] = coins.get(i).getDenomination().intValue();
					returnArrayIndex++;
					count--;
				}
			}
			return returnArray;
		} catch (Exception e) {
			error(e);
		}
		return null;
	}

	public Integer getValuerequested() {
		return valuerequested;
	}

	public void setValuerequested(Integer valuerequested) {
		this.valuerequested = valuerequested;
	}

	public Integer getTotalcoins() {
		return totalcoins;
	}

	public void setTotalcoins(Integer totalcoins) {
		this.totalcoins = totalcoins;
	}

	public List<Coin> getReturncoins() {
		return returncoins;
	}

	public void setReturncoins(List<Coin> returncoins) {
		this.returncoins = returncoins;
	}

	public boolean isMostrarResultado() {
		return mostrarResultado;
	}

	public void setMostrarResultado(boolean mostrarResultado) {
		this.mostrarResultado = mostrarResultado;
	}

}