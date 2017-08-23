package controller;

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

import dao.DaoArtista;
import dao.DaoCategoria;
import dao.DaoEstudio;
import dao.DaoFilme;
import model.Artista;
import model.Categoria;
import model.Estudio;
import model.Filme;
import model.Usuario;

@WebServlet("/filme")
public class FilmeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String acao = request.getParameter("acao");
		String cat = request.getParameter("cat");
		DaoCategoria dcat;
		DaoFilme daoFilme;
		DaoArtista daoArtista = new DaoArtista();
		DaoEstudio daoest = new DaoEstudio();
		
		if(acao != null) {
			if(acao.equals("pagePesquisa")){
				
				dcat = new DaoCategoria();
				daoest = new DaoEstudio();
				request.setAttribute("categorias", dcat.findAll());
				request.setAttribute("estudios", daoest.findAll());
				request.getRequestDispatcher("/jsp/filme/pesquisaFilme.jsp").
				forward(request, response);
			}
			else if(acao.equals("inserir")){
				String titulo = request.getParameter("titulo");
				String ano = request.getParameter("ano");
				String descricao = request.getParameter("descricao");
				String nota = request.getParameter("nota");
				String categorias[] = request.getParameterValues("categorias[]");
				String artistas[] = request.getParameterValues("artistas[]");
				String estudioid = request.getParameter("estudio");
				String preco = request.getParameter("preco");
				String url= request.getParameter("url");
				
				
		
				Filme f = new Filme();
				f.setTitulo(titulo);
				f.setAno(Integer.parseInt(ano));
				f.setDescricao(descricao);
				f.setPreco(Double.parseDouble(preco));
				f.setUrl(url);
				f.setNota(Double.parseDouble(nota));
				String subLink = request.getParameter("url");
				String[] parts = subLink.split("=");
				f.setSublink(parts[1].trim());

				Estudio e = new Estudio();
				e = daoest.findById(Integer.parseInt(estudioid));
				
				f.setEstudio(e);
				
				List<Categoria> lista = new ArrayList<>();
				List<Artista> listaArtista = new ArrayList<>();

				
				dcat = new DaoCategoria();
				daoFilme = new DaoFilme();
				
				if(categorias != null) {
					for(String var: categorias) {
						Categoria c = dcat.findById(Integer.parseInt(var));
						lista.add(c);
					}
				}
				
				f.setCategorias(lista);
				daoFilme.save(f);
				
				if(artistas != null) {
					for(String a: artistas) {
						Artista aAux = daoArtista.findById(Integer.parseInt(a));
						Integer filmeId = f.getId();
						
						Filme filmeAux = daoFilme.findById(filmeId);
						
						List<Filme> filmesAux = new ArrayList<>();
						filmesAux.add(filmeAux);
						
						aAux.setFilmes(filmesAux);
						daoArtista.update(aAux);
					}
				}
				
				request.setAttribute("categorias", dcat.findAll());
				request.setAttribute("estudios", daoest.findAll());
				request.getRequestDispatcher("/jsp/filme/pesquisaFilme.jsp").forward(request, response);
			}
			else if(acao.equals("atualizar")){
				dcat = new DaoCategoria();
				daoFilme = new DaoFilme();
				
				String id = request.getParameter("id");
				String titulo = request.getParameter("titulo");
				String ano = request.getParameter("ano");
				String descricao = request.getParameter("descricao");
				String nota = request.getParameter("nota");
				String categorias[] = request.getParameterValues("categorias[]");
				String artistas[] = request.getParameterValues("artistas[]");
				String preco = request.getParameter("preco");
				String url= request.getParameter("url");
				
				Filme f = daoFilme.findById(Integer.parseInt(id));
				f.setTitulo(titulo);
				f.setAno(Integer.parseInt(ano));
				f.setDescricao(descricao);
				f.setPreco(Double.parseDouble(preco));
				f.setUrl(url);
				f.setNota(Double.parseDouble(nota));
				String subLink = request.getParameter("url");
				String[] parts = subLink.split("=");
				f.setSublink(parts[1].trim());
				
				List<Categoria> lista = new ArrayList<>();
				
				if(categorias != null) {
					for(String var: categorias) {
						Categoria c = dcat.findById(Integer.parseInt(var));
						lista.add(c);
					}
				}
				f.setCategorias(lista);
				
				daoFilme.update(f);
				
				if(artistas != null) {
					for(String a: artistas) {
						Artista aAux = daoArtista.findById(Integer.parseInt(a));
						Integer filmeId = f.getId();
						
						Filme filmeAux = daoFilme.findById(filmeId);
						
						List<Filme> filmesAux = new ArrayList<>();
						filmesAux.add(filmeAux);
						
						aAux.setFilmes(filmesAux);
						daoArtista.update(aAux);
					}
				} else {
					List<Artista> todosArtistas = daoArtista.findAll();
					for(Artista tA: todosArtistas) {
						List<Filme> filmeNulo = new ArrayList<>();
						tA.setFilmes(filmeNulo);
						daoArtista.update(tA);
					}
				}
				
				request.setAttribute("categorias", dcat.findAll());
				request.getRequestDispatcher("/jsp/filme/pesquisaFilme.jsp").forward(request, response);
			}
			else if(acao.equals("manter")){
				dcat = new DaoCategoria();
				
				// igual array list THEREFORE ITS UNIQUE ( sรณ um id/categoria, whatever)
				Set<Categoria> categorias = new TreeSet<Categoria>();
				categorias.addAll(dcat.findAll());
				
				Set<Artista> artistas = new TreeSet<Artista>();
				artistas.addAll(daoArtista.findAll());
				
				request.setAttribute("categorias", categorias);
				request.setAttribute("artistas", artistas);
				request.setAttribute("estudios", daoest.findAll());
				request.getRequestDispatcher("/jsp/filme/manterFilme.jsp").
				forward(request,response);
				
			}
			// permite editar o filme
			else if(acao.equals("editar")){
				Integer filmeId = Integer.parseInt(request.getParameter("id"));
				daoFilme = new DaoFilme();
				dcat = new DaoCategoria();
				Filme filme = daoFilme.findById(filmeId);
				
				request.setAttribute("filme", filme);
				
				/* Categoria */
				Set<Categoria> categorias = new TreeSet<Categoria>();
				for(Categoria c: filme.getCategorias()) {
					c.setSelecionado(true);
					categorias.add(c);
				}
				categorias.addAll(dcat.findAll());
				request.setAttribute("categorias", categorias);
				
				/* Artista */
				Set<Artista> artistas = new TreeSet<Artista>();
				List<Artista> artistasAux = daoArtista.findAll();
				for(Artista aAux: artistasAux) {
					for(Filme fAux: aAux.getFilmes()) {
						if(fAux.getId() == filme.getId()) {
							aAux.setSelecionado(true);
							artistas.add(aAux);
						}
					}
				}
				/*if(filme.getArtistas() != null) {
					for(Artista a: filme.getArtistas()) {
						a.setSelecionado(true);
						artistas.add(a);
					}
				}*/
				artistas.addAll(daoArtista.findAll());
				request.setAttribute("artistas", artistas);
				request.setAttribute("estudios", daoest.findAll());
				
				
				request.getRequestDispatcher("/jsp/filme/manterFilme.jsp").
				forward(request,response);
				
			}
			// exibe informacoes do filme
			else if(acao.equals("detalhe")){
				Integer filmeId = Integer.parseInt(request.getParameter("id"));
				daoFilme = new DaoFilme();
				Filme f = daoFilme.findById(filmeId);
				
				
				HttpSession session = request.getSession(true);
				Usuario user = (Usuario)session.getAttribute("user");
				
				String x = "";
				String y = "";
				
				if(user.getTipo().equals("admin")){
					x= x+ "<a href='/Cinema7Final/filme?acao=editar&id="+f.getId()
					+"' id='editar' class='btn btn-primary'>Editar</a>";
				}
				String htmlArtista = "<p><h4>Elenco: </h4>";
				
				for(Artista a: f.getArtistas()) {
					if(user.getTipo().equals("admin")){
						htmlArtista = htmlArtista +	"<a href='/Cinema7Final/artista?acao=editar&id="+
										a.getId()+"'>"+a.getNome()+"</a><br>";
					}else{
						htmlArtista = htmlArtista +a.getNome()+"<br>";
					}
							
				}
				
				htmlArtista = htmlArtista + "</p>";
				
				
				
				
				out.println("<h4>"+f.getTitulo()+"</h4>"
				+ "<p>"+f.getAno()+"</p>"
				+ "<p>"+f.getDescricao()+"</p>"
				+ "<h4>Nota: </h4>"
				+ "<p>"+f.getNota()+"</p>"
				+ "<h4>Estudio: </h4>"
				+ "<p>"+f.getEstudio().getNome()+"</p>"
				+ x
				+ htmlArtista
				);
			}
			// filtra por categoria
			else if(acao.equals("filtra")){
				dcat = new DaoCategoria();
				
				// cat = 1,2,6,9, ..
				String catIds = request.getParameter("cat");
				String arrayCat[] = catIds.split(",");

				Set<Filme> filmes = new TreeSet<>();
				
				// conversao de ID para Categoria
				for(String catId: arrayCat) {
					if(catId!=null && catId.length()>0) {
						Categoria categoria = dcat.findById(Integer.parseInt(catId));
					
						// adiciona todos os filmes desta categoria na lista
						filmes.addAll(categoria.getFilmes());
					}
				}
				
				String resposta;
				resposta = "<h4>Filmes encontrados</h4><br><ul>";

				for(Filme f: filmes){
					resposta = resposta + 
							"<li><a href='' data-id='"+f.getId()+"'>"+
							f.getTitulo()+"</a></li>";
				}
				resposta = resposta + "</ul>";
				out.println(resposta);
			}
			else if(acao.equals("filtra2")){
				daoest = new DaoEstudio();
				
				// cat = 1,2,6,9, ..
				String estIds = request.getParameter("est");
				String arrayEst[] = estIds.split(",");

				Set<Filme> filmes = new TreeSet<>();
				
				// conversao de ID para Categoria
				for(String estId: arrayEst) {
					if(estId!=null && estId.length()>0) {
						Estudio estudio = daoest.findById(Integer.parseInt(estId));
					
						// adiciona todos os filmes desta categoria na lista
						filmes.addAll(estudio.getFilmes());
					}
				}
				
				String resposta;
				resposta = "<h4>Filmes encontrados</h4><br><ul>";

				for(Filme f: filmes){
					resposta = resposta + 
							"<li><a href='' data-id='"+f.getId()+"'>"+
							f.getTitulo()+"</a></li>";
				}
				resposta = resposta + "</ul>";
				out.println(resposta);
			}else if(acao.equals("filtralike")){
				String parametro = request.getParameter("parametro");
				daoFilme = new DaoFilme();
				
				Set<Filme> filmes = new TreeSet<>();
				
				// conversao de ID para Categoria
				filmes.addAll(daoFilme.findByLike(parametro));	
				
				String resposta;
				resposta = "<h4>Filmes encontrados</h4><br><ul>";

				for(Filme f: filmes){
					resposta = resposta + 
							"<li><a href='' data-id='"+f.getId()+"'>"+
							f.getTitulo()+"</a></li>";
				}
				resposta = resposta + "</ul>";
				
				out.println(resposta);
			}
			else if(acao.equals("pesquisar")){
				String query = request.getParameter("fquery");
				daoFilme = new DaoFilme();
				List<Filme> filmes = daoFilme.findByTitulo(query);
				
				String resposta = "<h4>Filmes encontrados</h4><br><ul>";
				
				for(Filme f: filmes){
					resposta = resposta + 
					"<li><a href='' data-id='"+f.getId()+"'>"+
							f.getTitulo()+"</a></li>";
				}
				resposta = resposta + "</ul>";
				
				out.println(resposta);
				
			}
		}
	}
}


