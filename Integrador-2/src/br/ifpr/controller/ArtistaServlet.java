package br.ifpr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ifpr.dao.DaoArtista;
import br.ifpr.dao.DaoCategoria;
import br.ifpr.dao.DaoFilme;
import br.ifpr.model.Artista;
import br.ifpr.model.Categoria;
import br.ifpr.model.Filme;
import br.ifpr.model.Usuario;

@WebServlet("/artista")
public class ArtistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ArtistaServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String acao = request.getParameter("acao");
		String cat = request.getParameter("cat");
		DaoCategoria dcat;
		DaoFilme daoFilme;
		DaoArtista daoArtista;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(acao != null) {
			if(acao.equals("manter")){
				request.getRequestDispatcher("/jsp/artista/manterArtista.jsp").forward(request,response);
			} else if(acao.equals("inserir")) {
				String nome = request.getParameter("nome");
				String datanascimento = request.getParameter("datanascimento");
				String nacionalidade= request.getParameter("nacionalidade");
				String sexo; 
				if(request.getParameter("sexo").equals(0)){
					sexo = "Masculino";
				}else
					sexo ="Feminino";
				
				Artista a = new Artista();
				a.setNome(nome);
				try {
					a.setDataNascimento(sdf.parse(datanascimento));
				} catch (ParseException e) {
					e.printStackTrace();
				} // Date
				a.setNacionalidade(nacionalidade);
				a.setSexo(sexo);
				
				daoArtista = new DaoArtista();
				daoArtista.save(a);

				request.getRequestDispatcher("/jsp/artista/manterArtista.jsp").forward(request, response);
			} else if(acao.equals("editar")) {
				daoArtista = new DaoArtista();
				Integer artistaID = Integer.parseInt(request.getParameter("id"));
				
				Artista a = daoArtista.findById(artistaID);
				
				request.setAttribute("artista", a);
				
				request.getRequestDispatcher("/jsp/artista/manterArtista.jsp").forward(request, response);
			} else if(acao.equals("atualizar")) {
				daoArtista = new DaoArtista();
				Integer artistaID = Integer.parseInt(request.getParameter("id"));
				
				Artista a = daoArtista.findById(artistaID);
				
				String nome = request.getParameter("nome");
				String datanascimento = request.getParameter("datanascimento");
				String nacionalidade= request.getParameter("nacionalidade");
				String sexo; 
				if(request.getParameter("sexo").equals(0)){
					sexo = "Masculino";
				}else
					sexo ="Feminino";
				
				a.setNome(nome);
				try {
					a.setDataNascimento(sdf.parse(datanascimento));
				} catch (ParseException e) {
					e.printStackTrace();
				} // Date
				a.setNacionalidade(nacionalidade);
				a.setSexo(sexo);
				
				daoArtista.update(a);
				
				request.getRequestDispatcher("/jsp/artista/manterArtista.jsp").forward(request, response);
			} else if(acao.equals("pagePesquisa")){
				daoArtista = new DaoArtista();
				dcat = new DaoCategoria();
				
				List<Categoria> categorias = dcat.findAll();
				List<Artista> artistas = daoArtista.findAll();
				
				//findByCategoria
				
				String resposta = "";
				
				for(Artista a: artistas){
					resposta = resposta + 
					"<li><a href='' data-id='"+a.getId()+"'>"+
							a.getNome()+"</a></li>";
				}
				resposta = resposta + "</ul>";
				
				request.setAttribute("resposta", resposta);
				request.setAttribute("categorias", categorias);
				request.getRequestDispatcher("/jsp/artista/pesquisaArtista.jsp").forward(request, response);
				
			} else if(acao.equals("pesquisar")) {
				
				String query = request.getParameter("fquery");
				daoArtista = new DaoArtista();
				List<Artista> artistas = daoArtista.findByTitulo(query);
				
				String resposta = "<h4>Artistas Encontrados</h4>";
				for(Artista a: artistas) {
					resposta = resposta +
							"<li><a href='' data-id='"+a.getId()+"'>" +
							a.getNome()+"</a></li>";
				}
				
				resposta = resposta + "</ul>";
				
				out.println(resposta);
				
			} else if(acao.equals("detalhe")) {
				Integer artistaId = Integer.parseInt(request.getParameter("id"));
				
				
				
				daoArtista = new DaoArtista();
				Artista a = daoArtista.findById(artistaId);
				
				String sexo = "Masculino";
				if(a.getSexo() == "Femino") {
					sexo = "Feminino";
				}
				
				HttpSession session = request.getSession(true);
				Usuario user = (Usuario)session.getAttribute("user");
				
				String x = "";
				
				if(user.getTipo().equals("admin")){
					x= x+ "<a href='/Cinema7Final/artista?acao=editar&id="+a.getId()
					+ "' id='editar' class='btn btn-primary'>Editar</a>";
				}
				
				out.println("<h4>"+a.getNome()+"</h4>"
						+"<h5>Nacionalidade:</h5>"
						+ "<p>"+a.getNacionalidade()+"</p>"
						+"<h5>Sexo:</h5>"						
						+ "<p>"+sexo+"</p>"
						+ x);
			} else if(acao.equals("salvafilmes")) {
				daoFilme = new DaoFilme();
				daoArtista = new DaoArtista();
				
				Filme f1 = daoFilme.findById(3);
				List<Filme> filmes = new ArrayList<>();
				
				filmes.add(f1);
				
				Artista a1 = daoArtista.findById(1);
				Artista a2 = daoArtista.findById(2);
				
				a1.setFilmes(filmes);
				a2.setFilmes(filmes);
				
				daoArtista.update(a1);
				daoArtista.update(a2);
				
				out.println("SAVED IT!");	
			} else if(acao.equals("filtra")) {
				dcat = new DaoCategoria();
				daoArtista = new DaoArtista();
					
				Integer catId = Integer.parseInt(request.getParameter("cat")); 

				List<Artista> artistas = daoArtista.findByCategoria(catId);
				String resposta = "<h4>Artistas Encontrados</h4>";
				if((artistas != null) && (!artistas.isEmpty())) {
					for(Artista a: artistas) {
						resposta = resposta +
								"<li><a href='' data-id='"+a.getId()+"'>"+
								a.getNome()+"</a></li>";
					}
				}
				resposta = resposta + "</ul>";
				out.println(resposta);
			}
		}
	}
}
