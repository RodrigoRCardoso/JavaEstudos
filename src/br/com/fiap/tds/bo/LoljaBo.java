package br.com.fiap.tds.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.tds.bean.lolja;
import br.com.fiap.tds.exception.ClienteNaoEncontradoException;
import br.com.fiap.tds.exception.DadoInvalidoException;



public class LoljaBo {
	
		private LoljaBo dao = new LoljaBo();
		

		public List<lolja> listar() throws ClassNotFoundException, SQLException{
			return dao.listar();
		}
		

		public lolja pesquisar(int codigo) throws ClassNotFoundException, SQLException {
			return dao.pesquisar(codigo);
		}
		

		public void remover(int codigo) throws ClassNotFoundException, SQLException, ClienteNaoEncontradoException {
			dao.remover(codigo);
		}
		

		public void atualizar(lolja lolja) throws DadoInvalidoException, ClassNotFoundException, SQLException, ClienteNaoEncontradoException {
			validar(lolja);
			dao.atualizar(lolja);
		}
		
		/**
		 * Valida o cargo antes de chamar a camada de persistência
		 * @param cargo
		 * @throws DadoInvalidoException 
		 * @throws SQLException 
		 * @throws ClassNotFoundException 
		 */
		public void cadastrar(lolja lolja) throws DadoInvalidoException, ClassNotFoundException, SQLException {
			validar(lolja);
			dao.cadastrar(lolja);
		}
		
		/**
		 * Método que valida os dados do cargo
		 * @param Cargo
		 */
		private void validar(lolja lolja ) throws DadoInvalidoException {
			if (lolja.getNome() == null || lolja.getNome().isEmpty() ) {
				throw new DadoInvalidoException("Nome é obrigatório");
			}
			if (lolja.getNome().length() > 40) {
				throw new DadoInvalidoException("Nome com no máximo 40 caracteres");
			}
		}
		
}
