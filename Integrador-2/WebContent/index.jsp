<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
   				<div class="container bootstrap snippet">
					<div class="panel-body inf-content bg-success">
						<div class="row">
	   						<span class="label label-default titulo-eventos">Painel de Eventos</span>
	   					</div>
	   					<div class="row">
	   						<c:forEach  begin="0" end="5" var="evento" items="${mestre.eventos}" varStatus="loop">
								<div class="col-md-4 text-center"><a href="<%=request.getContextPath()%>/evento?acao=visualizar&id=${evento.id}" class="btn btn-success" role="button">${fn:substring(evento.nome, 0, 20)}</a></div>
								 <c:if test="${loop.count eq 3}"> 
								 	<br /><br />
								 </c:if>
						 	</c:forEach>				
	    				</div>
    				</div>
    			</div>
    			<div class="row"><br/>
    			${mensagem}
    			</div>
    			<div class="row"><br/></div>	
    			<div class="col-md-12">
    				<div class="row">
    				<!--  Começo do painel do Usuário -->
						<div class="container bootstrap snippet">
							<div class="panel-body inf-content">
								<div class="row">
									<div class="col-md-4"> 
										<img alt="" style="width:300px;" title="" class="img-circle img-thumbnail isTooltip" src="${not empty mestre.caminhoImagem ? mestre.caminhoImagem : 'images/usuarios/padrao.png'}" data-original-title="Usuario">									
										<ul title="Ratings" class="list-inline ratings text-center">
											<li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
											<li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
											<li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li><li>
											<a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
											<li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
										</ul>
									</div>
									<div class="col-md-6"> <strong>Informações:</strong><br>
										<div class="table-responsive">
											<table class="table table-condensed table-responsive table-user-information">
												<tbody>
													<tr>
														<td><strong><span class="glyphicon glyphicon-asterisk text-primary"></span>Identificação</strong></td>
														<td class="text-primary"> ${mestre.id}</td>
													</tr>
														<tr><td><strong><span class="glyphicon glyphicon-user  text-primary"></span> Nome </strong></td>
														<td class="text-primary"> ${mestre.nome}</td></tr>
													<tr>
														<td> <strong> <span class="glyphicon glyphicon-cloud text-primary"></span> Usuário </strong></td>
														<td class="text-primary"> ${mestre.login}</td>
													</tr>
													<tr>
														<td> <strong> <span class="glyphicon glyphicon-envelope text-primary"></span> Email </strong></td>
														<td class="text-primary"> ${mestre.email}</td>
													</tr>
													<tr>
														<td> <strong> <span class="glyphicon glyphicon-bookmark text-primary"></span> Ativo </strong></td>
														<td class="text-primary"> ${mestre.ativo ? "Ativo" : "Inativo"}</td>
													</tr>
													<tr>
														<td> <strong> <span class="glyphicon glyphicon-calendar text-primary"></span> Data de Nascimento </strong></td>
														<td class="text-primary"> <fmt:formatDate pattern = "dd-MM-yyyy" value = "${mestre.dataNascimento}" /></td>
													</tr>
													<tr>
														<td> <strong> <span class="glyphicon glyphicon-calendar text-primary"></span> Data de Cadastro </strong></td>
														<td class="text-primary"> <fmt:formatDate pattern = "dd-MM-yyyy" value = "${mestre.dataCadastro}" />  </td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4 col-md-offset-6">
										<div class="col-md-2 text-center"><a href="<%=request.getContextPath()%>/mestre?acao=editar" class="btn btn-primary" role="button">Editar Informações</a></div>
									</div>
								</div>
							</div>
						</div>
    				</div>
    			</div>
    		</div>
    	</div>
    	<%@include file="/jsp/inc/rodape.jsp" %>
	</body>
</html>

