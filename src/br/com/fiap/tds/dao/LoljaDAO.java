package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import br.com.fiap.tds.bean.Cliente;
import br.com.fiap.tds.bean.lolja;
import br.com.fiap.tds.exception.EntidadeNaoEncontradaException;
import br.com.fiap.tds.factory.ConnectionFactory;

public class LoljaDAO {
	
	public void atualizar(lolja lolja) throws ClassNotFoundException, SQLException, EntidadeNaoEncontradaException{
		
		Connection conexao = ConnectionFactory.getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement("UPDATE TB_LOLJA SET NM_LOLJA = ? WHERE CD_LOLJA = ?");
		
		stmt.setString(1, lolja.getNome());
		stmt.setInt(2, lolja.getCodigo());
		
		int qtd = stmt.executeUpdate();
		
		stmt.close();
		conexao.close();
		
		if(qtd == 0) 
			throw new EntidadeNaoEncontradaException();
		
	}
	/**
	 * Remove um cargo pela PK
	 * @param codigo
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws EntidadeNaoEncontradaException 
	 */
		public void remover(int codigo) throws SQLException, ClassNotFoundException, EntidadeNaoEncontradaException {
				
				Connection conexao = ConnectionFactory.getConnection();
				
				PreparedStatement stmt = conexao.prepareStatement("DELETE FROM TB_LOLJA WHERE CD_LOLJA = ?");
				
				stmt.setInt(1, codigo);
				
				int qtd = stmt.executeUpdate();
			
				stmt.close();
				conexao.close();
				
				if (qtd == 0)
					throw new EntidadeNaoEncontradaException();
		
	}
		/**
		 * Cadastra um cargo 
		 * @param cargo
		 * @throws SQLException 
		 * @throws ClassNotFoundException 
		 */
		public void cadastrar(lolja lolja) throws ClassNotFoundException, SQLException {
			
			Connection conexao = ConnectionFactory.getConnection();
			
			PreparedStatement stmt = conexao.prepareStatement(
					"INSERT INTO TB_LOLJA(CD_LOLJA, NM_LOLJA, DT_LOLJA) VALUES "
					+ " (SQ_TB_LOLJA.NEXTVAL, ?, sysdate)");
			
			stmt.setString(1, lolja.getNome());
			//stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
			
			stmt.executeUpdate();
			
			stmt.close();
			conexao.close();
			
		}
		/**
		 * Recupera um cargo pela PK
		 * @param codigo
		 * @return Cargo
		 * @throws SQLException 
		 * @throws ClassNotFoundException 
		 */
		public Cliente pesquisar(int codigo) throws ClassNotFoundException, SQLException {
			
			Connection conexao = ConnectionFactory.getConnection();
			
			PreparedStatement stmt = conexao.prepareStatement(
					"SELECT * FROM TB_LOLJA WHERE CD_LOLJA = ?");
			
			stmt.setInt(1, codigo);
			
			ResultSet resultado = stmt.executeQuery();
			
			Cliente cargo = null;
			
			if (resultado.next())
				cargo = parse(resultado);
			
			stmt.close();
			conexao.close();
			
			return cargo;
		}
		/**
		 * Recupera todos os cargos cadastrados
		 * @return List<Cargo> 
		 * @throws SQLException 
		 * @throws ClassNotFoundException 
		 */
		public List<Lolja> listar() throws ClassNotFoundException, SQLException{
			
			Connection conexao = ConnectionFactory.getConnection();
			
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM TB_LOLJA");
			
			ResultSet resultado = stmt.executeQuery();
			
			List<Lolja> lista = new ArrayList<Lolja>();
			
			while (resultado.next()) {
				lolja = parse(resultado);
				lista.add(lolja);
			}
			
			stmt.close();
			conexao.close();
			
			return lista;
			
		}
		/**
		 * Recupera um objeto do tipo Cargo de um ResultSet
		 * @param resultado
		 * @return Cargo
		 * @throws SQLException 
		 */
		private Lolja parse(ResultSet resultado) throws SQLException {
			int codigo = resultado.getInt("CD_LOLJA");
			String nome = resultado.getString("NM_LOLJA");
			String data = resultado.getString("DT_LOLJA");
			
			lolja lolja = new lolja(codigo, nome, data);
			
			return lolja;
		}
	

}
