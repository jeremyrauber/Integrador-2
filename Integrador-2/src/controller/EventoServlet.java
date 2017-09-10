package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoEvento;
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
		
		String mensagem = "";
		
		if(acao == null){
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
    	}else {
		
			if (acao.equals("cadastrar")) {
	    		
	    		request.getRequestDispatcher("jsp/evento/cadastrarEvento.jsp").forward(request, response);
	    		
	    	}else if (acao.equals("editar")) {
	    		
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
	    		
	    		System.out.println(dataInicio+"*"+dataFim);
	    		
				Evento e = new Evento();
				
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
				daoEvento.save(e);
				
				mensagem = "Cadastro de evento realizado com sucesso!";
				request.setAttribute("mensagem", mensagem);
				request.setAttribute("evento", e);
				request.getRequestDispatcher("jsp/evento/manterEvento.jsp").forward(request, response);
			}   
    	}
	}
}
