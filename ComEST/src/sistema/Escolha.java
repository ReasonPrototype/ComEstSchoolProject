//Já está
package sistema;

import java.util.ArrayList;
import restaurante.Opcao;
import restaurante.Prato;

/** Representa um prato escolhido pelo cliente e respetivas opções selecionadas
 * Deve ter a indicação de qual o prato escolhido e uma lista com as opções selecionadas.
 * Ao adicionar uma opção dever verificar se a mesma faz parte das opções suportadas pelo prato. 
 */
public class Escolha {

	private ArrayList<Opcao> options = new ArrayList<Opcao>();
	private Prato pratinho;
	
	//Verificar se o prato foi escolhido
	public Escolha (Prato p) {
		this.pratinho = p;
	}
	
	//Se o pratinho suportar opcao adiciona
	public void addOption(Opcao o) {
		
		if (this.pratinho.temOpcao(o)) {
			this.options.add(o);
		}
	}
	
	public void removeOption(Opcao o) {
		this.options.remove(o);
	}
	
	/** Retorna o peso total da escolha, ou seja,
	 * o peso do prato mais o peso de cada uma das opções selecionadas.
	 * @return o peso total da escolha
	 */
	public int getPeso() {
		
		int pesoPrato = this.pratinho.getWeight();
		int pesoOption = 0;
		for (Opcao o: this.options) {
			pesoOption += o.getWeight();
		}
		return pesoPrato+pesoOption;
		
	}
	
	/** Retorna o preço total da escolha, ou seja,
	 * o preço do prato mais o preço de cada uma das opções selecionadas.
	 * @return o preço total da escolha
	 */
	public float getPreco( ) {
		
		float precoPrato = this.pratinho.getPrice();
		float precoOption = 0;
		for (Opcao o: this.options) {
			precoOption += o.getPrice();
		}
		return precoPrato+precoOption;
		
	}
	
	public Prato getPrato() {
		return this.pratinho;
	}
	
	
	
}
