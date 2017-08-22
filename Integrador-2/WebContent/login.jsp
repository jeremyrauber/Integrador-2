<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
	<%@include file="/jsp/inc/topo.jsp" %>
	<title>Login | Sistema</title>
  </head>
  <body>
 <div class="container">
  
      
      
      <div class="row marketing">      
            
		<div class="row">
		    <!-- filtro -->
		  <div class="col-md-6">	

		<c:if test="${not empty mensagem}">	
			<div class="">
			<c:if test="${mensagem ne 'Cadastro realizado com sucesso'}">	
				<p class="bg-danger">${mensagem} !</p>
			</c:if>
			<c:if test="${mensagem eq 'Cadastro realizado com sucesso'}">
				<p class="bg-success">${mensagem} !</p>
	  		</c:if>
			</div>
		</c:if>
		</div>
		</div>
		<div class="row">
	 <div class="col-md-4">		
	<h2>Login do Sistema</h2>
	<form method="POST" action="<%=request.getContextPath()%>/login">
		Usuário: <input class="form-control" type="text" name="login" /><br>
		Senha: <input class="form-control" type="password" name="senha" /><br>
		 <button type="submit" class="btn btn-primary"> Logar </button>	
	</form>
	<br>
	<label><a href="<%=request.getContextPath()%>/register?acao=registrar" >Registre-se</a></label>
		   </div>		   
		  
		</div>
        
    </div>
      <footer class="footer">
        <p>&copy; 2016 CinemaSeven, Inc.</p>
      </footer>

    </div> <!-- /container -->

    </body>
</html>