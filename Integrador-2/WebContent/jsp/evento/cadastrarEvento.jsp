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
    			<div class="row">
					<form class="form-horizontal" action="">
    					<fieldset>
							<!-- Form Name -->
							<legend>Cadastro de evento</legend>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="fullname">Nome do evento</label>  
							  <div class="col-md-6">
							  	<input id="fullname" name="fullname" type="text" placeholder="Nome que será visualizado pelos participantes" class="form-control input-md" maxlength="30" minlength="5" value="${not empty evento.nome ? evento.nome : ''}">
							  </div>
							</div>
							
							<!-- Data input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="datai">Data de Início</label>  
							  <div class="col-md-6">
							  <input id="datai" name="datai" type="date" placeholder="" class="form-control input-md" value="${not empty evento.dataInicio ? evento.dataInicio : ''}">
							  </div>
							</div>
							
							<!-- Data input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="dataf">Data de Término</label>  
							  <div class="col-md-6">
							  <input id="dataf" name="dataf" type="date" placeholder="" class="form-control input-md">
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="descricao">Descrição</label>
							  <div class="col-md-6">
							    <textarea class="form-control" rows="5" id="descricao" name="descricao">${not empty evento.descricao ? evento.descricao : ''}</textarea>
							    <span class="help-block">(descrição suscinta)</span>
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="keyword">Palavra - Chave</label>
							  <div class="col-md-6">
							    <input id="keyword" name="keyword" type="text" placeholder="palavra-chave" class="form-control input-md" required="" value="${not empty evento.palavraChave ? evento.palavraChave : ''}">
							    <span class="help-block">(Chave de acesso ao evento)</span>
							  </div>
							</div>
							
							<!-- Button (Double) -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="Cadastrar"></label>
							  <div class="col-md-8">
							  	<button id="Cadastrar" name="acao" class="btn btn-success" value="adicionar">Cadastrar</button>
							  	<a href="<%=request.getContextPath()%>/" class="btn btn-danger" role="button">Cancelar</a>
							  </div>
							</div>
							
						</fieldset>
					</form>
				</div>
			</div>
		</div>
    	<%@include file="/jsp/inc/rodape.jsp" %>
	</body>
</html>

