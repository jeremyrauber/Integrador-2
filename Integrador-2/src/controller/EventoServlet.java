package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import model.Atividade;
import model.Evento;
import model.Mestre;

@WebServlet("/evento")
public class EventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(true);
		
		String acao = request.getParameter("acao");
		
		DaoEvento daoEvento = new DaoEvento();
		DaoAtividade daoAtividade = new DaoAtividade();
		
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
				
						lista.add(a);
					}
				}
				
				Evento e = daoEvento.findById(id);
				
				e.setAtividades(lista);
				e.setNome(nome);
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date date;
				System.out.println("passou aqui");
				
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
				
				daoEvento.update(e);
				
				mensagem = "Evento atualizadocom sucesso!";
				request.setAttribute("mensagem", mensagem);
				request.setAttribute("evento", e);
				request.setAttribute("mestre", mestre);
				request.getRequestDispatcher("jsp/evento/manterEvento.jsp?acao=visualizar&id="+e.getId()).forward(request, response);
	    		
	    	}if (acao.equals("avaliar")) {
	    		
	    		
	    		
	    		request.getRequestDispatcher("jsp/evento/avaliarEvento.jsp").forward(request, response);
	    		
	    	}if (acao.equals("ranking")) {
	    		
	    		request.getRequestDispatcher("jsp/evento/ranking.jsp").forward(request, response);
	    		
	    	}
    	}
	}
}
