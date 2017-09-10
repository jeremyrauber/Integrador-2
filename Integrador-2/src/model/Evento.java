package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="evento")
public class Evento {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String descricao;
	
	@Column(nullable=false,name="data_inicio")
	private Date dataInicio;
	
	@Column(nullable=false,name="data_fim")
	private Date dataFim;
	
	@Column(nullable=false,name="palavra_chave")
	private String palavraChave;
	
	@ManyToOne
    @JoinColumn(name="mestre_id_mestre", nullable=false)
    private Mestre mestre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public Mestre getMestre() {
		return mestre;
	}

	public void setMestre(Mestre mestre) {
		this.mestre = mestre;
	}
	
}
