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
									<div class="col-md-2"> 
										<button type="button" class="btn btn-default btn-lg">
										<a href="<%=request.getContextPath()%>/evento?acao=visualizar&id=${evento.id}" class="btn btn-primary" role="button">
											<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Voltar
										</a>

										</button>
									</div>
									<c:choose>
								    <c:when test="${envio_usuario!=null}">
								        <div class="col-md-10 text-center"> <strong>Últimos Envios do Usuario:</strong><br>
											<div class="row">
												<c:forEach  begin="0" end="6" var="imagens" items="${envios}" >
													<c:if test = "${imagens.caminhoImagem ne envio_usuario.caminhoImagem }">
											         <img alt="Submissões anteriores do Usuário" class="img-thumbnail" style="width:100px; border:1px solid;" title="" src="${imagens.caminhoImagem}">
											      </c:if>
													
											 	</c:forEach>
											</div>
											<div class="row">
												<div class="table-responsive">
												<img alt="Submissão do Usuário" style="width:350px;" title="" class="img-circle img-thumbnail isTooltip" src="${envio_usuario.caminhoImagem}" data-original-title="Usuario">
												
												</div>
											</div>
											<div class="row">
												<table class="table table-hover ">												
													<tr>
														<th>Evento:</th>
														<td>${envio_usuario.evento.nome}</td>
													</tr>
													<tr>
														<th>Usuario:</th>
														<td>${envio_usuario.usuario.login}</td>
													</tr>
													<tr>
														<th>Atividade:</th>
														<td>${envio_usuario.atividade.descricao}</td>
													</tr>
													<tr>
														<th>Data Envio Usuário:</th>
														<td>${envio_usuario.dataFimAtividade}</td>
													</tr>
													<tr>
														<th>Caminho do Arquivo:</th>
														<td>${envio_usuario.caminhoImagem}</td>
													</tr>
												</table>
											</div>
										</div>
										</div>
								<div class="row">
									<div class="col-md-4"> 
										<button type="button" class="btn btn-warning btn-lg" id="banir">
										  <span class="glyphicon glyphicon-ban-circle" aria-hidden="true" ></span> Banir
										</button>
									</div>
									<div class="col-md-3 col-md-offset-1"> 
										<button type="button" class="btn btn-success btn-lg" id="correto">
										  <span class="glyphicon glyphicon-ok" aria-hidden="true" ></span> Correto
										</button>
									</div>
									<div class="col-md-3"> 
										<button type="button" class="btn btn-danger btn-lg" id="errado">
										  <span class="	glyphicon glyphicon-remove" aria-hidden="true"></span> Errado
										</button>
										
									</div>
								</div>
								    </c:when>    
								    <c:otherwise>
								  		<div class="col-md-10 text-center"> 
								  	  		<br><strong>Ainda não há submissoes!</strong><br>
								  	  	</div>
								    </c:otherwise>
								</c:choose>
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
<script>
	
	$("#correto" ).click(function() {
			$.ajax({
				method: "POST",
				url: "<%=request.getContextPath()%>/evento",
				data: { acao: "julgar", tipo: "correto"},
				success: function(data){
					alert("deu bom "+data);
					console.log(data);
				},
				error:function(){
					alert("Erro no servidor, tente novamente mais tarde.");
				}
			})
		});
	
	$("#banir" ).click(function() {
		$.ajax({
			method: "POST",
			url: "<%=request.getContextPath()%>/evento",
			data: { acao: "julgar", tipo: "banir"},
			success: function(data){
				alert("deu bom "+data);
				console.log(data);
			},
			error:function(){
				alert("Erro no servidor, tente novamente mais tarde.");
			}
		})
	});
	
	$("#errado" ).click(function() {
		$.ajax({
			method: "POST",
			url: "<%=request.getContextPath()%>/evento",
			data: { acao: "julgar", tipo: "errado"},
			success: function(data){
				alert("deu bom "+data);
				console.log(data);
			},
			error:function(){
				alert("Erro no servidor, tente novamente mais tarde.");
			}
		})
	});

</script>
