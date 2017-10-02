package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import dao.DaoUsuarioAtividade;
import model.Atividade;
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

	    		evento.setEventoUsuario(foo);
	    		
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
	    		
	    		Evento e = new Evento();
				
				
				List<Atividade> lista = new ArrayList<>();
				
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
			
				daoEvento.save(e); 
			
				mensagem = "Cadastro de evento realizado!";
				request.setAttribute("mensagem", mensagem);

				request.setAttribute("evento", e);
				request.getRequestDispatcher("jsp/evento/manterEvento.jsp").forward(request, response);
			
	    	}
	    	else if (acao.equals("editar")) {
	    		Integer id = Integer.parseInt(request.getParameter("id"));
	    		Evento evento = daoEvento.findById(id);
	    		
	    		
	    		Set<Atividade> atividades = new TreeSet<Atividade>();
				for(Atividade a: evento.getAtividades()) {
					a.setSelecionado(true);
					atividades.add(a);
				}
				atividades.addAll(daoAtividade.findAll());
				for(Atividade a: atividades) {
					System.out.println(a.getDescricao()+"9"+a.isSelecionado());
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
	    		
	    		List<Atividade> lista = new ArrayList<>();
				
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
				List<EventoUsuario> eu = daoEventoUsuario.findByEventoId(id);
	    		Set<EventoUsuario> foo = new HashSet<EventoUsuario>(eu);

	    		eve.setEventoUsuario(foo);
				
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
				
				mensagem = "Evento atualizadocom sucesso!";
				request.setAttribute("mensagem", mensagem);
				request.setAttribute("evento", eve);
				request.setAttribute("mestre", mestre);
				request.getRequestDispatcher("jsp/evento/manterEvento.jsp?acao=visualizar&id="+id).forward(request, response);
	    		
	    	}else if (acao.equals("avaliar")) {
	    		
	    		Integer id = Integer.parseInt(request.getParameter("id"));
	    		
	    		Evento e =  daoEvento.findById(id);
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
	    		
	    		request.getRequestDispatcher("jsp/evento/ranking.jsp").forward(request, response);
	    		
	    	}else if (acao.equals("julgar")) {
	    		
	    		String tipo = request.getParameter("tipo");
	    		String ids = request.getParameter("ids");
	    		String[] parts = ids.split("-");

	    		Integer id_evento= Integer.parseInt(parts[0]);
	    		Integer id_atividade = Integer.parseInt(parts[1]);
	    		Integer id_usuario= Integer.parseInt(parts[2]);
	    		 
	    		if(tipo.equals("correto")) {
	    			UsuarioAtividade usua = daoUsuarioAtividade.findByEventoAtividadeUsuarioId(id_evento, id_atividade, id_usuario);
	    			usua.setStatus(1);
	    			daoUsuarioAtividade.update(usua);
	    			mensagem = "correto"+ids;
	    			
	    		}else if(tipo.equals("errado")) {
	    			mensagem = "errado";
	    			
	    		}else if(tipo.equals("banir")) {
	    			mensagem = "banir";
	    			
	    		}
	    		out.print(mensagem);
	    	}
			
    	}
	}
}
