package dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.transform.AliasToBeanResultTransformer;

import model.Ranking;
import model.Usuario;
import model.EventoUsuario;
import model.Mestre;

public class DaoRanking extends DaoEntity<Usuario, Integer> {

	public DaoRanking() {
		super(Usuario.class);
	}
	
	public List<Ranking> findByEvento(Integer id_evento){

		List<Ranking> alunos = em
			    .createQuery( "SELECT login,bairro, cidade, estado,data_nasc as dataNascimento, "
			    		+ "SUM(TIMESTAMPDIFF(second,'2017-09-29',data_fim_atividade)) AS tempoTotal, "
			    		+ "count(id_atividade) AS totalAtividade FROM usuario INNER JOIN usuario_has_atividade ON id_usuario=id "
			    		+ "WHERE id_evento="+id_evento+" and status=1 group by id_usuario order by totalAtividade DESC, "
			    		+ "tempoTotal ASC")
			    .setResultTransformer(new AliasToBeanResultTransformer(Ranking.class))
			    .list();
		
		
		
	}
}
