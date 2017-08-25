package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.DaoMestre;
import model.Mestre;


public class EmailSender{
	
      public void enviar(String destinatario, String hashValidador,String path) {
    	  
            Properties props = new Properties();
            
            /** Parâmetros de conexão com servidor Gmail */
            
            props.put("mail.smtp.host", "nuvem34br.hoteldaweb.com.br");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
                        

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                            	 
                            	 String senhaEmail = "";
                                 try{
                                 	senhaEmail = new String ( Files.readAllBytes( Paths.get("c:\\temp\\senha.txt") ) );
                                 }catch (IOException e) 
                                 {
                                     e.printStackTrace();
                                 }
                                 System.out.println(senhaEmail);
                                   return new PasswordAuthentication("no-reply@turmadogrubbi.com.br",senhaEmail);
                             }
                        });
            
            System.out.println(path+"/rest/servicos/validar?hash="+hashValidador);
            /** Ativa Debug para sessão */
            session.setDebug(true);
            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("no-reply@turmadogrubbi.com.br")); //Remetente

                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse(destinatario);
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("Validar Acesso Sistema Aedes");//Assunto
                  message.setText("Caro Sr.(a), acesse: "+path+"/rest/servicos/validar?hash="+hashValidador+"  e valide seu acesso. Atenciosamente, Sistema Aedes Team.");
                  
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
                  
                  System.out.println("Feito!!!");
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      }
      
      public void recuperar(String destinatario ) {
    	  
          Properties props = new Properties();
          
          /** Parâmetros de conexão com servidor Gmail */
          
          props.put("mail.smtp.host", "nuvem34br.hoteldaweb.com.br");
          props.put("mail.smtp.socketFactory.port", "465");
          props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.port", "465");
                      

          Session session = Session.getDefaultInstance(props,
                      new javax.mail.Authenticator() {
                           protected PasswordAuthentication getPasswordAuthentication() 
                           {
                          	 
                          	 String senhaEmail = "";
                               try{
                               	senhaEmail = new String ( Files.readAllBytes( Paths.get("c:\\temp\\senha.txt") ) );
                               }catch (IOException e) 
                               {
                                   e.printStackTrace();
                               }
                               System.out.println(senhaEmail);
                                 return new PasswordAuthentication("no-reply@turmadogrubbi.com.br",senhaEmail);
                           }
                      });
          
          /** Ativa Debug para sessão */
          session.setDebug(true);
          try {
        	  
        	  	DaoMestre dao = new DaoMestre();
        	  	Mestre mestre = dao.findbyrEmail(destinatario);
        	  	
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("no-reply@turmadogrubbi.com.br")); //Remetente

                Address[] toUser = InternetAddress //Destinatário(s)
                           .parse(destinatario);
                message.setRecipients(Message.RecipientType.TO, toUser);
                message.setSubject("Recuperacao de Acesso Sistema Aedes");//Assunto
                message.setText("Caro Sr.(a), Sua senha do Sistema Aedes é: "+mestre.getSenhanu()+" . Atenciosamente, Sistema Aedes Team.");
                
                /**Método para enviar a mensagem criada*/
                Transport.send(message);
                
                System.out.println("Feito!!!");
           } catch (MessagingException e) {
                throw new RuntimeException(e);
          }
    }
}

