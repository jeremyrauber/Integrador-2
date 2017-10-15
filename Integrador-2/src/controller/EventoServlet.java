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

import dao.DaoAtividade;
import dao.DaoEvento;
import dao.DaoEventoUsuario;
import dao.DaoRanking;
import dao.DaoUsuarioAtividade;
import model.Atividade;
import model.Ranking;
import model.Evento;
import model.EventoUsuario;
import model.Mestre;
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
	    			erro = " Nome não pode ser vazio ou menor que 5 caracteres!";
	    		}
	    		if(datai - hoje<0) {
	    			erro = erro + " Data de início não pode ser inferior a hoje!";
	    		}
	    		if(dataf - hoje<0) {
	    			erro = erro + " Data de fim não pode ser inferior a hoje!";
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
				
				for(Atividade a: daoAtividade.findAll()) {
					atividades.add(a);
					System.out.println(a.getDescricao());
				}
				
				for(Atividade a: atividades) {
					System.out.println(a.getDescricao());
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
	    		
	    		Set<Atividade> lista = new HashSet<>();
				
				if(atividades != null) {
					for(String var: atividades) {
						Atividade a = daoAtividade.findById(Integer.parseInt(var));
						System.out.println(a.getDescricao());
						lista.add(a);
					}
				}
				
				Evento eve =  daoEvento.findById(id);
				
				eve.setAtividades(lista);
				eve.setNome(nome);
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date date;

	    		
	    		List<EventoUsuario> eu = daoEventoUsuario.findByEventoId(eve.getId());
	    		Set<EventoUsuario> foo = new HashSet<EventoUsuario>(eu);

	    		eve.setEventoUsuarios(foo);
				
				try {
					date = format.parse(dataInicio);
					eve.setDataInicio(date);
					date = format.parse(dataFim);
					eve.setDataFim(date);
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				eve.setDescricao(descricao);
				eve.setPalavraChave(keyword);
				
				Mestre mestre = (Mestre) session.getAttribute("mestre");
				
				eve.setMestre(mestre);
				
				daoEvento.update(eve);
				
				eve =  daoEvento.findById(id);
				eu = daoEventoUsuario.findByEventoId(id);
				for(EventoUsuario e : eu) {
					System.out.println(e.getUsuario().getNome()+">>>>>>>>>>>>>>>>>>>>>>>>");
				}
				
				mestre = (Mestre) session.getAttribute("mestre");
				
				mensagem = "Evento atualizadocom sucesso!";
				request.setAttribute("mensagem", mensagem);
				request.setAttribute("evento", eve);
				request.setAttribute("mestre", mestre);
				request.getRequestDispatcher("jsp/evento/manterEvento.jsp?acao=visualizar&id="+id).forward(request, response);
	    		
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
	    		
	    		DaoRanking daoRanking = new DaoRanking();
	    		Integer id = Integer.parseInt(request.getParameter("id"));
	    		List<Ranking> ranking = daoRanking.findByEvento(id);
	    		for(Ranking r: ranking){
	    			System.out.println(r.getLogin()+"-"+r.getTempoTotal()+"-"+r.getTotalAtividade());
	    			
	    		}
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
	    			UsuarioAtividade novousuario = daoUsuarioAtividade.findByEventoAtividadeUsuarioId(id_evento, id_atividade, id_usuario);
	    			novousuario.setStatus(true);
	    			daoUsuarioAtividade.update(novousuario);
	    			novousuario = null;
	    			
	    		}else if(tipo.equals("errado")) {
	    			UsuarioAtividade novousuario= daoUsuarioAtividade.findByEventoAtividadeUsuarioId(id_evento, id_atividade, id_usuario);
	    			novousuario.setStatus(false);
	    			novousuario.setCaminhoImagem("");
	    			daoUsuarioAtividade.update(novousuario);
	    			novousuario = null;

	    			
	    		}else if(tipo.equals("banir")) {
	    			EventoUsuario eu = (EventoUsuario) daoEventoUsuario.findByEventoAndUsuarioID(id_evento,id_usuario);
	    			eu.setBanidoEvento(true);
	    			daoEventoUsuario.update(eu);
	    			mensagem = "Usuario banido do evento!";
	    			
	    		}
	    		out.print(mensagem);
	    	}
			
    	}
	}
}
