package webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import dao.DaoMestre;
import model.Mestre;


@Path("/servicos")
public class Servicos {
	
		
	@Path("/validar")
	@GET
	public String validar(@QueryParam("hash") String hashValidador){
		
		
		
		DaoMestre daoMestre = new DaoMestre();
		Mestre mestre = new Mestre();
		try {
			mestre = daoMestre.findByHash(hashValidador);
			mestre.setAtivo(true);
			daoMestre.update(mestre);
			return "Suas credenciais foram verificadas corretamente no sistema. Por favor logue-se com seu usuario e senha cadastrados.<br> http://localhost:8080/Integrador-2/login";
			
		}catch(Exception e){
			
			return "Houve algum problema com sua validacao. Entre em contato com nossos administradores.";
		}
		
		
	}	

}
