package webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import dao.DaoAtividade;
import dao.DaoEvento;
import dao.DaoEventoAtividade;
import dao.DaoEventoUsuario;
import dao.DaoMestre;
import dao.DaoUsuario;
import dao.DaoUsuarioAtividade;
import model.Evento;
import model.EventoAtividade;
import model.EventoUsuario;
import model.EventoUsuarioId;
import model.Mestre;
import model.Usuario;
import model.UsuarioAtividade;
import model.UsuarioAtividadeId;


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
		
		try {		
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
		}catch(Exception e){
			
			return "Houve algum problema em nossos servidores. Entre em contato com nossos administradores.";
		}
		
		
	}	
	  
	    @POST  
	    @Path("/upload")  
	    @Consumes(MediaType.MULTIPART_FORM_DATA)  
	    public Response uploadFile(  
	            @FormDataParam("file") InputStream uploadedInputStream,  
	            @FormDataParam("file") FormDataContentDisposition fileDetail,
	            @FormDataParam("usuario") String usuario,
	            @FormDataParam("atividade") String atividade,
	            @FormDataParam("evento") String evento) {  
	    	
	    		DaoEventoAtividade daoEventoAtividade = new DaoEventoAtividade();
	    		DaoUsuarioAtividade daoUsuarioAtividade = new DaoUsuarioAtividade();
	    		
	    		List<EventoAtividade> atividades = daoEventoAtividade.findByEventoID(Integer.parseInt(evento));
	    		Integer validadorAtiv=0;
	    		for(EventoAtividade ativ : atividades)
	    		{
	    			if(Integer.parseInt(atividade)==ativ.getId().getIdAtividade())
	    				validadorAtiv=1;
	    		}
	    		
	    		DaoEventoUsuario daoEventoUsuario = new DaoEventoUsuario();
	    		List<EventoUsuario> eventoUsuarios = daoEventoUsuario.findByEventoId(Integer.parseInt(evento));
	    		Integer validadorUsu=0;
	    		for(EventoUsuario ev : eventoUsuarios)
	    		{
	    			if(Integer.parseInt(usuario)==ev.getUsuario().getId())
	    				validadorUsu=1;
	    				
	    		}

	    		if(validadorAtiv==0 || validadorUsu==0)
	    		{
	    			 String output = "Usuario, evento ou atividade incorretos, verifique e tente novamente!! ";   
	 	            return Response.status(200).entity(output).build();
	    		}
	    		else{
		    		String nomeArquivo ="usuario"+usuario+"_atividade"+atividade+"_evento"+evento;
		            String fileLocation = "C:\\Users\\usr\\Git\\Integrador-2\\WebContent\\images\\"+nomeArquivo+".jpg";  
		            
		            try {  
		                FileOutputStream out = new FileOutputStream(new File(fileLocation));  
		                int read = 0;  
		                byte[] bytes = new byte[1024];  
		                out = new FileOutputStream(new File(fileLocation));  
		                while ((read = uploadedInputStream.read(bytes)) != -1) {  
		                    out.write(bytes, 0, read);  
		                }  
		                out.flush();  
		                out.close();
		              
		            } catch (IOException e) {e.printStackTrace();}
		            
		            DaoAtividade daoAtividade = new DaoAtividade();
		            DaoEvento daoEvento = new DaoEvento();
		            DaoUsuario daoUsuario = new DaoUsuario();
		            
		            UsuarioAtividade obj = new UsuarioAtividade();
		            UsuarioAtividadeId idua = new UsuarioAtividadeId();
		            
		            idua.setIdAtividade(Integer.parseInt(atividade));
		            idua.setIdEvento(Integer.parseInt(evento));
		            idua.setIdUsuario(Integer.parseInt(usuario));
		            
		            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
		            obj.setId(idua);
		            obj.setAtividade(daoAtividade.findById(Integer.parseInt(atividade)));
		            obj.setEvento(daoEvento.findById(Integer.parseInt(evento)));
		            obj.setUsuario(daoUsuario.findById(Integer.parseInt(usuario)));
		            obj.setDataFimAtividade(date);
		            obj.setStatus(false);
		            obj.setCaminhoImagem("images/"+nomeArquivo+".jpg");
		            
		            daoUsuarioAtividade.save(obj);
		            
		            String output = "Imagem carregada com sucesso para : " + fileLocation;  
		            return Response.status(200).entity(output).build();
	    		}
        }  
}  


