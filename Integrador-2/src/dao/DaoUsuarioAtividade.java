package dao;

import java.util.List;

import javax.persistence.Query;

import model.EventoUsuario;
import model.UsuarioAtividade;

public class DaoUsuarioAtividade extends DaoEntity<UsuarioAtividade, Integer> {

	public DaoUsuarioAtividade(){
		super(UsuarioAtividade.class);
	}
	
	public UsuarioAtividade findByEventoId(Integer id) {
		try{
			Query q = em.createNativeQuery("SELECT * FROM usuario_has_atividade WHERE id_evento="+id+" AND caminho_imagem IS NOT NULL AND caminho_imagem <> '' AND status=0 LIMIT 1",UsuarioAtividade.class);
			return  (UsuarioAtividade) q.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
	
	public List<UsuarioAtividade> findEnviosByUsuarioAndEventoId(Integer id_evento, Integer id_usuario) {
		try{
			Query q = em.createNativeQuery("SELECT * FROM usuario_has_atividade WHERE id_evento="+id_evento+" AND id_usuario="+id_usuario+" AND caminho_imagem IS NOT NULL AND caminho_imagem <> '' AND status=0",UsuarioAtividade.class);
		
			return  q.getResultList();
		}catch (Exception e) {
			return null;
		}
	}
	

}
