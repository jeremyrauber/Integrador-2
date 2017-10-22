<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  	<head>
		<%@include file="/jsp/inc/topo.jsp" %>
			<link href="<%=request.getContextPath()%>/css/login/login.css" rel="stylesheet">
			<style>
				footer {
				  position: absolute;
				  bottom: 0;
				}
				.inf-content{     border:1px solid #DDDDDD;
				    -webkit-border-radius:10px;
				    -moz-border-radius:10px;
				    border-radius:10px;
				    box-shadow: 7px 7px 7px rgba(0, 0, 0, 0.3);
				}
				.painel-eventos{
					margin:5px;
					padding:5px;
				}
				.direita{
				position: absolute;
				right: 0;`
				margin-botton:10px;
				}
			</style>
  	</head>
  	<body>
		<%@include file="/jsp/inc/menu.jsp" %>
    	<div id="wrap">
    		<div class="container">
    			<div class="row">
    			
    			
    			<c:if test="${ not empty mensagem}">
							  	<c:choose>
						  			<c:when test="${mensagem == 'Cadastro de evento realizado!' }" >
						  			<div class="form-group">
									  	<div class="alert alert-success col-md-6 col-md-offset-4 has-feedback" role="alert">
						  					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						  						<span aria-hidden="true">&times;</span>
						  					</button>
									  		<strong>Sucesso!</strong> ${mensagem}
										</div>
									</div>	
									</c:when>
									<c:when test="${mensagem == 'Problemas no servidor tente novamente mais tarde!' }" >
									  	<div class="alert alert-danger col-md-4 col-md-offset-4 has-feedback" role="alert">
						  					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						  						<span aria-hidden="true">&times;</span>
						  					</button>
									  		<strong>Atenção!</strong> ${mensagem}
										</div>
									</c:when>
									<c:when test="${mensagem == 'Evento atualizadocom sucesso!' }" >
									  	<div class="alert alert-success col-md-4 col-md-offset-4 has-feedback" role="alert">
						  					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						  						<span aria-hidden="true">&times;</span>
						  					</button>
									  		<strong>Hey!</strong> ${mensagem}
										</div>
									</c:when>
								</c:choose>
							  </c:if>
    			
    			
    			<div class="col-md-6">
    				<form class="form-horizontal">
						<fieldset>
						
							<legend>Informações do Evento </legend>

							  
							<!--Informacao do evento -->
								<div class="panel panel-primary">
							      <div class="panel-heading">
							        <h3 class="panel-title"><i class="fa fa-windows fa-2x"></i>Nome:${evento.nome}</h3>
							      </div>
							      <div class="panel-body">
							      <img src=""></a>
							        <ul>
									<li><strong>Descrição:</strong> ${evento.descricao}</li>
									<li><strong>Data Início:</strong> <fmt:formatDate pattern = "dd-MM-yyyy" value = "${evento.dataInicio}" /></li>
									<li><strong>Data Fim:</strong> <fmt:formatDate pattern = "dd-MM-yyyy" value = "${evento.dataFim}" /></li>
									<li><strong>Palavra Chave:</strong> ${evento.palavraChave}</li>
									</ul>
							      </div>
							    </div>
					</fieldset>
					</form>
				</div>
				<div class="col-md-6">
					<fieldset>
					<legend>Usuários participantes do evento <a href="<%=request.getContextPath()%>/evento?acao=ranking&id=${evento.id}" class="direita btn btn-info" role="button">Listar</a></legend>
						<table class="table table-bordered">
		  						<thead>
		  							 <tr>
		  							 	<th>#</th>
		  							 	<th>Login</th>
		  							 	<th>Nome</th>
		  							  </tr>
		  						</thead>
		  						<tbody>
		  							<c:forEach  var="ranking" items="${ranking}" varStatus="loop">
			  						 	<tr>
			  						 		<th scope="row">${loop.count}</th>
			  						 		<td>${ranking.login}</td>
			  						 		<td>${ranking.nome}</td>
			  						 	 </tr>
		  						 	 </c:forEach>
								</tbody>
						</table> 
				</div>			 
			</div>
			<div class="row">
				<div class="col-md-6">
					<a href="<%=request.getContextPath()%>/evento?acao=editar&id=${evento.id}" class="btn btn-info" role="button">Editar Evento</a>
				</div>
				<div class="col-md-6">
					<a href="<%=request.getContextPath()%>/evento?acao=avaliar&id=${evento.id}" class="btn btn-danger" role="button">Avaliar Envios</a>
				</div>			
			</div>	
		</div>
	</div>
    	<%@include file="/jsp/inc/rodape.jsp" %>
	</body>
</html>

