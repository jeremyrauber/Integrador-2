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
  	<style>
  		#pesquisa_cat {
  			display: none;
  			margin-bottom: 20px;
  		}
  		#pesquisa_cat span {
	  		margin-right: 20px;
  		}
  		#ativar_pesquisa_cat {
  			margin-left: 30px;
  		}
  	</style>

    <div class="container">
    
      <%@include file="/jsp/inc/menu.jsp" %>
      
      <div class="row marketing">
      
        <div id="pesquisa_nome" class="row">
	        <input type="text" name="fquery" id="fquery" placeholder="digite o nome do artista" autocomplete="off"/>
	        <button id="search" class="btn btn-default">Pesquisar</button>
	        <button id="ativar_pesquisa_cat" class="btn btn-default">Pesquisar por Categoria</button>
        </div>
        <div id="pesquisa_cat" class="row">
        	<div class="row"><h2>Escolha uma Categoria:</h2> </div>
        	<c:forEach items="${categorias}" var="cat">
			   	<span>
			   		</span><input type="radio" class="cat" name="categoria" value="${cat.id}"/>${cat.nome}
			   	</span>
		    </c:forEach>
        </div>
    
		<div class="row">
		    <!-- filtro -->
		  <div class="col-sm-4">
		  
		    <div id="resultado">
		    	${resposta}
		    </div>
		  </div>
		
		   
		   <!-- detalhe artista -->
		  <div class="col-sm-8">
		  	<div id="detalheArtista"></div>
			<!-- <div id="resultado"></div> -->
		  </div>
		</div>
        
    </div>

      <footer class="footer">
        <p>&copy; 2016 CinemaSeven, Inc.</p>
      </footer>

    </div> <!-- /container -->

	<script>
	$(document).ready(function(){
		$("#ativar_pesquisa_cat").click(function() {
			$("#pesquisa_nome").css("display", "none");
			//$("#btn_pesquisa_cat").css("display", "none");
			$("#pesquisa_cat").css("display", "block");
		});
		
		$("#search").click(function(){
			//$(".cat").prop("checked",false);
			$.ajax({
				method: "GET",
				url: "<%=request.getContextPath()%>/artista",
				data: { acao: "pesquisar", fquery: $("#fquery").val()},
				success: function(data){
					$("#resultado").html(data);
				},
				error:function(){
					alert("nao foi possivel realizar a chamada ajax");
				}
			})
		});
		
		$(".cat").click(function(){ // dispara o ajax
			// get all checked checkboxes
			// into array
			// send to controller
			//var catIds = "";
			//$(".cat:checked").each(function() {
				catId = $(this).val();
			//});
			
			$.ajax({
				method: "GET",
				url: "<%=request.getContextPath()%>/artista",
				data: { acao: "filtra", cat: catId},
				success: function(data){
					$("#resultado").html(data);
				},
				error:function(){
					alert("nao foi possivel realizar a chamada ajax");
				}
			})
		});
//	 	regiao observada ; evento ; tag ; função

		$("#resultado").on("click","a",function(e){ // dispara o ajax
			$.ajax({
				method: "GET",
				url: "<%=request.getContextPath()%>/artista",
				data: { acao: "detalhe", id: $(this).data("id")},
				success: function(data){
					$("#detalheArtista").html(data);
				},
				error:function(){
					alert("nao foi possivel realizar a chamada ajax");
				}
			});
			// por causa do <a>, ele força um carregamento da pagina, e nao queremos isso
			//preventDefault elimina esse comportamento do link
			e.preventDefault();
		});
		
		
	});
	</script>

    </body>
</html>
