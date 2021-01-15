package br.com.fiap.tds.bean;

/* 
 * CREATE TABLE TB_ENDERECO (
    CD_ENDERECO NUMBER NOT NULL PRIMARY KEY,
    DS_LOGRADOURO VARCHAR(50) NOT NULL,
    NR_LOGRADOURO VARCHAR(10) NOT NULL,
    DS_ESTADO VARCHAR(2) NOT NULL, 
    NR_CEP VARCHAR(8));
   
    CREATE SEQUENCE SQ_TB_ENDERECO INCREMENT BY 1 START WITH 1;
 	@author rodri
 */

public class Endereco {
	
	private int codigo;
	
	private String logradouro;
	
	private String estado;
	
	private String cep;
	
	private String numero;

	public Endereco() {}

	public Endereco(int codigo,String logradouro, String estado, String cep, String numero) {
		super();
		this.codigo = codigo;
		this.logradouro = logradouro;
		this.estado = estado;
		this.cep = cep;
		this.numero = numero;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	

}
