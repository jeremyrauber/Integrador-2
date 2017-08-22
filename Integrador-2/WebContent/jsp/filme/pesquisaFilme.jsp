<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
	<%@include file="/jsp/inc/topo.jsp" %>
  </head>

  <body>

    <div class="container">
    
      <%@include file="/jsp/inc/menu.jsp" %>
      
      <div class="row marketing">
      
        <div class="row">
	        <input type="text" name="fquery" id="fquery" placeholder="digite o nome do filme" autocomplete="off"/>
	        <button id="search" class="btn btn-primary">Pesquisar</button>
        </div>
    
		<div class="row">
		    <!-- filtro -->
		  <div class="col-sm-4">
		  
		    <h4>Escolha uma categoria:</h4>
		    
		    <c:forEach items="${categorias}" var="cat">
		    	<input type="checkbox" class="cat" name="categoria" value="${cat.id}"/>${cat.nome}<br>
		    </c:forEach>
		 	<h4>Escolha um Estudio:</h4>
		    
		    <c:forEach items="${estudios}" var="est">
		    	<input type="checkbox" class="est" name="estudio" value="${est.id}"/>${est.nome}<br>
		    </c:forEach>
		    
		    <h4>10 Filmes mais curtidos:</h4>
		    <input type="checkbox" class="like" name=""like"/> 
		    <select id="selectBox">
				  <option value="1">1</option>
				  <option value="5">5</option>
				  <option value="10">10</option>
				  <option value="50">50</option>
				</select> <br>
		   </div>

		   
		   <!-- detalhe filme -->
		  <div class="col-sm-8">
			<div id="detalheFilme"></div>
			<div id="resultado"></div>
		  </div>
		</div>
        
    </div>

      <footer class="footer">
        <p>&copy; 2016 CinemaSeven, Inc.</p>
      </footer>

    </div> <!-- /container -->

	<script> 
	
			$(document).ready(function(){
			
				$("#search").click(function(){
					$(".cat").prop("checked",false);
					$.ajax({
						method: "GET",
						url: "<%=request.getContextPath()%>/filme",
						data: { acao:"pesquisar", fquery:$("#fquery").val()},
						success: function(data){	
							$("#resultado").html(data);
						},
						error: function(){
							alert("nao foi possivel realizar a chamada ajax!");
						}
					});
				});
				$(".cat").click(function(){
					var catIds = "";
					$(".cat:checked").each(function(){				
						catIds = catIds + $(this).val()+",";
					});
					
					$.ajax({
						method: "GET",
						url: "<%=request.getContextPath()%>/filme",
						data: { acao:"filtra", cat: catIds},
						success: function(data){	
							$("#resultado").html(data);
						},
						error: function(){
							alert("nao foi possivel realizar a chamada ajax!");
						}
					});
				});
				$(".est").click(function(){
					var estIds = "";
					$(".est:checked").each(function(){				
						estIds = estIds + $(this).val()+",";
					});
					
					$.ajax({
						method: "GET",
						url: "<%=request.getContextPath()%>/filme",
						data: { acao:"filtra2", est: estIds},
						success: function(data){	
							$("#resultado").html(data);
						},
						error: function(){
							alert("nao foi possivel realizar a chamada ajax!");
						}
					});
				});
				$(".like").click(function(){
					var estIds = "";
					$.ajax({
						method: "GET",
						url: "<%=request.getContextPath()%>/filme",
						data: { acao:"filtralike",parametro:$( "#selectBox option:selected" ).text()},
						success: function(data){	
							$("#resultado").html(data);
						},
						error: function(){
							alert("nao foi possivel realizar a chamada ajax!");
						}
					});
				});
				
				//regiaro observada; evento; tag; funcao;
				
				$("#resultado").on("click","a",function(e){ 
					$.ajax({
						method: "GET",
						url: "<%=request.getContextPath()%>/filme",
						data: { acao:"detalhe", id:$(this).data("id")},
						success: function(data){	
							$("#detalheFilme").html(data);
						},
						error: function(){
							alert("nao foi possivel realizar a chamada ajaxa!");
						}
					});
					e.preventDefault(); //usando por causa da Tag <a> (essa tag forca um carregamento da pagina)
				});
				
				
			});
		</script>

    </body>
</html>
