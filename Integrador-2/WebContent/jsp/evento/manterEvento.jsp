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
    			<div class="col-md-6">
    				<form class="form-horizontal">
						<fieldset>
						
							<legend>Evento: ${evento.nome}</legend>
					
							<c:if test="${ not empty mensagem}">
							  	<c:choose>
						  			<c:when test="${mensagem == 'Alterações salvas com sucesso!' }" >
						  			<div class="form-group">
									  	<div class="alert alert-info col-md-6 col-md-offset-4 has-feedback" role="alert">
						  					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						  						<span aria-hidden="true">&times;</span>
						  					</button>
									  		<strong>Informativo!</strong> ${mensagem}
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
								</c:choose>
							  </c:if>
						
							<!-- Appended Input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="matricula">Matricula</label>
							  <div class="col-md-6">
							    <div class="input-group">
							      <input id="matricula" name="matricula" class="form-control" placeholder="matricula" type="text" required="">
							      <span class="input-group-addon">Pesquisar</span>
							    </div>
							    <p class="help-block">(digite a sua matricula da SEMEC)</p>
							  </div>
							</div>
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fullname">Nome</label>  
							  <div class="col-md-6">
							  <input id="fullname" name="fullname" type="text" placeholder="seu nome" class="form-control input-md">
							    
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cpf">CPF</label>  
							  <div class="col-md-6">
							  <input id="cpf" name="cpf" type="text" placeholder="seu CPF" class="form-control input-md">
							    
							  </div>
							</div>
							
							<!-- Password input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="password">Senha</label>
							  <div class="col-md-6">
							    <input id="password" name="password" type="password" placeholder="senha" class="form-control input-md" required="">
							    <span class="help-block">(crie uma senha)</span>
							  </div>
							</div>
							
							<!-- Password input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="confirmpassword">Confirmar senha</label>
							  <div class="col-md-6">
							    <input id="confirmpassword" name="confirmpassword" type="password" placeholder="senha" class="form-control input-md" required="">
							    <span class="help-block">(Confirme sua senha)</span>
							  </div>
							</div>
							
							<!-- Multiple Radios -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="rad">Tipo de usuário</label>
							  <div class="col-md-4">
							  <div class="radio">
							    <label for="rad-0">
							      <input type="radio" name="rad" id="rad-0" value="1" checked="checked">
							      Operador
							    </label>
							    </div>
							  <div class="radio">
							    <label for="rad-1">
							      <input type="radio" name="rad" id="rad-1" value="2">
							      Administrador
							    </label>
								</div>
							  </div>
							</div>
							
							<!-- Button (Double) -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="Cadastrar"></label>
							  <div class="col-md-8">
							    <button id="Cadastrar" name="Cadastrar" class="btn btn-success">Cadastrar</button>
							    <button id="Cancelar" name="Cancelar" class="btn btn-danger">Cancelar</button>
							  </div>
							</div>
							
				</fieldset>
				</form> 
				</div>
				<div class="col-md-6">
					<fieldset>
					<legend>Ranking usuários <a href="<%=request.getContextPath()%>/" class="direita btn btn-info" role="button">Listar</a></legend>
					<table class="table table-bordered">
  						<thead>
  							 <tr>
  							 	<th>#</th>
  							 	<th>First Name</th>
  							 	<th>Last Name</th>
  							 	<th>Username</th>
  							  </tr>
  						</thead>
  						<tbody>
  						 	<tr>
  						 		<th scope="row">1</th>
  						 		<td>Mark</td>
  						 	 	<td>Otto</td>
  						 	 	<td>@mdo</td>
  						 	 </tr>
  						 	 <tr>
  						 	 	<th scope="row">2</th>
  						 	 	<td>Jacob</td>
  						 	 	<td>Thornton</td> 
  						 	 	<td>@fat</td>
  						 	 </tr>
  						 	 <tr>
  						 	 	<th scope="row">3</th>
  						 	 	<td colspan="2">Larry the Bird</td>
  						 	 	<td>@twitter</td>
  						 	 </tr>
						</tbody>
					</table> 
				</div>				 
			</div>
		</div>
	</div>
    	<%@include file="/jsp/inc/rodape.jsp" %>
	</body>
</html>

