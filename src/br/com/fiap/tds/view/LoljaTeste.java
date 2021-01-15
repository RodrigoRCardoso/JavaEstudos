package br.com.fiap.tds.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.fiap.tds.bean.lolja;
import br.com.fiap.tds.bo.LoljaBo;
import br.com.fiap.tds.factory.ConnectionFactory;

public class LoljaTeste {
	
	
	public static void main(String[] args) {
		
		
		LoljaBo bo = new LoljaBo();
		
	
		
		try {

			
			lolja lolja = new lolja("Wallmart");
			bo.cadastrar(lolja);
			System.out.println("Loja registrada!");
			
			
			lolja = bo.pesquisar(1);
			System.out.println(lolja);

			
			System.out.println("LISTAR:");
			List<lolja> lista = bo.listar();
			for(lolja item : lista)
				System.out.println(item);
			
			
			lolja.setNome("Carrefour");
			bo.atualizar(lolja);
			System.out.println("loja atualizada");
			
			
			bo.remover(5);
			System.out.println("loja removida");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
