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
			</style>
  	</head>
  	<body>
		<%@include file="/jsp/inc/menu.jsp" %>
    	<div id="wrap">
    		<div class="container">
    			<div class="col-md-12">
    				<div class="row">
    				
    					<form class="form-horizontal" action="<%=request.getContextPath()%>/mestre" method="POST">
    					<fieldset>
							<!-- Form Name -->
							<legend>Edição de cadastro</legend>
							
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
							<c:when test="${mensagem == 'Senha alterada com sucesso!' }" >
							  	<div class="alert alert-success col-md-4 col-md-offset-4 has-feedback" role="alert">
				  					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				  						<span aria-hidden="true">&times;</span>
				  					</button>
							  		<strong>Atenção!</strong> ${mensagem}
								</div>
							</c:when>
							<c:otherwise>
					           <div class="alert alert-danger col-md-4 col-md-offset-4 has-feedback" role="alert">
				  					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				  						<span aria-hidden="true">&times;</span>
				  					</button>
							  		<strong>Atenção!</strong> ${mensagem}
								</div>
					        </c:otherwise>
						</c:choose>
					  </c:if>
					  </div>
						<div class="row">	
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="senhaAntiga">Senha Antiga:</label>  
							  <div class="col-md-6">
							  	<input id="senhaAntiga" name="senhaAntiga" type="password" class="form-control input-md" value="">
							  </div>
							</div>
							
							<!-- Data input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="senha">Nova Senha</label>  
							  <div class="col-md-6">
							  	<input id="senha" name="senha" type="password" placeholder="" class="form-control input-md" value="">
							  </div>
							</div>
														
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="senhare">Repita a nova Senha</label>  
							  <div class="col-md-6">
							  	<input id="senhare" name="senhare" type="password" class="form-control input-md" value="">
							  </div>
							</div>
							
									
							<!-- Button (Double) -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="Cadastrar"></label>
							  <div class="col-md-8">
							  	<button id="Cadastrar" name="acao" class="btn btn-success" value="novaSenha">Salvar</button>
							  	<a href="<%=request.getContextPath()%>/" class="btn btn-danger" role="button">Cancelar</a>
							  </div>
							</div>
							
						</fieldset>
					</form>


				
    				</div>
    			</div>
    		</div>
    	</div>
    	<%@include file="/jsp/inc/rodape.jsp" %>
	</body>
</html>

