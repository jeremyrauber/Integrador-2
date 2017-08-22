package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Helper.HashMD5;
import dao.DaoMestre;
import dao.DaoUsuario;
import model.Mestre;
import model.Usuario;

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
        
    	DaoMestre daoMestre = new DaoMestre();
    	HashMD5 hash = new HashMD5();
    	
    	
    	String acao = request.getParameter("acao");
    	String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        String mensagem = "";
        
    	
    	if(acao == null){
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    	}else if (acao.equals("login")) {
    		
    		if (login == null || login.isEmpty()) {
                mensagem = " Por favor entre com o login do usuario. ";
            }

            if (senha == null || senha.isEmpty()) {
                mensagem += " Por favor entre com a senha do usuario. ";
            }
            
            if (mensagem.isEmpty() && (!senha.isEmpty() && !login.isEmpty())) {
            	Mestre mestre;
            	
            	
				try {
					mestre = daoMestre.findMestre(login,hash.toMD5(senha));
				} catch (Exception e) {
					mestre = null;
				}

	
				if (mestre != null) {
	                request.getSession().setAttribute("user", mestre);
	                
	                response.sendRedirect(request.getContextPath() + "/index.jsp");
	                return;
	            }else {
	                mensagem = "Login invalido, tente novamente.";
	            }
            } 	
        }else if(acao.equals("logout")) {
            	
           	HttpSession session = request.getSession(false);
           	
           	if(session!=null){ 
           	//If session is not null
                mensagem = "Você foi deslogado.";
                session.invalidate(); //removes all session attributes bound to the session
                System.out.println("Logged out");
           	}    	
           	request.setAttribute("mensagem", mensagem); 
        }    	    	
        request.setAttribute("mensagem", mensagem);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
