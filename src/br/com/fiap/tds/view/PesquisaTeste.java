package br.com.fiap.tds.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fiap.tds.factory.ConnectionFactory;

public class PesquisaTeste {
	
	
public static void main(String[] args) {
		
		try {
		
			
			Connection conexao = ConnectionFactory.getConnection();
			
			
			Statement stmt = conexao.createStatement();
						
			
			ResultSet resultados = stmt.executeQuery("SELECT * FROM TB_CLIENTE");
			
			
			while (resultados.next()){
			
				int codigo = resultados.getInt("CD_CLIENTE");
				String nome = resultados.getString("NM_CLIENTE");

				String data = resultados.getString("DT_NASCIMENTO");
				
				System.out.println(codigo + " " + nome + " " + data);
			}
			
			//Fechar
			stmt.close();
			conexao.close();
			
		}catch(ClassNotFoundException e) {
			System.out.println("Driver não encontrado!");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("Erro ao abrir o banco ou executar a pesquisa");
			e.printStackTrace();
		}
		
	}
	
}
