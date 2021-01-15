package br.com.fiap.tds.view;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.fiap.tds.bo.ClienteBo;
import br.com.fiap.tds.exception.ClienteNaoEncontradoException;
import br.com.fiap.tds.exception.DadoInvalidoException;
import br.com.fiap.tds.factory.ConnectionFactory;

public class RemoveTeste {
	
	
public static void main(String[] args) {
		
		
		int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código"));
		Connection conexao = null;
		
		try {
			
			conexao = ConnectionFactory.getConnection();
			ClienteBo bo = new ClienteBo(conexao);
			
			
			bo.remover(codigo);
			
			JOptionPane.showMessageDialog(null, "Funcionário removido!");
			
		}catch(DadoInvalidoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}catch(ClienteNaoEncontradoException e) {
			JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro..");
		}finally {
			try {
				if (conexao != null)
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
