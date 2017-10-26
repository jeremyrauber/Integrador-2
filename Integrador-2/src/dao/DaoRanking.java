package dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import model.BarraDTO;
import model.Dashboard;
import model.PizzaDTO;
import model.Ranking;
import model.Usuario;


public class DaoRanking extends DaoEntity<Usuario, Integer> {

	public DaoRanking() {
		super(Usuario.class);
	}
	
	public List<Ranking>  findUsuarioByEvento(Integer id_evento){
		Query q = em
			    .createNativeQuery("select nome,login FROM evento_has_usuario INNER JOIN usuario ON (id=id_usuario) WHERE id_evento="+id_evento);
		
		List<Object[]> listResults = q.getResultList();
		List<Ranking> ranking = new ArrayList<>();
		Integer i =0;
		for (Object[] record : listResults) {
			Ranking r = new Ranking();
		    r.setLogin((String) listResults.get(i)[1]);
		    r.setNome( (String) listResults.get(i)[0]);
		    ranking.add(r);
		    i++;
		}
		return ranking;	
	}
	
	public List<Ranking> findRankingByEvento(Integer id_evento){

		Query q = em.createNativeQuery( "SELECT login,bairro, cidade, estado,data_nasc as dataNascimento, "
					    		+ "SUM(TIMESTAMPDIFF(second,(SELECT data_inicio FROM evento WHERE id="+id_evento+"),data_fim_atividade)) AS tempoTotal, "
					    		+ "count(id_atividade) AS totalAtividade, "
					    		+ "nome, "
					    		+ "(SELECT banido_evento from evento_has_usuario WHERE id_evento=1 AND evento_has_usuario.id_usuario=usuario_has_atividade.id_usuario ) "
					    		+ "FROM usuario INNER JOIN usuario_has_atividade ON id_usuario=id "
					    		+ "WHERE id_evento="+id_evento+" and status=1 GROUP BY id_usuario order by totalAtividade DESC, "
					    		+ "tempoTotal ASC");
		
		//SELECT login,bairro, cidade, estado,data_nasc as dataNascimento, SUM(TIMESTAMPDIFF(second,(SELECT data_inicio FROM evento WHERE id=1 ),data_fim_atividade)) AS tempoTotal, count(id_atividade) AS totalAtividade, nome, (SELECT banido_evento from evento_has_usuario WHERE id_evento=1 AND evento_has_usuario.id_usuario=usuario_has_atividade.id_usuario ) as banido FROM usuario INNER JOIN usuario_has_atividade ON id_usuario=id WHERE id_evento=1 and status=1 GROUP BY id_usuario order by totalAtividade DESC, tempoTotal ASC;
		
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
		    Integer banido=0;
		    if ( listResults.get(i)[8]!=null)
		    		banido=Integer.valueOf((Integer) listResults.get(i)[8]);
		    
		    r.setBanido( banido );
		    ranking.add(r);
		    i++;
		}
		return ranking;	
	}
	
	public Dashboard findItensDashboard(Integer id_evento){
		
		try {
			Query q = em
				    .createNativeQuery("SELECT * FROM (select sum(status) as fotos, "
				    		+ "(select sum(status))/((select count(id_atividade) FROM evento_has_atividade where id_evento="+id_evento+")* " 
				    		+ "(SELECT count(id_usuario) FROM evento_has_usuario where id_evento="+id_evento+")) as media, "
				    		+ "SUM(TIMESTAMPDIFF(second,(SELECT data_inicio FROM evento WHERE id="+id_evento+"),data_fim_atividade)) AS tempoTotal FROM usuario_has_atividade "
				    		+ "WHERE id_evento="+id_evento+" AND status=1) p1 "
				    		+ "JOIN "
				    		+ "(SELECT count(evento_has_usuario.id_usuario) as participantes, sum(banido_evento) AS banidos, "
				    		+ "count(distinct (select bairro from usuario where id=evento_has_usuario.id_usuario)) AS bairro from evento_has_usuario where id_evento="+id_evento+") p2 ON 1=1");		 
			
			List<Object[]> listResults = q.getResultList();
			List<Dashboard> dashboard = new ArrayList<>();
			Integer i = 0;
			Dashboard d = new Dashboard();
			for (Object[] record : listResults) {
				
				d.setNumeroFotos(( (BigDecimal) listResults.get(i)[0]).intValue());
				d.setMediaFotos( ((BigDecimal)  listResults.get(i)[1]).doubleValue() );
				Integer totativ = ((BigDecimal) listResults.get(i)[2]).intValue();
				   
				   Integer hours = totativ / 3600;
				   Integer minutes = (totativ % 3600) / 60;
				   Integer seconds = totativ % 60;
	
				   String timeString = String.format("%02dh %02dmin %02ds", hours, minutes, seconds);
				
				d.setTempoTotal(timeString);
				d.setParticipantes( ((BigInteger)  listResults.get(i)[3]).intValue() );
				d.setBanidos(( (BigDecimal) listResults.get(i)[4]).intValue());
				d.setBairro(( (BigInteger) listResults.get(i)[5]).intValue());
				dashboard.add(d);
				i++;
			}
			return d;	
		}catch(Exception e) {
			return null;
		}
	}
	
	public List<PizzaDTO> findItensGraficoPizza(Integer id_evento){
		Query q = em.createNativeQuery(
					"SELECT sum(status), (select bairro from usuario where usuario.id=usuario_has_atividade.id_usuario) AS bairro FROM usuario_has_atividade where id_evento="+id_evento+" GROUP BY bairro");
		List<Object[]> listResults = q.getResultList();
		List<PizzaDTO> itens = new ArrayList<>();
		Integer i =0;
		for (Object[] record : listResults) {
			PizzaDTO d = new PizzaDTO();
			d.setQuantidade(( (BigDecimal) listResults.get(i)[0]).intValue());
			d.setBairro( (String) listResults.get(i)[1] );
			
			itens.add(d);
			i++;
		}
		return itens;	
	}
	
	public List<BarraDTO> findItensGraficoBarra(Integer id_evento){
	
		Query q = em.createNativeQuery(
					"SELECT sum(status) AS quantidade ,(SELECT TIMESTAMPDIFF(YEAR, data_nasc, CURDATE()) FROM usuario WHERE id=id_usuario) AS idade "
					+ "FROM usuario_has_atividade WHERE id_evento="+id_evento+" GROUP BY idade ORDER BY idade;");
		
		List<Object[]> listResults = q.getResultList();
		List<BarraDTO> itens = new ArrayList<>();
		Integer i =0;
		for (Object[] record : listResults) {
			BarraDTO b = new BarraDTO();
			b.setQuantidade(( (BigDecimal) listResults.get(i)[0]).intValue() );
			b.setIdade( ( (BigInteger ) listResults.get(i)[1]).intValue() );
			
			itens.add(b);
			i++;
		}
		return itens;	
	}
	
}
