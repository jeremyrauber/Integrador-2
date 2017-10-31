package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.DaoMestre;
import model.Mestre;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mensagem ="";
		HttpSession session = request.getSession(true);
		DaoMestre daoMestre = new DaoMestre();
		
		/*Identifica se o formulario é do tipo multipart/form-data*/
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
            	// Create a new file upload handler    
                @SuppressWarnings("deprecation")
				DiskFileUpload upload = new DiskFileUpload();    
                // Set upload parameters    
                upload.setSizeMax(5*1024*1024); //5Mb    
               // upload.setRepositoryPath("/home/kde/file");    
                // Parse the request    
                @SuppressWarnings("deprecation")
				List items = upload.parseRequest(request);    
                Iterator it = items.iterator();    
                while (it.hasNext()) {    
                   FileItem fitem = (FileItem) it.next();    
                   if (!fitem.isFormField()) {  
                	 Mestre mestre = (Mestre) session.getAttribute("mestre");
                	 String momeArquivo =mestre.getLogin()+ fitem.getName().substring(fitem.getName().length() - 4);
                	 	
                  	 File uploadedFile = new File("C:\\Users\\usr\\Git\\Integrador-2\\WebContent\\images\\usuarios\\"+momeArquivo);
                  	 //File uploadedFile = new File("c:\\Users\\Crash\\git\\Integrador-2\\Integrador-2\\WebContent\\images\\usuarios\\"+momeArquivo);
                	// File uploadedFile = new File(getServletContext().getRealPath("images/usuarios/")+momeArquivo);
                	 
                	 System.out.println(getServletContext().getRealPath("images/usuarios/"));
                  	 fitem.write(uploadedFile);
                            	     	
                     mestre.setCaminhoImagem("images/usuarios/"+momeArquivo);
                  	 daoMestre.update(mestre);
                  	 
                  	 
                  	 Mestre novoMestre = new Mestre();
                  	 novoMestre = daoMestre.findMestre(mestre.getLogin(), mestre.getSenha());
                  	 request.getSession().setAttribute("mestre", novoMestre);
                  	 
                  }    
                }
               
                request.setAttribute("messagem", "Arquivo carregado com sucesso");
            } catch (Exception ex) {
                request.setAttribute("messagem", "Upload de arquivo falhou devido a "+ ex);
            }
 
        } else {
            request.setAttribute("messagem","Desculpe este Servlet lida apenas com pedido de upload de arquivos");
        }
        mensagem = "Upload efetuado com sucesso!";
        
        request.setAttribute("mensagem",mensagem);
        request.getRequestDispatcher("jsp/mestre/upload.jsp").forward(request, response);
	}

}
