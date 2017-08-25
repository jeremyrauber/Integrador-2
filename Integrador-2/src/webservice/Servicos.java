package webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@Path("/servicos")
public class Servicos {
	
	@Path("/validar")
	@GET
	public String validar(@QueryParam("hash") String hashValidador){
		
		// http://localhost:8080/Integrador-2/servicos/validar?hash=123
		
		return "Suas credenciais foram verificadas corretamente no sistema. Por favor logue-se com seu usuário e senha cadastrados."+hashValidador;
	}	

}
