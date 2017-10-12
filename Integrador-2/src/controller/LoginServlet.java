package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoBairro;
import dao.DaoMestre;
import helper.HashMD5;
import model.Bairro;
import model.Mestre;

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
        System.out.println("ta na raiz");
    	DaoMestre daoMestre = new DaoMestre();
    	HashMD5 hash = new HashMD5();
    	Mestre mestre = new Mestre();
    	mestre = null;
    	
    	DaoBairro daoBairro = new DaoBairro();
    	List<Bairro> bairros = daoBairro.findOrdenado();
    	
    	String acao = request.getParameter("acao");
    	
    	String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String mensagem = "";
        request.setAttribute("login", login);
        request.setAttribute("senha", senha);
        
        System.out.println("aqui!");
        
    	if(acao == null){
    		System.out.println("Entrou primeira vez.");
    		login = "";
            senha ="";
    	}else if (acao.equals("login")) {
    		
    		if (login == null || login.isEmpty()) {
                mensagem = " Por favor entre com o login do usuario. ";
            }

            if (senha == null || senha.isEmpty()) {
                mensagem += " Por favor entre com a senha do usuario. ";
            }
            
            if (mensagem.isEmpty() && (!senha.isEmpty() && !login.isEmpty())) {
            	mestre = new Mestre();
            	
				try {
					
					mestre = daoMestre.findMestre(login,hash.toMD5(senha));
					
					//da um refresh no usuario mestre;
					mestre = daoMestre.refreshNoMestre(mestre);
					
					
					if (mestre.getNome() != null ) {
						
						if(mestre.getAtivo()){
							System.out.println("aqui mestre");
							mestre = daoMestre.refreshNoMestre(mestre);
							mestre.getEventos(); //refreshando os eventos;
			                request.getSession().setAttribute("mestre", mestre);
			                
			                request.setAttribute("mestre", mestre);
			                request.getRequestDispatcher("index.jsp").forward(request, response);
			                return;
						}else if(!mestre.getAtivo()) {
			            	 mensagem = "Verifique seu email e confirme sua conta. Caso já o tenha feito contate nossos administradores.";
			            	 mestre = null;
			            }
		            }	
				} catch (Exception e) {
					 mensagem = "Login invalido, tente novamente.";
					 mestre = null;
				}
            } 	
        }else if(acao.equals("logout")) {
        	mestre = null;
        	
        	HttpSession session = request.getSession(false);
                        
           	if(session!=null){ 
           	//If session is not null
                mensagem = "Você foi deslogado.";
                session.invalidate(); //removes all session attributes bound to the session
                System.out.println("Logged out");
           	}    	
        }else  if(acao.equals("inicial")) {
        	Mestre novoMestre = new Mestre();
        	
        	HttpSession session = request.getSession(true);
        	
        	mestre = (Mestre) session.getAttribute("mestre");
        	novoMestre = daoMestre.findMestre(mestre.getLogin(), mestre.getSenha());
        	
            request.getSession().setAttribute("mestre", novoMestre);
            request.setAttribute("bairros", bairros);
            request.setAttribute("mestre", novoMestre);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
    	
    	
    	
    	request.setAttribute("bairros", bairros);
        request.setAttribute("mensagem", mensagem);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
       
    }
}
