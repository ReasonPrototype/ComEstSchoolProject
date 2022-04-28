package menus;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import consola.SConsola;
import restaurante.Opcao;
import restaurante.Restaurante;
import sistema.ComEST;
import sistema.Escolha;
import sistema.Pedido;

/**
 * Classe que trata dos menus do servidor
 */
public class MenuServidor {
	
	private SConsola consola = new SConsola("Menu do TESTCovid", 30, 30, 500, 600);
	private ComEST server;

	/** Cria a interface para o menu principal
	 * @param s o servidor para o qual se está a cria a interface
	 */
	public MenuServidor( ComEST s ){
		server = s;
	}

	/**
	 * menu principal da aplicação
	 */
	public void menuPrincipal(){
		char opcao = 0;
			
		do {
			String menu = "Menu Administrador\n\n" + 
					"1- Ver Pedidos totais\n"+
					"2- Ver pedidos por restaurante\n" + 
					"\n0- Sair";
			consola.clear();
			consola.println( menu );
			opcao = consola.readChar();
			switch( opcao ){
			case '1':
				verTodosPedidos();
				break;
			case '2':
				verPedidosRestaurante();
				break;
			case '0': break;
			default:
				consola.println( "opção inválida");
				consola.readLine();
			}
		} while( opcao != '0' );

		consola.close();					// desligar o menu da central	
		System.exit( 0 );
	}

	/** Apresenta todos os pedidos existentes no sistema
	 * 
	 */
	private void verTodosPedidos() {
		// FEITO passar os pedidos de todo o sistema
		verPedidos( this.server.getTodosPedidos() );		
	}

	/** Apresenta todos os pedidos presentes na lista
	 * 
	 */
	private void verPedidos( Collection<Pedido> pedidos ) {
		consola.clear();
		for( Pedido p : pedidos ) {
			// FEITO preencher a informação corretamente
			String codigo = this.server.getCodigoPedido(p).get();
			String nomeRest = p.getRestaurante().getName();
			int peso = p.getTotalWeight();
			float preco = p.getPrice();
			float taxa = p.calcularTaxa();
			consola.println( String.format("%6s - %-30s  %4dg  %6.2fe  %6.2f€",
					codigo, nomeRest,  peso,
					preco, taxa) );
		}
		consola.print("\nDeseja ver algum pedido em detalhe?\nInsira o código: ");
		String cod = consola.readLine();
		if( cod.length() == 6) {
			// FEITO visualizar o pedido se houver código
			verPedido( this.server.getPedidoByCode(cod) );
		}
	}

	/**
	 *  
	 */
	private void verPedidosRestaurante() {
		consola.clear();		
		for(int i = 0; i < this.server.getRestaurantes().size(); i++) {
			Restaurante r = this.server.getRestaurantes().get(i);
			String nomeRest = r.getName();
			consola.println( (i+1) + ": " + nomeRest );
		}

		consola.print( "\nRestaurante: " );
		int ridx = consola.readInt() - 1;
		// FEITO verificar se o vaor introduzido é válido
		if( ridx < 0 || ridx >= this.server.getRestaurantes().size() )
			return;
		
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		for(Restaurante r: this.server.getRestaurantes()) {
			for(Pedido p: r.getPedidos()) {
				pedidos.add(p);
			}
		}
		// FEITO listar os pedidos do restaurante
		verPedidos( pedidos );
	}

	/** Lista as informações do pedido
	 * @param p o pedido que se pretende listar
	 */
	private void verPedido( Pedido p ) {
		if( p == null ) {
			consola.println("Pedido inexistente!");
			consola.readLine();
			return;			
		}
		
		consola.clear();
		// FEITO apresentar as infos corretas
		String codigo = this.server.getCodigoPedido(p).get();
		String nomeRest = p.getRestaurante().getName();
		float precoTotal = p.getPrice();
		float precoPratos = p.getPrecoPratos();
		float custoEntrega = p.calcularTaxa();
		int peso = p.getTotalWeight();
		consola.println( "Pedido " + codigo + "\nRestaurante:" + nomeRest +"\n");
		consola.println( String.format( "Preço        : %6.2f€", precoTotal ) ); 
		consola.println( String.format( "Preço  pratos: %6.2f€", precoPratos ) );  
		consola.println( String.format( "Custo entrega: %6.2f€  (%4dg)", custoEntrega, peso ) );
		
		for(Escolha e: p.getEscolhas()) {
			String nomePrato = e.getPrato().getName();
			float precoPrato = e.getPrato().getPrice();
			int pesoPrato = e.getPrato().getWeight();
			consola.println( String.format( "%-40s  %6.2f€  %4dg", nomePrato, precoPrato, peso ));
			for(Opcao o: e.getPrato().getOptions()) {
				String nomeOpcao = o.getName();
				float custoOpcao = o.getPrice();
				int pesoOpcao = o.getWeight();
				consola.println( String.format("     %-35s  %6.2f€  %4dg", nomeOpcao, custoOpcao, pesoOpcao ) );
			}
			
		}
		
		consola.readLine();
	
	}
}
