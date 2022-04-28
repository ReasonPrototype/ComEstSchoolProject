package sistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;

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
	
	// Utiliza a interface Optional para ser seguro utilizar sem ter null exception
	public Optional<String> getCodigoPedido( Pedido p ){
		for(Entry<String, Pedido> entry: this.pedidos.entrySet()) {
			if (entry.getValue().equals(p)) {
				return Optional.of(entry.getKey());
			}
		}
	    
		return Optional.empty();
	}
	
	public Pedido getPedidoByCode(String code) {
		return this.pedidos.get(code);
	}
	
	public Collection<Pedido> getTodosPedidos(){
		return this.pedidos.values();
	}
	
}
