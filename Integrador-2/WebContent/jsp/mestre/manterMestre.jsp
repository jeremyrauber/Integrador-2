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
						</c:choose>
					  </c:if>
							
							
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="nome">Nome Completo</label>  
							  <div class="col-md-6">
							  	<input id="nome" name="nome" type="text" class="form-control input-md" value="${mestre.nome}">
							  </div>
							</div>
							
							<!-- Data input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="datanasc">Data de Nascimento</label>  
							  <div class="col-md-6">
							  	<input id="datanasc" name="datanasc" type="date" placeholder="" class="form-control input-md" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${mestre.dataNascimento}" />">
							  </div>
							</div>
														
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="endereco">Endereço</label>  
							  <div class="col-md-6">
							  	<input id="endereco" name="endereco" type="text" class="form-control input-md" value="${mestre.endereco}">
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cep">CEP</label>
							  <div class="col-md-6">
							    <input id="cep" name="cep" type="text" class="form-control input-md" required="" value="${mestre.cep}" pattern= "\d{5}-?\d{3}">
							  </div>
							</div>
							
								<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="bairro">Bairro</label>
							  <div class="col-md-6">
							    <input id="bairro" name="bairro" type="text" class="form-control input-md" required="" value="${mestre.bairro}">
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="cidade">Cidade</label>
							  <div class="col-md-6">
							    <input id="cidade" name="cidade" type="text" class="form-control input-md" required="" value="${mestre.cidade}">
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="estado">Estado</label>
							  <div class="col-md-6">
							    <input id="estado" name="estado" type="text" class="form-control input-md" required="" value="${mestre.estado}">
							  </div>
							</div>
							
							<!-- Button (Double) -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="Cadastrar"></label>
							  <div class="col-md-8">
							  	<button id="Cadastrar" name="acao" class="btn btn-success" value="salvar">Salvar</button>
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

