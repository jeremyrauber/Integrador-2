package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import model.Usuario;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RegisterServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String acao = request.getParameter("acao");
		DaoUsuario dusu;
		
		if(acao != null) {
			if(acao.equals("registrar")){
				request.getRequestDispatcher("/jsp/usuario/registrarNovoUsuario.jsp").forward(request,response);
			
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

				request.getRequestDispatcher("/jsp/usuario/index.jsp").forward(request, response);
			}
			else if(acao.equals("inserirNovo")) {
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

				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
	}
}
