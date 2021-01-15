package br.com.fiap.tds.bean;
/**	
 *CREATE TABLE TB_CLIENTE (
    CD_CLIENTE NUMBER NOT NULL PRIMARY KEY,
    NM_CLIENTE VARCHAR(80) NOT NULL,
    DT_NASCIMENTO DATE NOT NULL);
   
   
	CREATE SEQUENCE SQ_TB_CLIENTE INCREMENT BY 1 START WITH 1;


ALTER TABLE TB_CLIENTE ADD CD_LOLJA NUMBER;
  
   ALTER TABLE TB_CLIENTE ADD CONSTRAINT FK_CD_LOLJA
   FOREIGN KEY (CD_LOLJA) REFERENCES TB_LOLJA (CD_LOLJA);

   ALTER TABLE TB_CLIENTE ADD CD_ENDERECO NUMBER;
  
   ALTER TABLE TB_CLIENTE ADD CONSTRAINT FK_CD_ENDERECO
   FOREIGN KEY (CD_ENDERECO) REFERENCES TB_ENDERECO (CD_ENDERECO);
 * @author rodri
 *
 */
public class Cliente {
		
	private int codigo;
	
	private String nome;
	
	private String dataNascimento;
	
	private lolja lolja;
	
	private Endereco endereco;
	
	public Cliente() {}

	public Cliente(int codigo, String nome, String dataNascimento, br.com.fiap.tds.bean.lolja lolja,
			Endereco endereco) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.lolja = lolja;
		this.endereco = endereco;
	}

	public Cliente(int codigo, String nome, String dataNascimento) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public Cliente(int codigo, String nome, String dataNascimento, br.com.fiap.tds.bean.lolja lolja) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.lolja = lolja;
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public lolja getLolja() {
		return lolja;
	}

	public void setLolja(lolja lolja) {
		this.lolja = lolja;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isAtivo() {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
