package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoMestre;
import helper.EmailSender;
import helper.HashMD5;
import model.Mestre;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("--------------------entrou aqui");
		EmailSender es = new EmailSender();
		
		DaoMestre daoMestre = new DaoMestre();
    	HashMD5 hash = new HashMD5();
    	    	
    	String acao = request.getParameter("acao");
        System.out.println(acao);
        String mensagem = "";
    	mensagem ="nenhum if";
        
    	if(acao == null){
    		mensagem ="";
            return;
    	}
		
		if(acao != null) {
			
			if(acao.equals("cadastrar")){
				
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
				
				Integer existeMestre = new Integer(daoMestre.findExistingLoginOrEmail(login,email).intValue());
				
				if(existeMestre == 0) {
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
					mestre.setSenhanu(senha);
					mestre.setCep(cep);
					mestre.setEndereco(endereco);
					mestre.setBairro(bairro);
					mestre.setCidade(cidade);
					mestre.setEstado(estado);
					
					String uuid = UUID.randomUUID().toString();
					
					String hashValidador;
					
					try {
						hashValidador = hash.toMD5(uuid);
						mestre.setHashValidador(hashValidador);
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					
					String rootPath = request.getScheme() + "://";
		            rootPath += request.getServerName().toString();
		            rootPath += (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
		            rootPath += request.getContextPath();
					
					
					daoMestre.save(mestre);
					
					es.enviar(mestre.getEmail(),mestre.getHashValidador(),rootPath);
					
					mensagem = "Cadastro realizado com sucesso! A confirmação de cadastro será enviada para seu email: "+email
							+". Só será permitida a entrada após validação.";
				}else {
					mensagem = "Login e/ou email já em uso! Favor tentar outro ou recuperar sua senha";
					request.setAttribute("nome", nome);
					request.setAttribute("data_nasc", data_nasc);
					request.setAttribute("senhacad", senha);
					request.setAttribute("logincad", login);
					request.setAttribute("endereco", endereco);
					request.setAttribute("cep", cep);
					request.setAttribute("email", email);
					request.setAttribute("bairro", bairro);
					request.setAttribute("cidade", cidade);
					request.setAttribute("estado", estado);
					
				}
			}else if(acao.equals("recuperar")){
				
				System.out.println("entrou aqui"+acao);
				String email = request.getParameter("email");
				es.recuperar(email);
				mensagem = "Email enviado com sucesso!";
				
			
			}else {
				mensagem = "Houve algum erro, retorne mais tarde.";
			}
			
				request.setAttribute("mensagem", mensagem);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
		}
	}
}
