package br.ifpr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ifpr.dao.DaoArtista;
import br.ifpr.dao.DaoCategoria;
import br.ifpr.dao.DaoEntity;
import br.ifpr.dao.DaoEstudio;
import br.ifpr.dao.DaoFilme;
import br.ifpr.model.Artista;
import br.ifpr.model.Categoria;
import br.ifpr.model.Estudio;
import br.ifpr.model.Filme;
import br.ifpr.model.Usuario;


@WebServlet("/estudio")
public class EstudioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String acao = request.getParameter("acao");
		String cat = request.getParameter("cat");
		DaoEstudio daoEstudio;
		
		
		if(acao != null) {
			if(acao.equals("pagePesquisa")){
				daoEstudio = new DaoEstudio();
				
				List<Estudio> estudios = daoEstudio.findAll();
				
				//findByCategoria
				
				String resposta = "";
				
				for(Estudio e: estudios){
					resposta = resposta + 
					"<li><a href='' data-id='"+e.getId()+"'>"+
							e.getNome()+"</a></li>";
				}
				resposta = resposta + "</ul>";
				
				request.setAttribute("resposta", resposta);
				request.getRequestDispatcher("/jsp/estudio/pesquisaEstudio.jsp").forward(request, response);
			}
			else if(acao.equals("inserir")){
				String nome = request.getParameter("nome");
				String pais = request.getParameter("pais");
				
		
				Estudio e = new Estudio();
				e.setNome(nome);
				e.setPais(pais);
				
				
				DaoEstudio daoEst = new DaoEstudio();
				
				daoEst.save(e);
				
				request.getRequestDispatcher("/jsp/estudio/pesquisaEstudio.jsp").forward(request, response);
			}
			else if(acao.equals("atualizar")){
				daoEstudio = new DaoEstudio();
				Integer estudioID = Integer.parseInt(request.getParameter("id"));
				
				Estudio e = daoEstudio.findById(estudioID);
				
				String nome = request.getParameter("nome");
				String pais = request.getParameter("pais");
				
				e.setNome(nome);
				e.setPais(pais);
				
				daoEstudio.update(e);
								
				request.getRequestDispatcher("/jsp/estudio/pesquisaEstudio.jsp").forward(request, response);
			}
			else if(acao.equals("manter")){
				request.getRequestDispatcher("/jsp/estudio/manterEstudio.jsp").forward(request,response);
				
			}
			// permite editar o filme
			else if(acao.equals("editar")){
				Integer estudioID = Integer.parseInt(request.getParameter("id"));
				
				daoEstudio = new DaoEstudio();
				
				Estudio estudio = daoEstudio.findById(estudioID);
				
				request.setAttribute("estudio", estudio);
								
				request.getRequestDispatcher("/jsp/estudio/manterEstudio.jsp").
				forward(request,response);
				
			}else if(acao.equals("detalhe")) {
				Integer estudioID = Integer.parseInt(request.getParameter("id"));
				
				daoEstudio = new DaoEstudio();
				Estudio e = daoEstudio.findById(estudioID);
				
				HttpSession session = request.getSession(true);
				Usuario user = (Usuario)session.getAttribute("user");
				
				String x = "";
				
				if(user.getTipo().equals("admin")){
					x= x+ "<a href='/Cinema7Final/estudio?acao=editar&id="+e.getId()
					+ "' id='editar' class='btn btn-primary'>Editar</a>";
				}
				
				out.println("<h4>"+e.getNome()+"</h4>"
						+ "<p>"+e.getPais()+"</p>"
						+ x
				);
			}
			}
		}
	}
