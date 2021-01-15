package br.com.fiap.tds.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.tds.bean.Cliente;
import br.com.fiap.tds.dao.ClienteDAO;
import br.com.fiap.tds.exception.ClienteNaoEncontradoException;
import br.com.fiap.tds.exception.DadoInvalidoException;

public class ClienteBo {
	
	
	private Connection conexao;
	private ClienteDAO funcionarioDao;
	private EnderecoBo enderecoBo;
	
	public ClienteBo(Connection conexao) throws ClassNotFoundException, SQLException  {
		//conexao = ConnectionFactory.getConnection();
		this.conexao = conexao;
		funcionarioDao = new ClienteDAO(conexao);
		enderecoBo = new EnderecoBo(conexao);
	}
	

	public void remover(int codigo) throws ClassNotFoundException, SQLException, 
										DadoInvalidoException, ClienteNaoEncontradoException  {
		Cliente cliente = funcionarioDao.pesquisar(codigo);
		
		if (cliente != null && cliente.isAtivo())
			throw new DadoInvalidoException("Funcionário deve estar desativado");
		
		funcionarioDao.remover(codigo);
	}
	

	public List<Cliente> buscar() throws ClassNotFoundException, SQLException{
		return funcionarioDao.listar();
	}
	

	public List<Cliente> buscar(String nome) throws ClassNotFoundException, SQLException{
		return funcionarioDao.buscarPorNome(nome);
	}
	

	public Cliente buscar(int codigo) throws ClassNotFoundException, SQLException {
		return funcionarioDao.pesquisar(codigo);
	}
	
	
	public void cadastrar(Cliente cliente) 
			throws DadoInvalidoException, ClassNotFoundException, SQLException {
		
		validar(cliente);
		
		//transação para cadastrar os dois ou não cadastrar nada
		
		//Desabilitar o auto-commit
		conexao.setAutoCommit(false);
		
		//funcionario.setAtivo(true); // funcionário sempre deve ser cadastro como verdadeiro
		enderecoBo.cadastrar(cliente.getEndereco());
		funcionarioDao.cadastrar(cliente);
		
		try {
		
			conexao.commit();
			
		} catch(Exception e) {
			conexao.rollback();
			throw new SQLException("Erro ao realizar o commit");
		} finally {
			//Volta ao padrão de auto-commit
			conexao.setAutoCommit(true);
		}
	}
	
	/**
	 * Valida os dados do funcionário antes de cadastrar ou atualizar
	 * @param funcionario
	 * @throws DadoInvalidoException 
	 */
	private void validar(Cliente cliente) throws DadoInvalidoException {
		//Validar se o nome está presente e tem menos do que 80 caracteres
		if (cliente.getNome() == null || 
				cliente.getNome().equals("") || cliente.getNome().length() > 80) {
			throw new DadoInvalidoException("Nome é obrigatório ou deve ser menor do que 80");
		}
		if (cliente.getDataNascimento() == null || cliente.getDataNascimento().equals("")) {
			throw new DadoInvalidoException("Data de nascimento é obrigatório");
		}
		if (cliente.getLolja() == null || cliente.getLolja().getCodigo() == 0) {
			throw new DadoInvalidoException("Cargo é obrigatório");
		}

	}
	
	public void atualizar(Cliente cliente) throws ClassNotFoundException, 
						SQLException, ClienteNaoEncontradoException, DadoInvalidoException {
		
		validar(cliente);		
		funcionarioDao.atualizar(cliente);
	}

	
}
