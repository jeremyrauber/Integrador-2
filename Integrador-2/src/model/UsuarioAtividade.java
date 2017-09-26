package model;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name="usuario_has_usuario")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.usuario",
        joinColumns = @JoinColumn(name = "id_usuario")),
    @AssociationOverride(name = "primaryKey.atividade",
        joinColumns = @JoinColumn(name = "id_atividade")) })
public class UsuarioAtividade implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;
	
	// composite-id key
	 @EmbeddedId
    private UsuarioAtividadeId primaryKey = new UsuarioAtividadeId();
		
	//outros atributos
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim_atividade", insertable=false)
	private Date dataFimAtividade;
	
	@Column(name="caminho_imagem")
	private Integer caminhoImagem;
	
	@Column(name="status",columnDefinition = "int default 0")
	private Integer status;


	 @EmbeddedId
    public UsuarioAtividadeId getPrimaryKey() {
        return primaryKey;
    }
 
	public void setPrimaryKey(UsuarioAtividadeId primaryKey) {
        this.primaryKey = primaryKey;
    }
 
    @Transient
    public Usuario getUsuario() {
        return getPrimaryKey().getUsuario();
    }
 
    public void setUsuario(Usuario usuario) {
        getPrimaryKey().setUsuario(usuario);
    }
 
    @Transient
    public Atividade getEvento() {
        return getPrimaryKey().getAtividade();
    }
 
    public void setAtividade(Atividade evento) {
        getPrimaryKey().setAtividade(evento);
    }
 
	public Date getDataFimAtividade() {
		return dataFimAtividade;
	}

	public void setDataFimAtividade(Date dataVinculo) {
		this.dataFimAtividade = dataVinculo;
	}

	public Integer getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(Integer caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}