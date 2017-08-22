package br.ifpr.controller;

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

	// Metodo que captura a requisição e onde
	// Eh realizada a decisão para onde ir a seguir.
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {  

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = ((HttpServletRequest)request).getRequestURI();
       
        if ( uri.indexOf("/css") > 0 ){
        	chain.doFilter(request, response);
        }else if ( uri.indexOf("/js") > 0 ){
        	chain.doFilter(request, response);
        }else if ( uri.indexOf("/bootstrap") > 0 ){
        	chain.doFilter(request, response);
        }else if ( uri.indexOf("/register") > 0 ){
        	chain.doFilter(request, response);
        }else if ( uri.indexOf("/usuario") > 0 ){
        	chain.doFilter(request, response);
        }
        
        
        HttpSession session = request.getSession(false);
        
        String loginServlet = request.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginServlet);

        if (loggedIn || loginRequest) {
        	// existe usuario logado, ele permite a requisicao:
            chain.doFilter(request, response);
        } else {
        	// caso nao exista usuario autenticado na sessao
            response.sendRedirect(loginServlet);
        }
        
        
        
        
    }
}
