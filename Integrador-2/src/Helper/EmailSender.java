package Helper;

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
import com.sun.mail.util.MailLogger;
import com.sun.mail.util.BASE64DecoderStream;



public class EmailSender{
	
      public void enviar(String destinatario) {
    	  
            Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "mail.turmadogrubbi.com.br");
            props.put("mail.smtp.socketFactory.port", "25");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "false");
            props.put("mail.smtp.port", "25");
                        

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

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("no-reply@turmadogrubbi.com.br")); //Remetente

                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse(destinatario);
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("Enviando email com JavaMail");//Assunto
                  message.setText("Enviei este email utilizando JavaMail com minha conta maluca!");
                  
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
                  
                  System.out.println("Feito!!!");
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      }
}

