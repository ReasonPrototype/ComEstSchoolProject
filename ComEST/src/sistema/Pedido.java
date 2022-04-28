//Já está
package sistema;

import java.util.ArrayList;
import restaurante.Restaurante;

/** Representa um pedido de comida. 
 * Um pedido deve indicar qual o restaurante selecionado e ter uma lista de escolhas.
 * Quando se adiciona uma escolha ao pedido este deve verificar se o prato é do restaurante 
 * ao qual o pedido está associado.
 */
public class Pedido {
	
	private Restaurante restaurante;
	private ArrayList<Escolha> escolhas = new ArrayList<Escolha>();
	

	//Iniciar o restaurante
	public Pedido (Restaurante r) {
		this.restaurante = r;
	}
	
	
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public ArrayList<Escolha> getEscolhas() {
		return escolhas;
	}
	public void setEscolhas(ArrayList<Escolha> escolhas) {
		this.escolhas = escolhas;
	}


	/** indica se o pedido não tem escolhas associadas
	 * @return true, se o pedido não tiver qualquer escolha
	 */
	public boolean estaVazio() {
		return this.escolhas.isEmpty();
	}
	
	
	public void addEscolha(Escolha e) {
		//Verifica se a escolha pertence ao restaurante
		if (this.restaurante.temPrato(e.getPrato())) {
			this.escolhas.add(e);
		}
	}
	
	public float getPrice() {
		float total = 0;
		
		for(Escolha e:this.escolhas) {
			total += e.getPreco();
		}
		
		return total;
	}
	
	public float getPrecoPratos() {
		return this.getPrice() + this.calcularTaxa();
	}
	
	
	public int getTotalWeight() {
		int total = 0;
		
		for(Escolha e:this.escolhas) {
			total += e.getPeso();
		}
		
		return total;
	}
	/**
	 * 
	 * Peso total (g) Tarifa a usar (€)
		[0, 1500] 2.5
		]1500, 3000] 4.5
		]3000, 4000] 5.0
		> 4000 6.0 base
		+ 1.0 por kilo acima dos 4kg
	 * @return
	 */
	public float calcularTaxa() {
		int pesog = this.getTotalWeight();
		
		if ( pesog <= 1500 ) {
			return 2.5f;
		} 
		
		if ( pesog <= 3000) {
			return 4.5f;
		}
		
		if ( pesog <= 4000) {
			return 5.0f;
		}
		
		int pesok = pesog / 1000;
		
		return 6.0f*pesok;
	}
	
	
	
}
