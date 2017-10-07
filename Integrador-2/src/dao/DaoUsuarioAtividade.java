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
	
	public UsuarioAtividade findByEventoAtividadeUsuarioId(Integer id_evento,Integer id_atividade, Integer id_usuario) {
		try{
			Query q = em.createNativeQuery("SELECT * FROM usuario_has_atividade WHERE id_evento="+id_evento+" AND id_atividade="+id_atividade+" AND id_usuario="+id_usuario,UsuarioAtividade.class);
			return  (UsuarioAtividade) q.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
	
	public List<UsuarioAtividade> findEnviosByUsuarioAndEventoId(Integer id_evento, Integer id_usuario) {
		try{
			Query q = em.createNativeQuery("SELECT a1.id_usuario,a1.id_atividade,a1.id_evento, data_fim_atividade,"
					+ " status, caminho_imagem  FROM usuario_has_atividade as a1 INNER JOIN evento_has_usuario on "
					+ "(evento_has_usuario.id_usuario=a1.id_usuario) WHERE a1.id_evento="+id_evento+" AND "
					+ "a1.id_usuario="+id_usuario+" AND caminho_imagem IS NOT NULL AND caminho_imagem <> '' ;",UsuarioAtividade.class);
		
			return  q.getResultList();
		}catch (Exception e) {
			return null;
		}
	}
	
	public void atualizaEnvio(Integer id_evento, Integer id_usuario,Integer id_atividade) {
		Query q = em.createNativeQuery("UPDATE usuario_has_atividade SET status=1 WHERE id_evento="+id_evento+" AND id_usuario="+id_usuario+" AND id_atividade="+id_atividade);
	}
	

}
