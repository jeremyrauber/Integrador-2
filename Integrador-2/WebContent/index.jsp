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
Data de entrega/apresentação: 06/12 
# Operações CRUD (manter)
- Filme (preço, url, like e dislike)
- Artista (nome, data_nascimento)
- Categoria (nome)
- Usuário (admin, normal)
(login - email, senha - md5)
- Estúdio (nome, pais)
# Funcionalidades
- Usuários devem logar!
- Usúario pode comprar filmes 
- Usuário possui uma biblioteca de filmes, disponível
na área do usuário, e pode assistir a qualquer momento
- filtros de filmes 
- (melhores filmes - aqueles com mais likes)
- por categoria
- por estúdio

Filme <-> Usuário (n-n) @ManyToMany

Filme possui n vendas => venda possui 1 filme @ManyToOne
Usuário possui n vendas => venda possui 1 usuario @ManyToOne
==> criar uma entidade Venda (ou Biblioteca)
é importante ter o valor que foi pago e quando 
foi feita a compra

-->
