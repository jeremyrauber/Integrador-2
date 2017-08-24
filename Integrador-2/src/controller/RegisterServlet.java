package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("--------------------entrou aqui");
		
		
		DaoMestre daoMestre = new DaoMestre();
    	HashMD5 hash = new HashMD5();
    	    	
    	String acao = request.getParameter("acao");
    	
    	String nome = request.getParameter("nome");
        String data_nasc = request.getParameter("data_nasc");
        String login = request.getParameter("login");
        String senha = request.getParameter("text_senha");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        
        System.out.println(senha+"-"+data_nasc);
        
        String mensagem = "";
    	mensagem ="nenhum if";
        
    	if(acao == null){
    		mensagem ="acao nula";
    		login = "";
            senha ="";
    	}
		
		if(acao != null) {
			if(acao.equals("cadastrar")){
				
				Mestre mestre = new Mestre();
				mestre.setNome(nome);
				
				
				 DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				 Date date;
				try {
					date = format.parse(data_nasc);
					mestre.setDataNascimento(date);
				} catch (ParseException e1) {
					e1.printStackTrace();
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
				
				mensagem = "Cadastro realizado com sucesso! A confirmação de cadastro será enviada para seu email: "+email
						+"Só será permitida a entrada após validação.";
			}else {
				mensagem = "Houve algum erro, retorne mais tarde.";
			}			
				request.setAttribute("mensagem", mensagem);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
