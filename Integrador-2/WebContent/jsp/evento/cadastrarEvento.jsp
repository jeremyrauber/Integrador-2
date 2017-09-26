<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
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
    			
    			
    			
    			
    			
    			
					<form action="<%=request.getContextPath()%>/evento" method="POST">
    					<fieldset>
							<!-- Form Name -->
							<legend>${not empty evento? 'Atualizar ' : 'Cadastro de '} evento</legend>
							
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
							  <jsp:useBean id="now" class="java.util.Date" /><fmt:formatDate var="atual" value="${now}" pattern="yyyy-MM-dd" />
							  <fmt:formatDate type="date" var="dataInicio" value="${evento.dataInicio}" pattern="yyyy-MM-dd"/>
							  <input id="datai" name="datai" type="date" placeholder="" class="form-control input-md" value="${not empty evento.dataInicio ? dataInicio : atual}">
							  </div>
							</div>
							
							<!-- Data input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="dataf">Data de Término</label>  
							  <div class="col-md-6">
								<c:set var="tomorrow" value="<%=new Date(new Date().getTime() + 2*60*60*24*1000)%>"/>
								<fmt:formatDate type="date" var="totomorrow" value="${tomorrow}" pattern="yyyy-MM-dd"/>
								<fmt:formatDate type="date" var="datafim" value="${evento.dataFim}" pattern="yyyy-MM-dd"/>
							  	<input id="dataf" name="dataf" type="date" placeholder="" class="form-control input-md" value='${not empty evento.dataFim ? datafim : totomorrow}'>
							  </div>
							</div>
						

							
							
							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="descricao">Descrição</label>
							  <div class="col-md-6">
							    <textarea class="form-control" rows="5" id="descricao" name="descricao" maxlength="255">${not empty evento.descricao ? evento.descricao : ''}</textarea>
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
							
							<div class="form-group">
							  <label class="col-md-4 control-label" for="keyword">Atividades para serem desenvolvidas</label>
							  <div class="col-md-6">
							    <c:forEach items="${atividades}" var="atividade">
							    	<div class="checkbox">
						    			<label>
						    				<input type="checkbox" name="atividades[]" value="${atividade.id}" ${atividade.selecionado ? 'checked' : '' } > 
							    			${atividade.descricao} - Nível: ${atividade.nivel }
							    		</label>
						    		</div>
						   		 </c:forEach>
							  </div>
							</div>
							
							<!-- Button (Double) -->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="Cadastrar"></label>
							  <div class="col-md-8">
							    <input type="hidden" name="acao" value="${not empty evento ? 'atualizar' : 'adicionar'}"/>
							     <input type="hidden" name="id" value="${evento.id}"/>
							  	<button type="submit" name="acao" class="btn btn-success">${not empty evento ? 'Atualizar' : 'Inserir'}</button>
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

