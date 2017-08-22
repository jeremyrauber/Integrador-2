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
		  
		    <h4>
${not empty categoria ? 'Atualizar ' : 'Cadastro de '} Categoria
		    </h4>
			
			<form action="<%=request.getContextPath()%>/categoria" method="POST">
			  
			  <div class="form-group">
			    <label for="nome">Nome</label>
			    <input type="text" class="form-control" value="${categoria.nome}" name="nome" id="nome" placeholder="Nome da Categoria" >
			  </div>
		  
			  
			  <input type="hidden" name="acao" value="${not empty categoria? 'atualizar' :  'inserir'}"/>
			  <input type="hidden" name="id" value="${categoria.id}"/>
			  <button type="submit" class="btn ${not empty categoria ? 'btn-danger' : 'btn-primary'}">${not empty categoria ? 'Atualizar' : 'Inserir'}</button>
			</form>
			  
		</div>
		</div>
        
    </div>

      <footer class="footer">
        <p>&copy; 2016 CinemaSeven, Inc.</p>
      </footer>

    </div> 

    </body>
</html>