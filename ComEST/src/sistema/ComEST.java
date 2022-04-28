package sistema;

import java.util.ArrayList;
import java.util.HashMap;

import restaurante.Restaurante;

/** Classe que representa todo o sistema
 * Deve ter uma lista de restaurantes suportados e outra dos pedidos efetuados
 * Deve ser a responsável por atribuir o código a um pedido, quando este é adicionado ao sistema 
 */
public class ComEST {
	
	private ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
	private HashMap<String, Pedido> pedidos = new HashMap<String, Pedido>();
	
	public ArrayList<Restaurante> getRestaurantes() {
		return restaurantes;
	}
	public void setRestaurantes(ArrayList<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}
	public HashMap<String, Pedido> getPedidos() {
		return pedidos;
	}
	
	public void addPedido(String codigo, Pedido p) {
		this.pedidos.put(codigo, p);
	}
	
	public float getTotal() {
		float total = 0;
		for (Pedido p:this.pedidos.values()) {
			total += p.getPrice();
		}
		
		return total;
	}
	
	
	
}
