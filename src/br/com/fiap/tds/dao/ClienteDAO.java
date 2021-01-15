package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.tds.bean.Cliente;
import br.com.fiap.tds.bean.lolja;
import br.com.fiap.tds.exception.ClienteNaoEncontradoException;

public class ClienteDAO {

	
private Connection conexao;
	
	/**
	 * Recebe a conexão com o banco de dados
	 * @param conexao
	 */
	public ClienteDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	private LoljaDAO loljaDao = new LoljaDAO();
	
	//Método privado que recebe um ResultSet e retorna um objeto do tipo funcionário
	private Cliente parse(ResultSet resultado) throws SQLException, ClassNotFoundException {
		
		int codigo = resultado.getInt("CD_CLIENTE");
		String nome = resultado.getString("NM_CLIENTE");
		String data = resultado.getString("DT_NASCIMENTO");
		int codigoLolja = resultado.getInt("CD_LOLJA"); //null -> 0
		
		Cliente lolja = null;
		//Valida se o funcionário possui um cargo, se tiver carrega o relacionamento
		if (codigoLolja != 0)
			lolja = loljaDao.pesquisar(codigoLolja);
		
		Cliente cliente = new Cliente(codigo, nome, data);
		
		return cliente;
	}
	
	
	/**
	 * Pesquisa os funcionários por parte do nome
	 * @param nome
	 * @return List<Funcionario> funcionários encontrados
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Cliente> buscarPorNome(String nome) throws ClassNotFoundException, SQLException{
		//Obter a conexão
		//Connection conexao = ConnectionFactory.getConnection();
		
		//Prepared Statement
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT * FROM TB_CLIENTE WHERE NM_CLIENTE like ?");
		
		//Passar o valor para a query
		stmt.setString(1, "%" +  nome + "%");
		
		//Criar a lista de funcionários
		List<Cliente> lista = new ArrayList<Cliente>();
		
		//Executar a query e obter os resultados
		ResultSet resultado = stmt.executeQuery();
		
		//Percorrer os resultados
		while (resultado.next()) {
		
			//Obter os dados das colunas
			Cliente cliente = parse(resultado);
			//Criar o objeto funcionário e adicionar na listar
			lista.add(cliente);
		}
		
		//Fechar
		stmt.close();
		//conexao.close();
		
		//Retornar a lista
		return lista;
	}
	
	/**
	 * Remove um funcionário pelo código
	 * @param id
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ClienteNaoEncontradoException 
	 */
	public void remover(int id) 
			throws ClassNotFoundException, SQLException, ClienteNaoEncontradoException {
		//Obter a conexão
		//Connection conexao = ConnectionFactory.getConnection();
		
		//Prepared Statement
		PreparedStatement stmt = conexao
				.prepareStatement("DELETE FROM TB_CLIENTE WHERE CD_CLIENTE = ?");
		
		//Setar o código na query
		stmt.setInt(1, id);
		
		//Executar a query
		int qtd = stmt.executeUpdate();
		
		//Fechar
		stmt.close();
		//conexao.close();

		//Verifica se removeu um funcionário
		if (qtd == 0)
			throw new ClienteNaoEncontradoException();
		
	}
 

	public void atualizar(Cliente cliente) 
			throws ClassNotFoundException, SQLException, ClienteNaoEncontradoException {
			
	
		PreparedStatement stmt = conexao.prepareStatement("UPDATE TB_CLIENTE SET"
			+ " NM_CLIENTE= ?, DT_NASCIMENTO =?, CD_LOLJA = ?"
			+ " WHERE CD_CLIENTE = ?");
		
		
		stmt.setString(1, cliente.getNome());
		stmt.setString(3, cliente.getDataNascimento());
		stmt.setInt(5, cliente.getLolja().getCodigo());
		stmt.setInt(6, cliente.getCodigo());
		
		//Executar a query (retorna a quantidade de linhas que foram modificadas)
		int qtd = stmt.executeUpdate();
		
		//Fechar a conexão
		stmt.close();
		//conexao.close();

		//Verifica se modificou alguma linha no banco de dados
		if (qtd == 0) {
			throw new ClienteNaoEncontradoException();
		}
	}
	

	public List<Cliente> listar() throws ClassNotFoundException, SQLException{

		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM TB_CLIENTE");
		
		//Criar a lista de Funcionário
		List<Cliente> lista = new ArrayList<Cliente>();
		
		//Executar a query
		ResultSet resultado = stmt.executeQuery();
		
		//Percorrer todos os registros encontrados
		while(resultado.next()) {		
			
			//Chama o método que obtem o funcionário do resultset
			Cliente cliente = parse(resultado);
			lista.add(cliente);
			
		}
		
		//Fechar
		stmt.close();
		//conexao.close();
		
		//Retornar a lista
		return lista;
	}
	
	
	public Funcionario pesquisar(int codigo) throws ClassNotFoundException, SQLException{
		//Conexão com o banco
		//Connection conexao = ConnectionFactory.getConnection();
		//Prepared Statement
		PreparedStatement stmt = conexao
			.prepareStatement("SELECT * FROM TB_CLIENTE WHERE CD_CLIENTE = ?");
		
		//Passar o codigo para a query
		stmt.setInt(1, codigo);
		
		//Executar 
		ResultSet resultado = stmt.executeQuery();
		
		Cliente cliente = null;
		
		//Valida se encontrou o funcionário
		if (resultado.next()) {
		
			//Chamar o método que obtem o funcionário do resultset
			cliente = parse(resultado);
			
		}
		
		//Fechar
		stmt.close();
		//conexao.close();		
		
		//Retornar o funcionário
		return cliente;
	}
	
	/**
	 * Cadastra um funcionário no banco de dados
	 * @param funcionario
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void cadastrar(Cliente cliente) throws SQLException, ClassNotFoundException {

		PreparedStatement stmt = conexao.prepareStatement("INSERT INTO TB_CLIENTE "
			+ " (CD_CLIENTE, NM_CLIENTE,DT_NASCIMENTO,CD_, CD_ENDERECO) "
			+ " VALUES (SQ_TB_CLIENTE.NEXTVAL, ?, ?, ?, ?, ?, ?)");
		
	
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getDataNascimento()); 
		stmt.setInt(3, cliente.getLolja().getCodigo()); 
		stmt.setInt(4, cliente.getEndereco().getCodigo());
		
		//Executar
		stmt.executeUpdate();
		
		//Fechar
		stmt.close();
		//conexao.close();
	}
	
}	
