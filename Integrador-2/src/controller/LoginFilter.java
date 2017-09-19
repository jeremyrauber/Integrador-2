package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoMestre;
import helper.HashMD5;
import model.Mestre;


@WebFilter("/*")
public class LoginFilter implements Filter {
	
	private ServletContext context;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.context = config.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
    }

	@Override
	public void destroy() {
		
    }
	// Método que captura a requisição e onde
	// Eh realizada a decisão para onde ir a seguir.
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {  

		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	    HttpSession session = request.getSession(true);
	    DaoMestre daoMestre = new DaoMestre();
	    HashMD5 hash = new HashMD5();
	     
	     
	    String loginServlet = request.getContextPath() + "/login";
	    
        boolean loggedIn = session != null && session.getAttribute("mestre") != null;
        boolean loginRequest = request.getRequestURI().equals(loginServlet);
        
        
            String rootPath = request.getScheme() + "://";
            rootPath += request.getServerName().toString();
            rootPath += (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
            rootPath += request.getContextPath();

        if (loggedIn || loginRequest) {
        	
        	// existe usuário logado, ele permite a requisição:
        	
        	Mestre mestreSession = (Mestre) session.getAttribute("mestre");
        	Mestre mestre = mestreSession;
        	System.out.println("aqui!");
        	try {
        		mestre = daoMestre.findMestre(mestreSession.getLogin(),hash.toMD5(mestreSession.getSenhanu()));
        	}catch (Exception e) {
        		
        	}
        	request.setAttribute("mestre", mestre);
            chain.doFilter(request, response);
            
        }else {
	    	// caso não exista usuário autenticado na sessão
        	
	    	String uri = ((HttpServletRequest)request).getRequestURI();
			if ( uri.indexOf("/css") > 0 || uri.indexOf(rootPath+"/css") > 0){
				chain.doFilter(request, response);
			}else if ( uri.indexOf("/js") > 0 || uri.indexOf(rootPath+"/js") > 0){
				chain.doFilter(request, response);
			}else if ( uri.indexOf("/fonts") > 0 || uri.indexOf(rootPath+"/fonts") > 0){
				chain.doFilter(request, response);
			}else if ( uri.indexOf("/register") > 0 || uri.indexOf(rootPath+"/register") > 0){
				chain.doFilter(request, response);
			}else if ( uri.indexOf("/servicos") > 0 || uri.indexOf(rootPath+"/servicos") > 0){
				chain.doFilter(request, response);
			}else if ( uri.indexOf("/novasenha") > 0 || uri.indexOf(rootPath+"/novasenha") > 0){
				chain.doFilter(request, response);
			}else {
				response.sendRedirect(loginServlet);
			}
	    }
        
    }
}
