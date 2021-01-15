package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.tds.bean.Endereco;

public class EnderecoDAO {

private Connection conexao;
	
	/**
	 * Construtor que recebe a conexão com o banco
	 * @param conexao
	 */
	public EnderecoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	/**
	 * Cadastra um endereço no banco de dados
	 * @param endereco
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrar(Endereco endereco) throws ClassNotFoundException, SQLException {
		
		//Connection conexao = ConnectionFactory.getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement("INSERT INTO TB_ENDERECO (CD_ENDERECO, DS_LOGRADOURO,"
				+ " DS_ESTADO, NR_LOGRADOURO, NR_CEP) VALUES "
				+ " (SQ_TB_ENDERECO.NEXTVAL, ?, ?, ?, ?)" , new String[] {"CD_ENDERECO"});
		
		stmt.setString(1, endereco.getLogradouro());
		stmt.setString(2, endereco.getEstado());
		stmt.setString(3, endereco.getNumero());
		stmt.setString(4, endereco.getCep());
		
		stmt.executeUpdate();

		//Recuperar o ID gerado pela sequence
		ResultSet resultado = stmt.getGeneratedKeys();
		
		if (resultado.next()) {
			int codigo = resultado.getInt(1);
			endereco.setCodigo(codigo);
		}
		
		stmt.close();
		//conexao.close();
	}
}
