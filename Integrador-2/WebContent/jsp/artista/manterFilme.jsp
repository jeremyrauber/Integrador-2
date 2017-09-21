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
${not empty filme? 'Atualizar Filme' : 'Cadastro de filme'}
		    </h4>
			
			<form action="<%=request.getContextPath()%>/filme" method="POST">
			  
			  <div class="form-group">
			    <label for="titulo">Título</label>
			    <input type="text" class="form-control" value="${filme.titulo}" name="titulo" id="titulo" placeholder="Título do filme">
			  </div>
			  <div class="form-group">
			    <label for="ano">Ano lançamento</label>
			    <input type="number" class="form-control" value="${filme.ano}" name="ano" id="ano" placeholder="2016" required autocomplete="off">
			  </div>
			  <div class="form-group">
			    <label for="descricao">Descrição</label>
			    <textarea class="form-control" id="descricao" name="descricao" required cols="20" rows="4">${filme.descricao}</textarea>
			  </div>
			  <div class="form-group">
			    <label for="url">URL</label>
			    <input type="text" class="form-control" value="${filme.url}" name="url" id="url" placeholder="www.youtube.com.br" >
			  </div>
			  <div class="form-group">
			    <label for="nota">Nota</label>
			    <input type="number" class="form-control" value="${filme.nota}" name="nota" id="nota" placeholder="0-10" min="0" max="10">
			  </div>
			   <div class="form-group">
			    <label for="preco">Preço</label>
			    <input type="number" class="form-control" id="preco" name="preco" value="${filme.preco}" placeholder="R$0.00" step="0.01" />
			  </div>
			  
			  <div class="checkbox">
			    <h5><b>Categorias</b></h5>
			    <c:forEach items="${categorias}" var="cat">
			    	<input type="checkbox" name="categorias[]" value="${cat.id}" ${cat.selecionado ? 'checked' : '' } />
			    	${cat.nome}<br>
			    </c:forEach>
			  </div>
			  <div class="sel">
			    <h5><b>Estudio</b></h5>
			    <select name="estudio">
					<c:forEach items="${estudios}" var="est">
						<option value="${est.id}">${est.nome}</option>
					</c:forEach>
				</select>
			  </div>
			  <div class="checkbox">
			  	<h5><b>Artistas</b></h5>
			  	<c:forEach items="${artistas}" var="art">
			  		<input type="checkbox" name="artistas[]" value="${art.id}" ${art.selecionado ? 'checked' : '' } />
			  		${art.nome}<br>
			  	</c:forEach>
			  </div>
			  <input type="hidden" name="acao" value="${not empty filme? 'atualizar' :  'inserir'}"/>
			  <input type="hidden" name="id" value="${filme.id}"/>
			  <button type="submit" class="btn ${not empty filme ? 'btn-danger' : 'btn-primary'}">${not empty filme ? 'Atualizar' : 'Inserir'}</button>
			</form>
		   
		   </div>
		   
		   <!-- detalhe filme -->
		  <div class="col-md-8">
		  	<div id="detalheFilme"></div>
		  </div>
		</div>
        
    </div>

      <footer class="footer">
        <p>&copy; 2016 CinemaSeven, Inc.</p>
      </footer>

    </div> <!-- /container -->

    </body>
</html>