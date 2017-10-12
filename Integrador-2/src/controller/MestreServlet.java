package controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.DaoMestre;
import helper.HashMD5;
import model.Mestre;

@WebServlet("/mestre")
public class MestreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String acao = request.getParameter("acao");
		HttpSession session = request.getSession(true);
		String mensagem = "";
		DaoMestre daoMestre = new DaoMestre();
		
		if(acao == null){
			System.out.println("NAO DEVIA PASAR AQUI");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
    	}else if (acao.equals("editar")) {
    		
    		Mestre mestre = (Mestre) session.getAttribute("mestre");
        	request.setAttribute("mestre", mestre);
    		request.getRequestDispatcher("jsp/mestre/manterMestre.jsp").forward(request, response);
    	}else if (acao.equals("salvar")) {
    		
    		String nome = request.getParameter("nome");
	        String data_nasc = request.getParameter("datanasc");
	        String endereco = request.getParameter("endereco");
	        String cep = request.getParameter("cep");
	        String bairro = request.getParameter("bairro");
	        String cidade = request.getParameter("cidade");
	        String estado = request.getParameter("estado");
	        
	        
	        Mestre mestre = (Mestre) session.getAttribute("mestre");
			mestre.setNome(nome);
			
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date;
			try {
				date = format.parse(data_nasc);
				mestre.setDataNascimento(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			mestre.setCep(cep);
			mestre.setEndereco(endereco);
			mestre.setBairro(bairro);
			mestre.setCidade(cidade);
			mestre.setEstado(estado);
    		
			daoMestre.update(mestre);
    		
    		System.out.println("Alterações salvas com sucesso!");
    		mensagem = "Alterações salvas com sucesso!";
        	request.setAttribute("mensagem", mensagem);
    		request.getRequestDispatcher("jsp/mestre/manterMestre.jsp").forward(request, response);
    		
    	}else if(acao.equals("alterarSenha")){
			Mestre mestre = (Mestre) session.getAttribute("mestre");
			request.setAttribute("mestre", mestre);
			request.getRequestDispatcher("jsp/mestre/alterarSenha.jsp").forward(request, response);
			
    	}else if(acao.equals("novaSenha")){
    		
    		String senhaAntiga = request.getParameter("senhaAntiga");
	        String senha = request.getParameter("senha");
	        String senhare = request.getParameter("senhare");
	        
	        
	        HashMD5 hash = new HashMD5();
	        Mestre mestre = (Mestre) session.getAttribute("mestre");
	        String senhaHash="";
	        try {
	        	senhaHash = hash.toMD5(senhaAntiga);
			} catch (Exception e) {
				mensagem = "erro no servidor";
				request.setAttribute("mensagem", mensagem);
				request.getRequestDispatcher("jsp/mestre/manterMestre.jsp").forward(request, response);
			}
	        System.out.println(mestre.getSenha()+"-"+senhaHash);
	        if(mestre.getSenha().toUpperCase().equals(senhaHash)){
	        	if(senha.equals(senhare)) {
	        		String senhaNovaHash = "";
	        		try {
	    	        	senhaNovaHash = hash.toMD5(senha);
	    	        	
	    			} catch (Exception e) {
	    				mensagem = "erro no servidor";
	    				request.setAttribute("mensagem", mensagem);
	    				request.getRequestDispatcher("jsp/mestre/manterMestre.jsp").forward(request, response);
	    			}
	        		
	        		mestre.setSenha(senhaNovaHash);
	        		daoMestre.update(mestre);
	        		request.getSession().setAttribute("mestre", mestre);
	        		
	        		request.setAttribute("mestre", mestre);
	    			mensagem = "Senha alterada com sucesso!";
	            	request.setAttribute("mensagem", mensagem);
	    			request.getRequestDispatcher("jsp/mestre/alterarSenha.jsp").forward(request, response);
	    			
	        	}else {
	        		mensagem = "Senhas não coincidem!";
					request.setAttribute("mestre", mestre);
					request.setAttribute("mensagem",mensagem);
	    			request.getRequestDispatcher("jsp/mestre/alterarSenha.jsp").forward(request, response);
	        		
	        	}	        	
	        }else {
	        	mensagem = "Senha atual incorreta!";
				request.setAttribute("mestre", mestre);
				request.setAttribute("mensagem",mensagem);
				request.getRequestDispatcher("jsp/mestre/alterarSenha.jsp").forward(request, response);
	        }
	        
	       
    		
			
			
    	}else if(acao.equals("upload")){
    		request.getRequestDispatcher("jsp/mestre/upload.jsp").forward(request, response);
    		
    	}else if(acao.equals("carregar")) {
    		
    		
    	}
	
		
		
	}
}

