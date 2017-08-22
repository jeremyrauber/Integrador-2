package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import dao.DaoArtista;
import dao.DaoCategoria;
import dao.DaoEntity;
import dao.DaoFilme;
import dao.DaoVenda;
import model.Filme;
import model.Usuario;
import model.Venda;

@WebServlet("/pdv")
public class PdvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String acao = request.getParameter("acao");
		String cat = request.getParameter("cat");
		DaoCategoria dcat;
		DaoFilme daoFilme;
		DaoVenda daoVenda;
		DaoArtista daoArtista;
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		if(acao != null) {
			if(acao.equals("comprar")){
				
				daoFilme = new DaoFilme();
				
				request.setAttribute("filmes", daoFilme.findAll());
				request.getRequestDispatcher("/jsp/pdv/comprar.jsp").forward(request,response);
				
			}else if(acao.equals("listar")){
				
				daoVenda = new DaoVenda();
				daoFilme = new DaoFilme();
				
				HttpSession session = request.getSession(true);
				Usuario user = (Usuario)session.getAttribute("user");
				
				List<Filme> listaDeFilmes = new ArrayList<>();
				List<Integer> listadeIds = daoVenda.findFilmesDoUser(user.getId());
				
				for(Integer filmesids : listadeIds){
					listaDeFilmes.add(daoFilme.findById(filmesids));
				}
				
				for(Filme  filmes: listaDeFilmes){
					System.out.println(filmes.getEstudio().getNome());
				}
			
				request.setAttribute("filmes", listaDeFilmes);
				request.getRequestDispatcher("/jsp/pdv/listar.jsp").forward(request,response);
				
			}else if(acao.equals("buscaTodos")){
				
				daoFilme = new DaoFilme();
				
				Gson gson = gsonBuilder.registerTypeHierarchyAdapter(Filme.class, new FilmesAdapter()).create();
				out.print(gson.toJson(daoFilme.findAll()));
				out.flush();
				out.close();

			}else if(acao.equals("efetuarcompra")){
				Integer filmeId = Integer.parseInt(request.getParameter("idFilme"));
				daoFilme = new DaoFilme();
				
				HttpSession session = request.getSession(true);
				Usuario user = (Usuario)session.getAttribute("user");
							
				daoVenda = new DaoVenda();
			
				if( daoVenda.findByUserAndFilme(user.getId(), filmeId).equals(0) ){
				
					Venda venda = new Venda();
					
					venda.setFilme(daoFilme.findById(filmeId));
					venda.setUsuario(user);
					venda.setPreco(daoFilme.findById(filmeId).getPreco());
					venda.setCreationDate(Calendar.getInstance().getTime());
					
					daoVenda.save(venda);
					
					out.print("Venda realizada com sucexsso");
				} else {
					out.print("Filme já está em sua biblioteca");
				}
			
			}else if(acao.equals("curtidas")){
				
				
				DaoEntity<Filme,Integer> dao = new DaoEntity<>(Filme.class);
				
				Integer id = Integer.parseInt(request.getParameter("id"));
				
				Filme filme= dao.findById(id);
				
				Integer res;
				
				daoVenda = new DaoVenda();
				
				HttpSession session = request.getSession(true);
				Usuario user = (Usuario)session.getAttribute("user");
				
				Venda aTalvenda = daoVenda.findVendaByUserAndFilme(user.getId(), id);
				
				
				if (aTalvenda.getCurtiu()==0){

					if (request.getParameter("tipo").equals("like")){
						filme.setLikes(filme.getLikes()+1);
						dao.update(filme);
						res = filme.getLikes();
						aTalvenda.setCurtiu(1);
						daoVenda.update(aTalvenda);
						out.print(res);
					}
					else if (request.getParameter("tipo").equals("unlike")){
						System.out.println("aqui2");
						filme.setDislikes(filme.getDislikes()+1);
						dao.update(filme);
						res = filme.getDislikes();
						aTalvenda.setCurtiu(1);
						daoVenda.update(aTalvenda);
						out.print(res);
					}
				
				}				
			}
		}
		

	}
	
	public class FilmesAdapter implements JsonSerializer<Filme>{
		
		@Override
		public JsonElement serialize(Filme filme, java.lang.reflect.Type arg1, JsonSerializationContext arg2) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id",filme.getId());
			jsonObject.addProperty("titulo", filme.getTitulo());
			jsonObject.addProperty("descricao", filme.getDescricao());
			jsonObject.addProperty("preco", filme.getPreco());
			jsonObject.addProperty("likes", filme.getLikes());
			jsonObject.addProperty("dislikes", filme.getDislikes());
			jsonObject.addProperty("nota", filme.getNota());
			return jsonObject;
		}
	
	}
}
		
