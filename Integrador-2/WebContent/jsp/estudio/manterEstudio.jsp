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
${not empty estudio ? 'Atualizar ' : 'Cadastro de '} Estudio
		    </h4>
			
			<form action="<%=request.getContextPath()%>/estudio" method="POST">
			  
			  <div class="form-group">
			    <label for="nome">Nome</label>
			    <input type="text" class="form-control" value="${estudio.nome}" name="nome" id="nome" placeholder="Nome do Estudio" >
			  </div>
			  
			  <div class="form-group">
			    <label for="pais">País</label>
			    <input type="text" class="form-control" value="${estudio.pais}"placeholder="Pais" name="pais" id="pais" required>
			  </div>
			  
			  
			  <input type="hidden" name="acao" value="${not empty estudio? 'atualizar' :  'inserir'}"/>
			  <input type="hidden" name="id" value="${estudio.id}"/>
			  <button type="submit" class="btn ${not empty estudio ? 'btn-danger' : 'btn-primary'}">${not empty estudio ? 'Atualizar' : 'Inserir'}</button>
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