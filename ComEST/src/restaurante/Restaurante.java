//Já está
package restaurante;

import java.util.ArrayList;

import sistema.Pedido;


/** Representa um restaurante
 * Cada restaurante deve ter um nome e uma descrição. A descrição deve ser uma apresentação
 * do restaurante indicando o seu tipo e filosofia.
 * Deve ainda ter uma lista com os pratos que disponibiliza. 
 * O restaurante deve ainda ter uma lista dos pedidos que os clientes já colocaram.
 */
public class Restaurante {
	
	private String name;
	private String desc;
	private ArrayList<Prato> pratos = new ArrayList<Prato>();
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ArrayList<Prato> getPratos() {
		return pratos;
	}
	public void setPratos(ArrayList<Prato> pratos) {
		this.pratos = pratos;
	}
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	public void addPedido(Pedido p) {
		this.pedidos.add(p);
	}

	/** indica se um dado prato pertence a este restaurante
	 * @param p o prato a verificar
	 * @return true, se o prato faz parte da lista do restaurante
	 */
	public boolean temPrato( Prato p ) {
		return this.pratos.contains(p);
	}
}
