package br.com.fiap.tds.bo;

import java.sql.Connection;
import java.sql.SQLException;



import br.com.fiap.tds.bean.Endereco;
import br.com.fiap.tds.dao.EnderecoDAO;
import br.com.fiap.tds.exception.DadoInvalidoException;

public class EnderecoBo {
			
private Connection conexao;
	
	private EnderecoDAO enderecoDao;
	
	public EnderecoBo(Connection conexao) throws ClassNotFoundException, SQLException {
		//conexao = ConnectionFactory.getConnection();
		this.conexao = conexao;
		enderecoDao = new EnderecoDAO(conexao);
	}
	
	public void cadastrar(Endereco endereco) 
			throws DadoInvalidoException, ClassNotFoundException, SQLException {
		validar(endereco);
		enderecoDao.cadastrar(endereco);
	}
	
	private void validar(Endereco endereco) throws DadoInvalidoException {
		if (StringUtils.isNullOrEmpty(endereco.getLogradouro())) {
			throw new DadoInvalidoException("Logradouro obrigatório");
		}
		if (StringUtils.isNullOrEmpty(endereco.getEstado())) {
			throw new DadoInvalidoException("Estado é obrigatório");
		}
		if (endereco.getEstado().length() != 2) {
			throw new DadoInvalidoException("Estado deve ter dois caracteres");
		}
		if (StringUtils.isNullOrEmpty(endereco.getCep())) {
			throw new DadoInvalidoException("CEP é obrigatório");
		}
		if (endereco.getCep().length() != 8) {
			throw new DadoInvalidoException("CEP deve conter 8 caracteres");
		}		
	}
		
}	
