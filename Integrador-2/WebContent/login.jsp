<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
	<%@include file="/jsp/inc/topo.jsp" %>
	<link href="<%=request.getContextPath()%>/css/login/login.css" rel="stylesheet">
  </head>
  <body>
	  <div id="wrap">
	  <c:if test="${ not empty mensagem}">
	  	<c:choose>
	  		<c:when test="${mensagem == 'Voc� foi deslogado.' }" >
			  	<div class="alert alert-info col-md-4 col-md-offset-4 has-feedback" role="alert">
  					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
  						<span aria-hidden="true">&times;</span>
  					</button>
			  		<strong>Informativo!</strong> ${mensagem}
				</div>
			</c:when>
			<c:when test="${mensagem == 'Email enviado com sucesso!' }" >
			  	<div class="alert alert-info col-md-4 col-md-offset-4 has-feedback" role="alert">
  					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
  						<span aria-hidden="true">&times;</span>
  					</button>
			  		<strong>Informativo!</strong> A confirma��o de cadastro ser� enviada para seu emai: ${email}. S� ser� permitida a entrada ap�s valida��o.
				</div>
			</c:when>
			<c:when test="${mensagem == 'Cadastro realizado com sucesso!' }" >
			  	<div class="alert alert-success col-md-4 col-md-offset-4 has-feedback" role="alert">
  					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
  						<span aria-hidden="true">&times;</span>
  					</button>
			  		<strong>Informativo!</strong> ${mensagem}
				</div>
			</c:when>
			<c:when test="${mensagem == 'Email para recuperar senha foi enviado com sucesso!' }" >
			  	<div class="alert alert-success col-md-4 col-md-offset-4 has-feedback" role="alert">
  					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
  						<span aria-hidden="true">&times;</span>
  					</button>
			  		<strong>Informativo!</strong> ${mensagem}
				</div>
			</c:when>
			
			<c:otherwise>
	    	<div class="alert alert-danger col-md-4 col-md-offset-4"role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			  	<strong>Aten��o!</strong> ${mensagem}
			</div>
	 		</c:otherwise>
		</c:choose>
	  </c:if>
  
  <div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Registrar</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" method="post" action="<%=request.getContextPath()%>/login"  role="form" style="display: block;">
									<input type="hidden" name="acao" value="login"/>
									
									<div class="input-group">
									    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
									    <input id="login" type="text" class="form-control" name="login" tabindex="1" placeholder="Login" value="${not empty login ? login : ''}">
									</div>
									 
									 
									
									<div class="input-group">
									    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
									    <input id="senha" type="password" class="form-control" name="senha" tabindex="2" placeholder="Senha" value="">
									</div>
																		
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Logar">
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="<%=request.getContextPath()%>/novasenha.jsp" tabindex="5" class="forgot-password">Esqueceu a senha?</a>
												</div>
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" method="post" action="<%=request.getContextPath()%>/register" role="form" style="display: none;" onsubmit="return validateForm()">
									<input type="hidden" name="acao" value="cadastrar"/>
									
									<div class="form-group">
										<label id="val_nome" class="control-label" for="nome">Nome Completo</label>
										<input type="text" name="nome" id="nome" tabindex="1" class="form-control" placeholder="Nome" value="${not empty nome ? nome : ''}" autofocus>
									</div>
									<div class="form-group">
										<label id="val_data_nasc" class="control-label" for="data_nasc">Data de Nascimento</label>
										<input type="date" name="data_nasc" id="data_nasc" tabindex="1" class="form-control" placeholder="Data Nascimento" value="${not empty data_nasc ? data_nasc: ''}">
									</div>
									<div class="form-group">
										<label id="val_login" class="control-label" for="login">Login</label>
										<input type="text" name="login" id="login" tabindex="1" class="form-control" placeholder="Login" value="${not empty logincad ? logincad : ''}">
									</div>
									<div class="form-group">
									 	<label id="val_senha" class="control-label" for="text_senha">Senha</label>
										<input type="password" name="text_senha" id="text_senha" tabindex="2" class="form-control" placeholder="Senha" value="${not empty senhacad ? senhacad : ''}">
									</div>
									<div class="form-group">
										<label id="val_senha" class="control-label" for="text_senha">Repita sua senha</label>
										<input type="password" name="repetir_senha" id="repetir_senha" tabindex="2" class="form-control" placeholder="Confirme a Senha" value="${not empty senhacad ? senhacad : ''}">
									</div>
									<div class="form-group">
										<label id="val_endereco" class="control-label" for="endereco">Endere�o</label>
										<input type="text" name="endereco" id="endereco" tabindex="1" class="form-control" placeholder="Endere�o" value="${not empty endereco ? endereco : ''}">
									</div>
									<div class="form-group">
										<label id="val_email" class="control-label" for="email">Email</label>
										<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email" value="${not empty email ? email : ''}">
									</div>
									<div class="form-group">
										<label id="val_cep" class="control-label" for="cep">CEP</label>
										<input type="text" name="cep" id="cep" tabindex="2" class="form-control" placeholder="CEP" value="${not empty cep ? cep : ''}" >
									</div>
									<div class="form-group">
										<label id="val_bairro" class="control-label" for="bairro">Bairro</label>
										<select id="bairro" name="bairro" class="form-control" >
  										<c:forEach  var="bairro" items="${bairros}" >		
										    	<option value="${bairro.nome}">${bairro.nome}</option>
									 	</c:forEach>
									 	</select>
									</div>
									<div class="form-group">
										<label id="val_cidade" class="control-label" for="cidade">Cidade</label>
										<input type="text" name="cidade" id="cidade" tabindex="2" class="form-control" placeholder="Cidade" value="Foz do Iguacu" disabled>
									</div>
									<div class="form-group">
										<label id="val_estado" class="control-label" for="estado">Estado</label>
										<input type="text" name="estado" id="estado" tabindex="2" class="form-control" placeholder="Estado" value="PR" disabled>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Cadastrar">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/jsp/inc/rodape.jsp" %>
	<script src="<%=request.getContextPath()%>/js/login/login.js"></script>	
	</div>
  </body>
</html>