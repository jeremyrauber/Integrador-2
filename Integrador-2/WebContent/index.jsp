<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
	<%@include file="/jsp/inc/topo.jsp" %>
  </head>
  <body>

    <div class="container">
    
      <%@include file="/jsp/inc/menu.jsp" %>
      
      <div class="row marketing">      
            
		<div class="row">
		    <!-- filtro -->
		  <div class="col-md-4">		   		   
		   </div>		   
		  
		</div>
        
    </div>
      <footer class="footer">
        <p>&copy; 2016 CinemaSeven, Inc.</p>
      </footer>

    </div> <!-- /container -->

    </body>
</html>


<!--  
Data de entrega/apresenta��o: 06/12 
# Opera��es CRUD (manter)
- Filme (pre�o, url, like e dislike)
- Artista (nome, data_nascimento)
- Categoria (nome)
- Usu�rio (admin, normal)
(login - email, senha - md5)
- Est�dio (nome, pais)
# Funcionalidades
- Usu�rios devem logar!
- Us�ario pode comprar filmes 
- Usu�rio possui uma biblioteca de filmes, dispon�vel
na �rea do usu�rio, e pode assistir a qualquer momento
- filtros de filmes 
- (melhores filmes - aqueles com mais likes)
- por categoria
- por est�dio

Filme <-> Usu�rio (n-n) @ManyToMany

Filme possui n vendas => venda possui 1 filme @ManyToOne
Usu�rio possui n vendas => venda possui 1 usuario @ManyToOne
==> criar uma entidade Venda (ou Biblioteca)
� importante ter o valor que foi pago e quando 
foi feita a compra

-->
