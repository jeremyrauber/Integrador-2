<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
	<%@include file="/jsp/inc/topo.jsp" %>
	<link href="<%=request.getContextPath()%>/css/login/login.css" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/login/login.js"></script>
  </head>
  <body>
	  
	  <c:if test="${ not empty mensagem}">
	  	<c:choose>
	  		<c:when test="${mensagem == 'Você foi deslogado.' }" >
			  	<div class="alert alert-info col-md-4 col-md-offset-4">
			  		<strong>Informativo!</strong> ${mensagem}
				</div>
			</c:when>
			<c:otherwise>
	    	<div class="alert alert-danger col-md-4 col-md-offset-4">
			  <strong>Atenção!</strong> ${mensagem}
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
								<form id="login-form" action="" action="<%=request.getContextPath()%>/login"  role="form" style="display: block;">
									<input type="hidden" name="acao" value="login"/>
									<div class="form-group">
										<input type="text" name="login" id="login" tabindex="1" class="form-control" placeholder="Login" value="">
									</div>
									<div class="form-group">
										<input type="password" name="senha" id="senha" tabindex="2" class="form-control" placeholder="Senha">
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
													<a href="" tabindex="5" class="forgot-password">Esqueceu a senha?</a>
												</div>
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" action="" method="post" role="form" style="display: none;">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="">
									</div>
									<div class="form-group">
										<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirm Password">
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
  </body>
</html>