package webservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import dao.DaoEventoUsuario;
import dao.DaoMestre;
import model.Evento;
import model.EventoUsuario;
import model.EventoUsuarioId;
import model.Mestre;
import model.Usuario;


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
	
	//http://localhost:8080/Integrador-2/rest/servicos/entrar_evento?id_evento=1&id_usuario=5
	@Path("/entrar_evento")
	@GET
	public String entrar(@QueryParam("id_usuario") Integer id_usuario,@QueryParam("id_evento") Integer id_evento) {
		
				
		DaoEventoUsuario daoEventoUsuario = new DaoEventoUsuario();
		EventoUsuario eu = new EventoUsuario();
		EventoUsuarioId euid = new EventoUsuarioId();		
		Usuario u1 = new Usuario();
		Evento e1 = new Evento();
		euid.setIdEvento(id_evento);
		euid.setIdUsuario(id_usuario);
	
		u1.setId(id_usuario);
		e1.setId(id_evento);
		eu.setUsuario(u1);
		eu.setEvento(e1);
		eu.setId(euid);
		eu.setBanidoEvento(false);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		eu.setDataVinculo(date);
		
		daoEventoUsuario.save(eu);
			
			return "Entrou no evento com sucesso";
			
	/*	}catch(Exception e){
			
			return "Houve algum problema em nossos servidores. Entre em contato com nossos administradores.";
		}
		*/
		
	}	

}
