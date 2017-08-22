<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
  <head>
	<%@include file="/jsp/inc/topo.jsp" %>
	
<script type="text/javascript" >
 $(document).ready(function(){
	 
 var i=0;
//AJAX PARA CRIAR DATATABLE
	 $.ajax({
			type:'GET',
			url:'pdv?acao=buscaTodos',
			headers : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; chatset=utf-8"
				},
				success:function(result){
					$("#dataTable > tbody").empty();
					var filmes = $.parseJSON(result);
					var s='';
					 for (var i=0; i < filmes.length ; i++){
					    s = s+ "<tr><td>"+filmes[i].id+"</td><td>"+filmes[i].titulo+"</td><td>"+
					    filmes[i].descricao+"</td><td>"+filmes[i].nota+"</td><td>"+
					    filmes[i].preco+"</td><td>"+filmes[i].likes+"</td><td>"+filmes[i].dislikes+
					    "</td><td><button class='btn btn-primary comprar' type='button' id='btn"+filmes[i].id+"'>"+
					    "Comprar</button></td></tr>";
					 }
					 
					$("#dataTable > tbody").append(s);
					
					$('#dataTable').dataTable( {
				        "lengthMenu": [5, 10, 50, 100],
				        "pageLength": 5
				    } );
				    
			},error : function(){
				alert("deu erro");
				
			}
		}); 

	   // FUNCAO PARA CRIAR RELACIONAMENTOS DE VENDA
	   $(document).on('click','.comprar',function(){ 	 
		   $.ajax({			 
				type:'GET',
				url:'pdv?acao=efetuarcompra',
				data: {
					"idFilme" : $(this).closest('tr').find('td:nth-child(1)').html(), 	
				},
				headers : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; chatset=utf-8"
					},
					success:function(result){
						 alert(result);            		   				 
					},error : function(){
						alert("deu erro");
					}
			}); 

	   });	
 });
</script>
  </head>

  <body>

    <div class="container">
    
      <%@include file="/jsp/inc/menu.jsp" %>

      <div class="row marketing">
    
		<div class="row">
		    <!-- filtro -->
		  <div class="col-md-12">
		 
		  	<h3>
		  		<p class="glyphicon glyphicon-usd">
		  		Lista de Compras
		  		<p>
		  		
		  	</h3>
			
			<form action="<%=request.getContextPath()%>/pdv" method="POST">
			  
			  <div class="col-md-12">
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover" id="dataTable">
                                <thead>
                                    <tr>
                                        <th>Código</th>
                                        <th>Título</th>
                                        <th>Descrição</th>
                                        <th>Nota</th>
                                        <th>Preço[R$]</th>
                                        <th>Likes</th>
                                        <th>Dislikes</th>
                                        <th>Comprar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.panel-body -->
                    </div>
		   
		   <!-- detalhe filme -->
		  <div class="col-md-8">
		  	<div id="detalheFilme"></div>
		  </div>
		  </form>
		</div>
        </div>
    </div>

      <footer class="footer">
        <p>&copy; 2016 CinemaSeven, Inc.</p>
      </footer>

    </div> <!-- /container -->

    </body>
<script src="<%=request.getContextPath()%>/js/dataTable/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/dataTable/dataTables.select.min.js"></script>
</html>