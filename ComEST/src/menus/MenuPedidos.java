package menus;

import java.util.List;

import consola.SConsola;
import restaurante.*;
import sistema.*;

/**
 * Classe que trata dos menus que simula a "aplicação movel"
 */
public class MenuPedidos {
	
	private SConsola consola = new SConsola("Menu ComEST", 630, 30, 500, 600);
	private ComEST server;    // servidor ao qual o simulador está ligado 
	
	/** Cria o menu da aplicação móvel
	 * @param s o servidor a que vai ligar
	 * @param u o utilizador a simular inicialmente 
	 */
	public MenuPedidos( ComEST s ){
		server = s;
	}

	/**
	 * menu que simula o telemóvel da aplicação
	 */
	public void menuPrincipal(){

		char opcao = 0;
		do {
			String menu = "Menu de consumidor\n" +  
					"1- Fazer pedido\n"+
					"2- Ver pedido\n"; 

			consola.clear();
			consola.println( menu );
			opcao = consola.readChar();
			switch( opcao ){
			case '1':
				fazerPedido();
				break;
			case '2':
				verPedido();
				break;
			default:
				consola.println( "opção inválida");
				consola.readLine();
			}
		} while( true );
	}


	/**
	 * Regista um código na aplicação 
	 */
	private void fazerPedido( ) {
		consola.clear();
		Restaurante r = escolherRestaurante();
		if( r == null )
			return;

		int pidx = 0;
		Pedido p = new Pedido(r);
		do {
			consola.clear();
			// Feito atribuir os valores adequados às variáveis 
			String nomeRest = r.getName();
			consola.println( nomeRest + "\n\nPratos já pedidos" );
			
			// TODO passar aqui a lista de escolhas 
			//printEscolhas( r.getPedidos() );
			
			// Feito apresentar a informação pedida
			float preco = p.getPrice();
			float taxaEntrega = p.calcularTaxa();
			float pesoTotal = p.getTotalWeight();
			float precoTotal = preco + taxaEntrega;
			consola.println( String.format("\nCusto pedido:  %6.2f€", preco ) );
			consola.println( String.format(  "Custo entrega: %6.2f€  por  %4dg", taxaEntrega, pesoTotal ) );
			consola.println( String.format(  "Total:         %6.2f€\n", precoTotal ) ); 
			consola.println( "Escolha um prato\n0 para confirmar o pedido\n-1 para cancelar\n" + 
					         "Pratos disponibilizados");
			
			// Feito listar os pratos disponibilizados pelos restaurante
			printPratos( r.getPratos() );
			consola.print("\nPrato: ");
			pidx = consola.readInt() ;
			// TODO se o índice escolhido for válido, apresentar o prato
			if( Math.abs(2 ) == 2 ) {
				// TODO se o índice é válido, apresentar o prato e respetivas opções 
				escolherOpcoesPrato( null, null );
			}
		} while( pidx != 0 && pidx != -1 );

		consola.clear();
		if( pidx == 0 ) {
			// TODO ver se o pedido tem pratos incluídos (substituir a condição, claro)
			if( Math.abs( 2 ) == 2 ) {
				// TODO adicionar o pedido ao sistema e ver o código
				String codigo = "Gerar o código de 6 dígitos";
				consola.println( "O seu pedido foi aceite!\n\nPara saber informações use o código " + codigo );
			} else {
				consola.println( "O seu pedido não for confirmado porque não tinha nenhum prato escolhido!" );
			}
		} else {
			consola.println( "O seu pedido foi cancelado a seu pedido!" );
		}
		consola.readLine();
	}

	/** apresenta, na consola, uma lista de escolhas
	 * @param escolhas a lista de escolhas a apresentar
	 */
	private void printEscolhas(List<Escolha> escolhas) {
		if( escolhas.size() == 0 )
			consola.println( "<Ainda sem pratos no pedido>" );
		// TODO para cada escolha imprimir
		String nomePrato ="Nome do prato";
		float precoPrato = 2.5f;
		consola.println( String.format("%-40s %6.2f€", nomePrato, precoPrato ) );
		// TODO para cada opção do prato imprimir
		String nomeOpcao = "nome da opção";
		float custoOpcao = 0.3f;
		consola.println( String.format("     %-35s %6.2f€", nomeOpcao, custoOpcao ) );
	}
	
	/** apresenta, na consola, uma lista de pratos
	 * @param pratos a lista de pratos a apresentar
	 */
	private void printPratos(List<Prato> pratos) {
		if( pratos.size() == 0 )
			consola.println( "<Sem pratos>" );
		// TODO para cada prato imprimir a informação solicitada
		for( int i = 0; i < pratos.size(); i++ ) {
			String nome = "nome prato";
			float preco = 2.5f;
			consola.println( String.format("%2d:%-40s %6.2f€", i+1, nome, preco ) );
		}
	}

	/** apresenta os restaurantes associados e pede ao utilizador para escolher um
	 * @return o restaurante selecionado ou null, caso não haja seleção
	 */
	private Restaurante escolherRestaurante() {
		// Feito listar os restaurantes do sistema  
		// O this.server vai ir a buscar os dados às colections
		for( int i = 0; i < this.server.getRestaurantes().size(); i++  ) {
			String nomeRest = this.server.getRestaurantes().get(i).getName();
			consola.println( (i+1) + ": " + nomeRest );
		}
		consola.print( "\nRestaurante: " );
		int ridx = consola.readInt() - 1;
		// Feito ver se o escolhido é válido
		if( ridx < 0 || ridx >= this.server.getRestaurantes().size()) {
			consola.println( "Restaurante inválido!" );
			consola.readLine();
			return null;
		}		
	
		// Feito selecionar o restaurante escolhido
		Restaurante r = this.server.getRestaurantes().get(ridx);
		String nome = r.getName();
		String desc = r.getDesc();
		consola.println( "\n\n" + nome + "\n" + desc + "\n\nOferta:");
		// Feito apresentar os pratos do restaurante
		printPratos( r.getPratos() );
		
		// Feito se confirmar é preciso retornar o restaurante
		consola.println( "Escolher o restaurante? (0: confirmar)" );
		int sim = consola.readInt();
		return sim == 0? r: null;
	}
	
	/** Apresenta as opções do prato selecionado e pede para confirmar o prato como 
	 * sendo escolhido
	 * @param pedido o pedido em processamento
	 * @param prato o prato a ser observado e eventualemente escolhido
	 */
	private void escolherOpcoesPrato(Pedido pedido, Prato prato) {
		// Feito usar este array booleano (que deve ter o mesmo tamanho da lista de opções)
		//      para saber quais as opções que estão selecionadas
		int numOpcoes = prato.getOptions().size();
		boolean select[] = new boolean[ numOpcoes ];
		
		// Feito apresentar info do prato
		String nomePrato = prato.getName();
		String descPrato = prato.getDesc();
		float precoPrato = prato.getPrice();
		String infoPrato = nomePrato + "\n" + descPrato +
				           "\nPreço base: "+ String.format("%6.2f€",precoPrato) + "\n\n" +
		                   "Selecione (desselecione) as opções pretendidas\n" +
				           "0 para confirmar\n-1 para cancelar\n";
		
		Escolha e = new Escolha(prato);
		int oidx = 0;
		do {
			consola.clear();
			consola.println( infoPrato );
			float precoTotal = e.getPreco();
			consola.println( String.format("Custo: %6.2f €", precoTotal ) );
			for( int i = 0; i < numOpcoes; i++  ) {
				Opcao o = prato.getOptions().get(i);
				consola.print( select[i]? "(o) ": "( ) " );
				String nomeOpcao = o.getName();
				float custoOpcao = o.getPrice();
				consola.println( String.format("%2d: %-35s %6.2f€", i+1, nomeOpcao, custoOpcao ) );
			}
			consola.println( "Opção: ");
			oidx = consola.readInt();
			if( oidx > 0 && oidx <= numOpcoes ) {
				int idx = oidx-1;
				select[idx] = !select[idx];
				if( select[idx] ) {
					// Feito adicionar opção à seleção
					e.addOption(prato.getOptions().get(idx));
				}	
				else {
					// Feito remover opção à seleção
					e.removeOption(prato.getOptions().get(idx));
				}
					
			}
		} while( oidx != 0 && oidx != -1 );
		if( oidx == 0 ) {
			// Feito se confirmar, adicionar o prato e respetivas opções selecionadas ao pedido
			pedido.addEscolha(e);
		}
			
	}

	/** Apresenta informação sucinta sobre um dado pedido
	 */
	private void verPedido() {
		consola.clear();
		consola.println( "Código? " );
		String code = consola.readLine();
		
		// Feito ver qual o pedido associado ao código, se algum
		Pedido p = this.server.getPedidos().getOrDefault(code, null);
		if( p == null ) {
			consola.println("Pedido inexistente!");
			consola.readLine();
			return;			
		}
		
		consola.clear();
		// Feito apresentar informação do pedido e restaurante
		String codigo = code;
		String nomeRestaurante = p.getRestaurante().getName();
		float precoTotal = p.getPrice();
		consola.println( "Pedido " + codigo +
				         "\nRestaurante:" + nomeRestaurante +
				         "\nPreço: " + precoTotal + "€\n\n");

		// Feito apresentar sumário das escolhas do pedido
		for( Prato pt:p.getRestaurante().getPratos()) {
			String nomePrato = pt.getName();
			consola.println( nomePrato );
			for( Opcao e:pt.getOptions()) {
				String nomeOpcao = e.getName();
				consola.println("    " + nomeOpcao );
			}
		}
		consola.readLine();
	}

}
