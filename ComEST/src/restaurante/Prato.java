//Já está
package restaurante;

import java.util.ArrayList;


/** Classe que representa um prato do sistema.
 * O prato deve ter um nome que o identifica. Deve ter ainda uma descrição, que é uma
 * explicação do que o prato contém ou o que é.
 * Cada prato deve ter um preço (sempre positivo) e um peso (sempre positivo).
 * Um prato pode ainda ter uma lista de opções.
 */
public class Prato {
	
	private String name;
	private String desc;
	private float price;
	private int weight;
	private ArrayList<Opcao> options = new ArrayList<Opcao>();
	
	
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public ArrayList<Opcao> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<Opcao> options) {
		this.options = options;
	}


	/** Indica se a opção indicada é válida para este prato
	 * @param o a opção a verificar
	 * @return true, se a opção é válida para o prato.
	 */
	
	//Retorna se tem a opcao valida
	public boolean temOpcao( Opcao o ) {
		return this.options.contains(o);
	}

}
