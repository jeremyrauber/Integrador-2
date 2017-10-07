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
			Query q = em.createNativeQuery("SELECT * FROM usuario_has_atividade as a1 INNER JOIN evento_has_usuario AS a2 "
					+ "ON (a1.id_usuario=a2.id_usuario and a1.id_evento=a2.id_evento) WHERE a1.id_evento=1 AND caminho_imagem IS NOT NULL AND caminho_imagem <> '' "
					+ "AND status=0 AND a2.banido_evento=0 LIMIT 1;",UsuarioAtividade.class);
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
			Query q = em.createNativeQuery("SELECT a1.id_usuario,a1.id_evento,id_atividade,data_fim_atividade,status,caminho_imagem FROM usuario_has_atividade as a1"
					+ " INNER JOIN evento_has_usuario as a2 on (a1.id_usuario=a2.id_usuario and a1.id_evento=a2.id_evento) WHERE a1.id_evento="+id_evento+" AND a1.id_usuario="+id_usuario+" AND status=1",UsuarioAtividade.class);
	
			return  q.getResultList();
		}catch (Exception e) {
			return null;
		}
	}
	
	public void atualizaEnvio(Integer id_evento, Integer id_usuario,Integer id_atividade) {
		Query q = em.createNativeQuery("UPDATE usuario_has_atividade SET status=1 WHERE id_evento="+id_evento+" AND id_usuario="+id_usuario+" AND id_atividade="+id_atividade);
	}
	

}
