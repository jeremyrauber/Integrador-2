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
${not empty categoria ? 'Atualizar ' : 'Cadastro de '} Usuario
		    </h4>
			
			<form action="<%=request.getContextPath()%>/usuario" method="POST">
			  
			  <div class="form-group">
			    <label for="login">Login</label>
			    <input type="email" class="form-control" value="${usuario.login}" name="login" id="login" placeholder="Nome do Usuario" >
			  </div>
			   <div class="form-group">
			    <label for="senha">Senha</label>
			    <input type="password" class="form-control" value="${usuario.senha}" name="senha" id="senha" placeholder="Senha" >
			  </div>
			   	<div class="form-group">
			    <label >Tipo</label>
			    <select class="form-control" name="tipo" >		    
  					<option value="admin" selected>Administrador</option>
  					<option value="normal">Normal</option>
				</select>
			  </div>
		  		  
			  <input type="hidden" name="acao" value="${not empty usuario? 'atualizar' :  'inserir'}"/>
			  <input type="hidden" name="id" value="${usuario.id}"/>
			  <button type="submit" class="btn ${not empty usuario ? 'btn-danger' : 'btn-primary'}">${not empty usuario ? 'Atualizar' : 'Inserir'}</button>
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