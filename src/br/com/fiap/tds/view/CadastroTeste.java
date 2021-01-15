package br.com.fiap.tds.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fiap.tds.factory.ConnectionFactory;

public class CadastroTeste {
	
public static void main(String[] args) {
		
		try {
			
			
			Connection conexao = ConnectionFactory.getConnection();
						
		
			Statement stmt = conexao.createStatement();
			
			stmt.executeUpdate("INSERT INTO TB_CLIENTE (CD_CLIENTE, NM_CLIENTE, "
					+ " DT_NASCIMENTO,) VALUES (SQ_TB_CLIENTE.NEXTVAL, "
					+ "'RODRIGO 3', 1, '01/12/2000' )");
			
			System.out.println("Funcionário registrado!");
					
			
			conexao.close();
			
		}catch(ClassNotFoundException e) {
			System.out.println("Driver JDBC não encontrado!");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("Não foi possível conectar!");
			e.printStackTrace();
		}
		
	}
		
}
