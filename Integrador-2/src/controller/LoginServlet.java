package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import model.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//request.getRequestDispatcher("login.jsp").forward(request, response);
    	doPost(request,response);       
    	
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String acao = request.getParameter("acao");
    	
    	if (acao=="logout"){
    		 request.getSession().removeAttribute("user");
             request.getSession().invalidate();
             request.getRequestDispatcher("login.jsp").forward(request, response);
    	}
    	
    	String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        String mensagem = "";

        if (login == null || login.isEmpty()) {
            mensagem = "Por favor entre com o nome do usuario";
        }

        if (senha == null || senha.isEmpty()) {
            mensagem = "Por favor entre com a senha";
        }

        if (mensagem.isEmpty()) {
        	DaoUsuario daoUser= new DaoUsuario();
        	Usuario user = daoUser.findByLogin(login);
        	
        	MessageDigest algorithm;
			try {
				algorithm = MessageDigest.getInstance("MD5");
			
        	byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
        	StringBuilder hexString = new StringBuilder();
        	for (byte b : messageDigest) {
        	  hexString.append(String.format("%02X", 0xFF & b));
        	}
        	String senhaMD5 = hexString.toString();
        	
	    
        	byte messageDigest2[] = algorithm.digest(user.getSenha().getBytes("UTF-8"));
        	StringBuilder hexString2 = new StringBuilder();
        	for (byte b : messageDigest) {
        	  hexString2.append(String.format("%02X", 0xFF & b));
        	}
        	String senhaBancoMD5 = hexString2.toString();
		    

//          if (user != null) {
        	if (login.equals(user.getLogin().toLowerCase()) && senhaMD5.equals(senhaBancoMD5)) {
                request.getSession().setAttribute("user", user);
                
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            } else {
                mensagem = "Login invalido, tente novamente";
            }  
		} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
				}
        }
       

        request.setAttribute("mensagem", mensagem);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}