package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Helper.HashMD5;
import dao.DaoMestre;
import model.Mestre;

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

		DaoMestre daoMestre = new DaoMestre();
    	HashMD5 hash = new HashMD5();
    	    	
    	String acao = request.getParameter("acao");
    	
    	String nome = request.getParameter("nome");
        String data_nasc = request.getParameter("data_nasc");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        
        String mensagem = "";
        
        
    	if(acao == null){
    		System.out.println("Entrou primeira vez.");
    		login = "";
            senha ="";
    	}
		
		if(acao != null) {
			if(acao.equals("cadastrar")){
				
				Mestre mestre = new Mestre();
				mestre.setNome(nome);
				
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				java.sql.Date data;
				try {
					data = new java.sql.Date(format.parse(data_nasc).getTime());
					mestre.setData_nasc(data);
				} catch (ParseException e) {

				}
								
				mestre.setLogin(login);
				
				try {
					mestre.setSenha(hash.toMD5(senha));
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				mestre.setEmail(email);
				mestre.setCep(cep);
				mestre.setEndereco(endereco);
				mestre.setBairro(bairro);
				mestre.setCidade(cidade);
				mestre.setEstado(estado);
				
				daoMestre.save(mestre);
				
				mensagem = "Cadastro realizado com sucesso! A confirmação de cadastro será enviada para seu email: "+email;
			}else {
				mensagem = "Houve algum erro, retorne mais tarde.";
			}			
				request.setAttribute("mensagem", mensagem);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
