package br.com.fiap.tds.bean;

/**
 *CREATE TABLE TB_LOLJA (
    CD_LOLJA NUMBER NOT NULL PRIMARY KEY,
    NM_LOLJA VARCHAR(40));
   
	CREATE SEQUENCE SQ_TB_LOJA INCREMENT BY 1 START WITH 1; 
 * @author rodri
 *
 */

public class lolja {
	
	private int codigo;
	
	private String nome;
	
	private String dataCadastro;
	
	public lolja() {}

	public lolja(String nome) {
		super();
		this.nome = nome;
	}

	public lolja(int codigo, String nome, String dataCadastro) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.dataCadastro = dataCadastro;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	

}
