package br.ifpr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.*;
import java.math.*;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ifpr.dao.DaoUsuario;
import br.ifpr.model.Usuario;


/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UsuarioServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String acao = request.getParameter("acao");
		String cat = request.getParameter("cat");
		DaoUsuario dusu;

		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(acao != null) {
			if(acao.equals("manter")){
				request.getRequestDispatcher("/jsp/usuario/manterUsuario.jsp").forward(request,response);
			} else if(acao.equals("inserir")) {
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				String tipo = request.getParameter("tipo");
				
				MessageDigest algorithm;
				try {
					algorithm = MessageDigest.getInstance("MD5");
					byte messageDigest[] = algorithm.digest("senha".getBytes("UTF-8"));
					StringBuilder hexString = new StringBuilder();
					for (byte b : messageDigest) {
					  hexString.append(String.format("%02X", 0xFF & b));
					}
					  String senhaMD5 = hexString.toString();			
					    
					    Usuario u = new Usuario();
						u.setLogin(login);
						u.setSenha(senhaMD5);
						u.setTipo(tipo);
						
						dusu = new DaoUsuario();
						dusu.save(u);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}				

				request.getRequestDispatcher("/jsp/usuario/manterUsuario.jsp").forward(request, response);
			} else if(acao.equals("editar")) {
				dusu = new DaoUsuario();
				Integer usuarioID = Integer.parseInt(request.getParameter("id"));
				
				Usuario u = dusu.findById(usuarioID);
				
				request.setAttribute("usuario", u);
				
				request.getRequestDispatcher("/jsp/usuario/manterUsuario.jsp").forward(request, response);
			} else if(acao.equals("atualizar")) {
				dusu = new DaoUsuario();
				Integer usuarioID = Integer.parseInt(request.getParameter("id"));
				
				Usuario u = dusu.findById(usuarioID);
				
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				String tipo = request.getParameter("tipo");
								
				u.setLogin(login);
				u.setSenha(senha);
				u.setTipo(tipo);
				
				dusu.update(u);
				
				request.getRequestDispatcher("/jsp/usuario/manterUsuario.jsp").forward(request, response);
			} else if(acao.equals("pagePesquisa")){
				dusu = new DaoUsuario();
								
				List<Usuario> usuarios = dusu.findAll();
				
				String resposta = "";
				
				for(Usuario u: usuarios){
					resposta = resposta + 
					"<li><a href='' data-id='"+u.getId()+"'>"+
							u.getLogin()+"</a></li>";
				}
				resposta = resposta + "</ul>";
				
				request.setAttribute("resposta", resposta);
				request.setAttribute("usuarios", usuarios);
				request.getRequestDispatcher("/jsp/usuario/pesquisaUsuario.jsp").forward(request, response);
				
			} else if(acao.equals("pesquisar")) {
				
				String query = request.getParameter("fquery");
				dusu = new DaoUsuario();
				List<Usuario> usuarios = dusu.findAllByLogin(query);
				
				String resposta = "<h4>Usuarios Encontrados</h4>";
				for(Usuario u: usuarios) {
					resposta = resposta +
							"<li><a href='' data-id='"+u.getId()+"'>" +
							u.getLogin()+"</a></li>";
				}
				
				resposta = resposta + "</ul>";
				
				out.println(resposta);
				
			} else if(acao.equals("detalhe")) {
				Integer usuarioId = Integer.parseInt(request.getParameter("id"));
				
				dusu = new DaoUsuario();
				Usuario u = dusu.findById(usuarioId);		
								
				HttpSession session = request.getSession(true);
				Usuario user = (Usuario)session.getAttribute("user");
				
				String x = "";
				
				if(user.getTipo().equals("admin")){
					x= x+ "<a href='/Cinema7Final/usuario?acao=editar&id="+u.getId()
					+ "' id='editar' class='btn btn-primary'>Editar</a>";
				}
				
				out.println("<h4> Login: "+u.getLogin()+"</h4>"
						+ "<p> Senha MD5: "+u.getSenha()+"</p>"
						+ "<p> Tipo: "+u.getTipo()+"</p>"
						+ x
				);
				
			} else if(acao.equals("inserirNovo")) {
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				String tipo = request.getParameter("tipo");
				
				MessageDigest algorithm;
				try {
					algorithm = MessageDigest.getInstance("MD5");
					byte messageDigest[] = algorithm.digest("senha".getBytes("UTF-8"));
					StringBuilder hexString = new StringBuilder();
					for (byte b : messageDigest) {
					  hexString.append(String.format("%02X", 0xFF & b));
					}
					  String senhaMD5 = hexString.toString();			
					    
					    Usuario u = new Usuario();
						u.setLogin(login);
						u.setSenha(senhaMD5);
						u.setTipo(tipo);
						
						dusu = new DaoUsuario();
						dusu.save(u);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}				
				String mensagem = "Cadastro realizado com sucesso";
				request.setAttribute("mensagem", mensagem);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	
	}

}
