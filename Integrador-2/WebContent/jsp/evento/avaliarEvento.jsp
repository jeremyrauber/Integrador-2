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
					<div class="col-md-12">
    				<!--  Começo do painel do Usuário -->
						<div class="container bootstrap snippet">
							<div class="panel-body inf-content">
								<div class="row">
									<div class="col-md-4"> 
										<button type="button" class="btn btn-default btn-lg">
										<a href="<%=request.getContextPath()%>/evento?acao=visualizar&id=${evento.id}" class="btn btn-primary" role="button">
											<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Voltar
										</a>

										</button>
									</div>
									<div class="col-md-6"> <strong>Envio do Usuario:</strong><br>
										<div class="table-responsive">
											<img alt="" style="width:600px;" title="" class="img-circle img-thumbnail isTooltip" src="https://bootdey.com/img/Content/user-453533-fdadfd.png" data-original-title="Usuario">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4"> 
										<button type="button" class="btn btn-warning btn-lg">
										  <span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span> Banir
										</button>
									</div>
									<div class="col-md-3 col-md-offset-1"> 
										<button type="button" class="btn btn-success btn-lg">
										  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Correto
										</button>
									</div>
									<div class="col-md-3"> 
										<button type="button" class="btn btn-danger btn-lg">
										  <span class="	glyphicon glyphicon-remove" aria-hidden="true"></span> Errado
										</button>
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

