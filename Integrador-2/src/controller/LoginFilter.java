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
	// M�todo que captura a requisi��o e onde
	// Eh realizada a decis�o para onde ir a seguir.
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {  

		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	    HttpSession session = request.getSession(true);
	     
	     
	    String loginServlet = request.getContextPath() + "/login";
	    
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginServlet);
        
        
            String rootPath = request.getScheme() + "://";
            rootPath += request.getServerName().toString();
            rootPath += (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
            rootPath += request.getContextPath();

        if (loggedIn || loginRequest) {
        	
        	// existe usu�rio logado, ele permite a requisi��o:
        	
        	Mestre usuario = (Mestre) session.getAttribute("user");
        	request.setAttribute("user", usuario);
            chain.doFilter(request, response);
            
        }else {
	    	// caso n�o exista usu�rio autenticado na sess�o
        	
	    	String uri = ((HttpServletRequest)request).getRequestURI();
			if ( uri.indexOf("/css") > 0 || uri.indexOf(rootPath+"/css") > 0){
				chain.doFilter(request, response);
			}else if ( uri.indexOf("/js") > 0 || uri.indexOf(rootPath+"/js") > 0){
				chain.doFilter(request, response);
			}else if ( uri.indexOf("/fonts") > 0 || uri.indexOf(rootPath+"/fonts") > 0){
				chain.doFilter(request, response);
			}else {
				response.sendRedirect(loginServlet);
			}
	    }
        
    }
}