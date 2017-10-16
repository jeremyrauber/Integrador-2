package dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import model.Ranking;
import model.Usuario;


public class DaoRanking extends DaoEntity<Usuario, Integer> {

	public DaoRanking() {
		super(Usuario.class);
	}
	
	public List<Ranking> findByEvento(Integer id_evento){

		Query q = em
			    .createNativeQuery( "SELECT login,bairro, cidade, estado,data_nasc as dataNascimento, "
			    		+ "SUM(TIMESTAMPDIFF(second,'2017-09-29',data_fim_atividade)) AS tempoTotal, "
			    		+ "count(id_atividade) AS totalAtividade, nome FROM usuario INNER JOIN usuario_has_atividade ON id_usuario=id "
			    		+ "WHERE id_evento="+id_evento+" and status=1 group by id_usuario order by totalAtividade DESC, "
			    		+ "tempoTotal ASC");
		List<Object[]> listResults = q.getResultList();
		List<Ranking> ranking = new ArrayList<>();
		Integer i =0;
		for (Object[] record : listResults) {
			Ranking r = new Ranking();
		    r.setLogin((String) listResults.get(i)[0]);
		    r.setBairro((String) listResults.get(i)[1]);
		    r.setCidade((String) listResults.get(i)[2]);
		    r.setEstado((String) listResults.get(i)[3]);
		    r.setDataNascimento((String) listResults.get(i)[4].toString());
		    Integer tempotot = ((BigDecimal)  listResults.get(i)[5]).intValue();
		    Integer totativ = ((BigInteger)  listResults.get(i)[6]).intValue();
		   
		   Integer hours = tempotot / 3600;
		   Integer minutes = (tempotot % 3600) / 60;
		   Integer seconds = tempotot % 60;

		   String timeString = String.format("%02dh %02dmin %02ds", hours, minutes, seconds);
		    
		    r.setTempoTotal(timeString);
		    r.setTotalAtividade( totativ );
		    r.setNome( (String) listResults.get(i)[7]);
		    ranking.add(r);
		    i++;
		}
		return ranking;
		
		
	}
}
