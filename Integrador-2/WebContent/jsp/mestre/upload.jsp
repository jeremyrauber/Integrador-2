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
				  		<div class="form-group">
						  	<div class="alert alert-info col-md-6 col-md-offset-4 has-feedback" role="alert">
			  					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			  						<span aria-hidden="true">&times;</span>
			  					</button>
						  		<strong>Informativo!</strong> ${mensagem}
							</div>
						</div>						
					  </c:if>
    			

    			
    			<div class="col-md-12">
					<form action="<%=request.getContextPath()%>/upload" method="post" enctype="multipart/form-data">
			        	<div class="form-group">
				        	<label for="file">Escolha uma imagem do arquivo</label>
				        	<br>
				        	<input type="file" class="form-control" name="file" id="file"/> 
				        	<br>
				        	<button type="submit" class="btn btn-primary" >Enviar</button>
				        </div>
			    	</form>
				</div>
				</div>
			</div>
		</div>
    	<%@include file="/jsp/inc/rodape.jsp" %>
	</body>
</html>
</script>

