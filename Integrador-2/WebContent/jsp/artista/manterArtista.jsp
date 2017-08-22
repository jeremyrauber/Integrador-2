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
${not empty artista? 'Atualizar ' : 'Cadastro de '} Artista
		    </h4>
			
			<form action="<%=request.getContextPath()%>/artista" method="POST">
			  
			  <div class="form-group">
			    <label for="nome">Nome</label>
			    <input type="text" class="form-control" value="${artista.nome}" name="nome" id="nome" placeholder="Nome do Artista" required autocomplete="off">
			  </div>
			  
			  <div class="form-group">
			    <label for="datanascimento">Data de Nascimento</label>
			    <input type="date" class="form-control" value="<fmt:formatDate type="date" value="${artista.dataNascimento}" pattern="yyyy-MM-dd" />" name="datanascimento" id="datanascimento" required>
			  </div>
	  
			  <div class="form-group">
			    <label for="nacionalidade">Nacionalidade</label>
			    <input type="text" class="form-control" value="${artista.nacionalidade}" name="nacionalidade" id="nacionalidade" placeholder="Nacionalidade do Artista" required autocomplete="off">
			  </div>
			  
			  <div class="form-group">
			  	<label for="sexo">Sexo</label>
			  	<select name="sexo" id="sexo">
			  		<option value="M">
			  			<c:if test="${artista.sexo eq masculino}">
							selected
						</c:if>
			  		Masculino</option>
			  		<option value="1">
			  			<c:if test="${artista.sexo eq feminino}">
							selected
						</c:if>
			  		Feminino</option>
			  	</select>
			  </div>
			  
			  <input type="hidden" name="acao" value="${not empty artista? 'atualizar' :  'inserir'}"/>
			  <input type="hidden" name="id" value="${artista.id}"/>
			  <button type="submit" class="btn ${not empty artista ? 'btn-danger' : 'btn-primary'}">${not empty artista ? 'Atualizar' : 'Inserir'}</button>
			</form>
			  
		</div>
		</div>
        
    </div>

      <footer class="footer">
        <p>&copy; 2016 CinemaSeven, Inc.</p>
      </footer>

    </div> <!-- /container -->

    </body>
</html>