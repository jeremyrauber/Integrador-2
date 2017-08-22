package br.ifpr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ifpr.dao.DaoArtista;
import br.ifpr.dao.DaoCategoria;
import br.ifpr.dao.DaoEstudio;
import br.ifpr.model.Artista;
import br.ifpr.model.Categoria;
import br.ifpr.model.Estudio;
import br.ifpr.model.Usuario;

/**
 * Servlet implementation class CategoriaServlet
 */
@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String acao = request.getParameter("acao");
		String cat = request.getParameter("cat");
		DaoCategoria daocat;
		
		
		if(acao != null) {
			if(acao.equals("pagePesquisa")){
				daocat = new DaoCategoria();
				
				List<Categoria> categorias = daocat.findAll();
				
				//findByCategoria
				
				String resposta = "";
				
				for(Categoria c: categorias){
					resposta = resposta + 
					"<li><a href='' data-id='"+c.getId()+"'>"+
							c.getNome()+"</a></li>";
				}
				resposta = resposta + "</ul>";
				
				request.setAttribute("resposta", resposta);
				request.getRequestDispatcher("/jsp/categoria/pesquisaCategoria.jsp").forward(request, response);
			}
			else if(acao.equals("inserir")){
				String nome = request.getParameter("nome");
			
		
				Categoria c = new Categoria();
				c.setNome(nome);
								
				
				 daocat = new DaoCategoria();
				
				 daocat.save(c);
				
				request.getRequestDispatcher("/jsp/categoria/pesquisaCategoria.jsp").forward(request, response);
			}
			else if(acao.equals("atualizar")){
				daocat = new DaoCategoria();
				Integer catID = Integer.parseInt(request.getParameter("id"));
				
				Categoria c = daocat.findById(catID);
				
				String nome = request.getParameter("nome");
				
				
				c.setNome(nome);
						
				daocat.update(c);
								
				request.getRequestDispatcher("/jsp/categoria/pesquisaCategoria.jsp").forward(request, response);
			}
			else if(acao.equals("manter")){
				request.getRequestDispatcher("/jsp/categoria/manterCategoria.jsp").forward(request,response);
				
			}
			// permite editar o filme
			else if(acao.equals("editar")){
				Integer catID = Integer.parseInt(request.getParameter("id"));
				
				daocat = new DaoCategoria();
				
				Categoria c = daocat.findById(catID);
				
				request.setAttribute("categoria", c);
								
				request.getRequestDispatcher("/jsp/categoria/manterCategoria.jsp").
				forward(request,response);
				
			}else if(acao.equals("detalhe")) {
				Integer catID = Integer.parseInt(request.getParameter("id"));
				
				daocat = new DaoCategoria();
				Categoria c = daocat.findById(catID);
				
				HttpSession session = request.getSession(true);
				Usuario user = (Usuario)session.getAttribute("user");
				
				String x = "";
				
				if(user.getTipo().equals("admin")){
					x= x+ "<a href='/Cinema7Final/categoria?acao=editar&id="+c.getId()
					+ "' id='editar' class='btn btn-primary'>Editar</a>";
				}
				out.println("<h4>"+c.getNome()+"</h4>"
						+ x
				);
			}else if(acao.equals("pesquisar")) {
				
				String query = request.getParameter("fquery");
				daocat = new DaoCategoria();
				List<Categoria> categorias = daocat.findByNome(query);
				
				String resposta = "<h4>Categorias Encontradas</h4>";
				for(Categoria c: categorias) {
					resposta = resposta +
							"<li><a href='' data-id='"+c.getId()+"'>" +
							c.getNome()+"</a></li>";
				}
				
				resposta = resposta + "</ul>";
				
				out.println(resposta);
				
			}
		}
	}

}
