package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoLogin;
import DAO.DaoMestre;
import Model.Mestre;



@WebServlet("/login")
public class ControllerLogin extends HttpServlet {
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
     DaoMestre dao = new DaoMestre();
      
     List<Mestre> mestre = dao.findAll();
     
      request.setAttribute("mestre", mestre);
      request.getRequestDispatcher("index.jsp").forward(request, response);
      
      /*
     
     String senhaDeCrypt =  "adjhajklsdas";
     
     if (password.equals(senhaDeCrypt)){
    	 
    	 //usuario logado

    	
    	 request.getSession().setAttribute("userlogged", user);
    	 request.setAttribute("aviso","Bem vindo: "+user+" !");
         request.getRequestDispatcher("index.jsp").forward(request, response);
     }else{
    	 request.setAttribute("aviso","Usuário/Senha Inválido");
    	 request.getRequestDispatcher("login.jsp").forward(request, response);
     }
*/
    }
 
}