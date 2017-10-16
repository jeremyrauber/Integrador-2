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
	  		<c:when test="${mensagem == 'Você foi deslogado.' }" >
			  	<div class="alert alert-info col-md-4 col-md-offset-4 has-feedback" role="alert">
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
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" method="post" action="<%=request.getContextPath()%>/register"  role="form" style="display: block;">
									<input type="hidden" name="acao" value="recuperar"/>
									
									<div class="input-group">
										<p>Insira seu email para recuperar sua conta.</p>
									</div>
									<div class="input-group">
									    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
									    <input id="email" type="text" class="form-control" name="email" tabindex="1" placeholder="email@email.com">
									</div>
																		
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Enviar">
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-3">
											<span class="input-group"><a href="<%=request.getContextPath()%>/login"><i class="glyphicon glyphicon-arrow-left">  </i> Voltar</a></span>
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