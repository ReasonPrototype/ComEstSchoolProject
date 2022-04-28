//Já está
package restaurante;

/** Classe que representa uma opção de um prato.
 * Uma opção deve ter um nome, que indica o que ela é.
 * Deve ainda ter um custo (que pode ser negativo) e um peso (que pode ser negativo). 
 */
public class Opcao {
	
	private String name;
	private float price;
	private int weight;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}
