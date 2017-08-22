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
	        <input type="text" name="fquery" id="fquery" placeholder="digite o nome do usuário" autocomplete="off"/>
	        <button id="search" class="btn btn-default">Pesquisar</button>
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
		  	<div id="detalheUsuario"></div>
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
		});
		
		$("#search").click(function(){
			//$(".cat").prop("checked",false);
			$.ajax({
				method: "GET",
				url: "<%=request.getContextPath()%>/usuario",
				data: { acao: "pesquisar", fquery: $("#fquery").val()},
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
				url: "<%=request.getContextPath()%>/usuario",
				data: { acao: "detalhe", id: $(this).data("id")},
				success: function(data){
					$("#detalheUsuario").html(data);
				},
				error:function(){
					alert("nao foi possivel realizar a chamada ajax");
				}
			});
			e.preventDefault();
		});
		
		
	});
	</script>

    </body>
</html>
