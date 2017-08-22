<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
  <head>
	<%@include file="/jsp/inc/topo.jsp" %>
	
	<!-- JQUERY -->
    <script type="text/javascript" src="bootstrap/datatable/js/jquery.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    
    
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		var res = 0;
	$('#tabelaResultado').dataTable();
			
	
	$('.like').click(function(){  //dispara o ajax
		$.ajax({
			method:"GET",
			url:"<%=request.getContextPath()%>/pdv?acao=curtidas&tipo=like",
			data: {id : $(this).closest('tr').find('td:first').text()},
			context: this,
			success: function(data){
				if(data!=''){
					$(this).closest('tr').find('.like').html(data+' <span class="glyphicon glyphicon-thumbs-up"></span>');
				}
			},error : function(){
				alert("deu erro");
			}	
		});
	});	
	
	
	$('.unlike').click(function(){  //dispara o ajax
		$.ajax({
			method:"GET",
			url:"<%=request.getContextPath()%>/pdv?acao=curtidas&tipo=unlike",
			data: {id : $(this).closest('tr').find('td:first').text()},
			context: this,
			success: function(data){
				if(data!=''){
					$(this).closest('tr').find('.unlike').html(data+' <span class="glyphicon glyphicon-thumbs-down"></span>');
				}
			},error : function(){
				alert("deu erro");
			}
		})
	});	
	
	} );
</script>    
    
</head>

<body>

    <div class="container">
    
      <%@include file="/jsp/inc/menu.jsp" %>

      <div class="row marketing">
    
		<div class="row">
		    <!-- filtro -->
		  <div class="col-md-12">
		  <h3>Biblioteca do Usuário</h3>
			
			<form action="<%=request.getContextPath()%>/pdv" method="POST">
			<div class="col-md-12">
                        <div class="panel-body">
			         <p class="help-block">Clique nas colunas para ordernar ou digite para buscar o que desejar</p>
							<table class="table table-striped table-bordered" id="tabelaResultado">
								<thead>
									<tr>
										<th> [Id] </th>
										<th> [Titulo] </th>
										<th> [Descrição] </th>
										<th> [Ano] </th>
										<th> [Link] </th>
										<th> [Likes] </th>
										<th> [Dislikes] </th>
										<th> [Estudio] </th>
										<th> [Nota] </th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${filmes}" var="obj">
									<tr>
										<td>${obj.id}</td>
										<td><a href="filme?acao=editar&id=${obj.id}">${obj.titulo}</a></td>
										<td>${obj.descricao}</td>
										<td>${obj.ano}</td>
										<td>
											<a href="${obj.url}">${obj.url} </a>
											<br>
											<iframe width="420" height="315" src="https://www.youtube.com/embed/${obj.sublink}" frameborder="0" allowfullscreen>
											</iframe>								
										</td>
										<td>
											<button type="button" class="btn btn-outline btn-primary like" id="like">	
												${obj.likes} <span class="glyphicon glyphicon-thumbs-up"></span>
											</button> 
											<br>
										</td>
										<td>
											<button type="button" class="btn btn-outline btn-danger unlike" id="dislike">
												${obj.dislikes} <span class="glyphicon glyphicon-thumbs-down"></span>
											</button>
											
										</td>
										<td>
											<div>
												<a class="btn btn-info" href="#">
	                                				${obj.estudio.nome}
	                            				</a>
												<br>
											</div>
										</td>
										<td>
										<a class="btn btn-success" href="#">
	                                				${obj.nota}
	                            				</a>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							
						</table>	
			        </div>
			    </div>
	        <!-- /.row -->
	       </div>
	       </div>
	       <!-- /.container-fluid -->
	   </div>
	   <!-- /#page-wrapper -->
	 </div>
    <!-- /#wrapper -->
            
    </body>
<script src="<%=request.getContextPath()%>/js/dataTable/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/dataTable/dataTables.select.min.js"></script>
</html>