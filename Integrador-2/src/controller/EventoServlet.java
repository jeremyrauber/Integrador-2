package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.google.gson.Gson;

import dao.DaoAtividade;
import dao.DaoEvento;
import dao.DaoEventoUsuario;
import dao.DaoRanking;
import dao.DaoUsuarioAtividade;
import model.Atividade;
import model.BarraDTO;
import model.Dashboard;
import model.Ranking;
import model.Evento;
import model.EventoUsuario;
import model.Mestre;
import model.PizzaDTO;
import model.Usuario;
import model.UsuarioAtividade;

@WebServlet("/evento")
public class EventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(true);
		
		String acao = request.getParameter("acao");
		PrintWriter out = response.getWriter();
		DaoEvento daoEvento = new DaoEvento();
		DaoAtividade daoAtividade = new DaoAtividade();
		DaoEventoUsuario daoEventoUsuario = new DaoEventoUsuario();
		DaoUsuarioAtividade daoUsuarioAtividade = new DaoUsuarioAtividade();
		DaoRanking daoRanking = new DaoRanking();
		
		String mensagem = "";
		
		if(acao == null){
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
    	}else {
		
			if (acao.equals("cadastrar")) {
				
				List<Atividade> atividades = daoAtividade.findAll();
				request.setAttribute("atividades", atividades);
	    		request.getRequestDispatcher("jsp/evento/cadastrarEvento.jsp").forward(request, response);
	    		
	    	}else if (acao.equals("visualizar")) {
	    		
	    		Integer id = Integer.parseInt(request.getParameter("id"));
	    		Evento evento = daoEvento.findById(id);
	    		List<EventoUsuario> eu = daoEventoUsuario.findByEventoId(evento.getId());
	    		Set<EventoUsuario> foo = new HashSet<EventoUsuario>(eu);

	    		evento.setEventoUsuarios(foo);
	    		
	    		for(EventoUsuario e : eu) {
					System.out.println(e.getUsuario().getNome()+">>>>>>>>>>>>>>>>>>>>>>>>");
				}
	    		
	    		List<Ranking> ranking = daoRanking.findUsuarioByEvento(id);
	    		request.setAttribute("ranking", ranking);
	    		request.setAttribute("evento", evento);    
	    		request.getRequestDispatcher("jsp/evento/manterEvento.jsp").forward(request, response);
	    		
	    	}else if (acao.equals("adicionar")) {
	    		
	    		String nome = request.getParameter("fullname");
	    		String dataInicio = request.getParameter("datai");
	    		String dataFim = request.getParameter("dataf");
	    		String descricao = request.getParameter("descricao");
	    		String keyword = request.getParameter("keyword");
	    		String atividades[] = request.getParameterValues("atividades[]");
	    		
	    		String erro = "";
	    		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
	    		Integer hoje  = Integer.parseInt(timeStamp);
	    		Integer datai = Integer.parseInt(dataInicio.replaceAll("-", ""));
	    		Integer dataf = Integer.parseInt(dataFim.replaceAll("-", ""));
  		
	    		if(nome.equals("") || nome.length()<5 ){
	    			erro = " Nome n�o pode ser vazio ou menor que 5 caracteres!";
	    		}
	    		if(datai - hoje<0) {
	    			erro = erro + " Data de in�cio n�o pode ser inferior a hoje!";
	    		}
	    		if(dataf - hoje<0) {
	    			erro = erro + " Data de fim n�o pode ser inferior a hoje!";
	    		}
	    		if(datai - dataf>0) {
	    			erro = erro + " Data de fim n�o pode ser inferior a data de in�cio!";
	    		}
	    		
	    		Evento e = new Evento();
				
				
				Set<Atividade> lista = new HashSet<>();
				e.setNome(nome);
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date date;
				
				try {
					date = format.parse(dataInicio);
					e.setDataInicio(date);
					date = format.parse(dataFim);
					e.setDataFim(date);
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				e.setDescricao(descricao);
				e.setPalavraChave(keyword);
				
				Mestre mestre = (Mestre) session.getAttribute("mestre");			
				
				e.setMestre(mestre);
				
				
				
				if(atividades != null) {
					for(String var: atividades) {
						Atividade a = daoAtividade.findById(Integer.parseInt(var));
						System.out.println(a.getId()+"-"+a.getDescricao());
						lista.add(a);
					}
				}
			
				e.setAtividades(lista);			
				
				
				if(erro.equals("")) {
					daoEvento.save(e); 
			
					mensagem = "Cadastro de evento realizado!";
					request.setAttribute("mensagem", mensagem);

					request.setAttribute("evento", e);
					request.getRequestDispatcher("jsp/evento/manterEvento.jsp").forward(request, response);
				}else {
					List<Atividade> a1 = daoAtividade.findAll();
					request.setAttribute("atividades", a1);
					request.setAttribute("evento", e);
					request.setAttribute("erro", erro);
					request.getRequestDispatcher("jsp/evento/cadastrarEvento.jsp").forward(request, response);
				}
			
	    	}
	    	else if (acao.equals("editar")) {
	    		Integer id = Integer.parseInt(request.getParameter("id"));
	    		Evento evento = daoEvento.findById(id);
	    		
	    		
	    		Set<Atividade> atividades = new TreeSet<Atividade>();
	    		
				for(Atividade a: evento.getAtividades()) {
					a.setSelecionado(true);
					atividades.add(a);
				}
				
				request.setAttribute("atividades", atividades);    		
	    		request.setAttribute("evento", evento);    
	    		request.getRequestDispatcher("jsp/evento/cadastrarEvento.jsp").forward(request, response);
	    		
	    	}else if (acao.equals("atualizar")) {
	    		
	    		String nome = request.getParameter("fullname");
	    		Integer id = Integer.parseInt(request.getParameter("id"));
	    		String dataInicio = request.getParameter("datai");
	    		String dataFim = request.getParameter("dataf");
	    		String descricao = request.getParameter("descricao");
	    		String keyword = request.getParameter("keyword");
	    		String atividades[] = request.getParameterValues("atividades[]");
	    		
	    		Evento eve =  daoEvento.findById(id);
	    		
	    		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
	    		String timeStamp2 = new SimpleDateFormat("yyyyMMdd").format(eve.getDataInicio());
	    		Integer hoje  = Integer.parseInt(timeStamp);
	    		Integer dataOriginal = Integer.parseInt(timeStamp2);
	    		Integer dataf = Integer.parseInt(dataFim.replaceAll("-", ""));
	    		
	    		String erro = "";
	    		if(nome.equals("") || nome.length()<5 ){
	    			erro = " Nome n�o pode ser vazio ou menor que 5 caracteres!";
	    		}
	    		if(dataf - dataOriginal< 0) {
	    			erro = erro + " Data de fim n�o pode ser inferior a hoje!";
	    		}
	    		
	    		Set<Atividade> lista = new HashSet<>();
				
				if(atividades != null) {
					for(String var: atividades) {
						Atividade a = daoAtividade.findById(Integer.parseInt(var));
						System.out.println(a.getDescricao());
						lista.add(a);
					}
				}
				
				eve.setAtividades(lista);
				eve.setNome(nome);
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date date;
    		
	    		List<EventoUsuario> eu = daoEventoUsuario.findByEventoId(eve.getId());
	    		Set<EventoUsuario> foo = new HashSet<EventoUsuario>(eu);

	    		eve.setEventoUsuarios(foo);
				
				try {
					date = format.parse(dataFim);
					eve.setDataFim(date);
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				eve.setDescricao(descricao);
				eve.setPalavraChave(keyword);
				
				Mestre mestre = (Mestre) session.getAttribute("mestre");
				
				eve.setMestre(mestre);
				
				if(erro.equals("")) {
					daoEvento.update(eve);
			
					eve =  daoEvento.findById(id);
					eu = daoEventoUsuario.findByEventoId(id);
					
					mestre = (Mestre) session.getAttribute("mestre");
					
					mensagem = "Evento atualizadocom sucesso!";
					request.setAttribute("mensagem", mensagem);
					request.setAttribute("evento", eve);
					List<Ranking> ranking = daoRanking.findUsuarioByEvento(id);
		    		request.setAttribute("ranking", ranking);
					request.setAttribute("mestre", mestre);
					request.getRequestDispatcher("jsp/evento/manterEvento.jsp?acao=visualizar&id="+id).forward(request, response);

				}else {
		    		Evento evento = daoEvento.findById(id);
	
		    		Set<Atividade> atividades2 = new TreeSet<Atividade>();
		    		
					for(Atividade a: evento.getAtividades()) {
						a.setSelecionado(true);
						atividades2.add(a);
					}
					request.setAttribute("erro", erro);
					request.setAttribute("atividades", atividades2);    		
		    		request.setAttribute("evento", evento);    
		    		request.getRequestDispatcher("jsp/evento/cadastrarEvento.jsp").forward(request, response);
				}
				
				
				
	    		
	    	}else if (acao.equals("avaliar")) {
	    		
	    		Integer eventoId = Integer.parseInt(request.getParameter("id"));
	    		
	    		Evento e =  daoEvento.findById(eventoId);
	    		UsuarioAtividade ua = new UsuarioAtividade();
	    		ua = daoUsuarioAtividade.findByEventoId(e.getId());
	    		
	    		
	    		try {
	    			List<UsuarioAtividade> envios = daoUsuarioAtividade.findEnviosByUsuarioAndEventoId(e.getId(), ua.getUsuario().getId());
	    			request.setAttribute("envios", envios);
	    		}catch (Exception erro) {
	    			request.setAttribute("envios",null);
				}
	    		
	    		
	    		request.setAttribute("evento", e);
	    		request.setAttribute("envio_usuario", ua);
	    		
	    		request.getRequestDispatcher("jsp/evento/avaliarEvento.jsp").forward(request, response);
	    		
	    	}else if (acao.equals("ranking")) {
	    		
	    		
	    		Integer id = Integer.parseInt(request.getParameter("id"));
	    		List<Ranking> ranking = daoRanking.findRankingByEvento(id);
	    		Dashboard d = daoRanking.findItensDashboard(id);
	    		
	    		request.setAttribute("dashboard", d);
	    		request.setAttribute("id",id);
	    		request.setAttribute("ranking", ranking);
	    		request.getRequestDispatcher("jsp/evento/ranking.jsp").forward(request, response);
	    		
	    	}else if (acao.equals("julgar")) {
	    		
	    		String tipo = request.getParameter("tipo");
	    		String ids = request.getParameter("ids");
	    		String[] parts = ids.split("-");

	    		Integer id_evento= Integer.parseInt(parts[0]);
	    		Integer id_atividade = Integer.parseInt(parts[1]);
	    		Integer id_usuario= Integer.parseInt(parts[2]);
	    		 
	    		if(tipo.equals("correto")) {
	    			UsuarioAtividade novoUsuarioAtiv = daoUsuarioAtividade.findByEventoAtividadeUsuarioId(id_evento, id_atividade, id_usuario);
	    			novoUsuarioAtiv.setStatus(true);
	    			daoUsuarioAtividade.update(novoUsuarioAtiv);
	    			novoUsuarioAtiv = null;
	    			mensagem = "Avaliado com sucesso!";
	    			
	    		}else if(tipo.equals("errado")) {
	    			UsuarioAtividade novoUsuarioAtiv = daoUsuarioAtividade.findByEventoAtividadeUsuarioId(id_evento, id_atividade, id_usuario);
	    			daoUsuarioAtividade.remove(novoUsuarioAtiv);
	    			novoUsuarioAtiv = null;
	    			mensagem = "Avaliado com sucesso!";

	    			
	    		}else if(tipo.equals("banir")) {
	    			EventoUsuario eu = (EventoUsuario) daoEventoUsuario.findByEventoAndUsuarioID(id_evento,id_usuario);
	    			eu.setBanidoEvento(true);
	    			daoEventoUsuario.update(eu);
	    			mensagem = "Usuario banido do evento!";
	    			
	    		}
	    		out.print(mensagem);
	    			
	    	}else if (acao.equals("pizza")) {
	    		Integer id = Integer.parseInt(request.getParameter("id"));
	    		List<PizzaDTO> pizza= daoRanking.findItensGraficoPizza(id);
	    		    	
	    		Gson gson = new Gson();
	    		
	    		out.println(gson.toJson( pizza));
	    		
	    	}else if (acao.equals("barra")) {
	    		Integer id = Integer.parseInt(request.getParameter("id"));
	    		List<BarraDTO> barra = daoRanking.findItensGraficoBarra(id);
	    		
	    		Gson gson = new Gson();
	    		out.println(gson.toJson( barra));
	    	}
    	}
	}
}
