<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
  	<head>
		<%@include file="/jsp/inc/topo.jsp" %>
			<link href="<%=request.getContextPath()%>/css/login/login.css" rel="stylesheet">
			<link href="<%=request.getContextPath()%>/css/dashboard.css" rel="stylesheet">
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
				.gi-2x{font-size: 2em;}
				.gi-3x{font-size: 3em;}
				.gi-4x{font-size: 4em;}
				.gi-5x{font-size: 5em;}
			</style>
  	</head>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
  <script type="text/javascript">
  var id =${id};
  var pizza;
  var barra;
  
  $.ajax({
		method: "GET",
		url: "<%=request.getContextPath()%>/evento?acao=pizza&id="+id,
		data: { acao: "pesquisar", fquery: $("#fquery").val()},
		success: function(data){
			pizza = jQuery.parseJSON(data);
		},
		error:function(){
			alert("nao foi possivel realizar a chamada ajax");
		}
	})
	
	$.ajax({
		method: "GET",
		url: "<%=request.getContextPath()%>/evento?acao=barra&id="+id,
		data: { acao: "pesquisar", fquery: $("#fquery").val()},
		success: function(data){
			
			barra = jQuery.parseJSON(data);
			console.log(barra);
		},
		error:function(){
			alert("nao foi possivel realizar a chamada ajax");
		}
	})

  
  
  google.charts.load('current', {packages: ['corechart', 'bar']});
  google.charts.setOnLoadCallback(drawAxisTickColors);

  function drawAxisTickColors() {
        var data = new google.visualization.DataTable();
        data.addColumn('number', 'Atividades');
        data.addColumn('number', 'Quantidade de Atividades Realizadas');

        
        var output = barra.map(function(item) { 
      
        	return [item.idade,item.quantidade]; 
        	
        });
        
        console.log(output);
        
  //  	var data = google.visualization.arrayToDataTable(output);

        data.addRows(
        	output
);

        var options = {
          focusTarget: 'category',
          hAxis: {
            title: 'Idades [anos]',
            viewWindow: {
              min: [ 7],
              max: [15]
            },
            textStyle: {
              fontSize: 14,
              color: '#053061',
              bold: true,
              italic: false
            },
            titleTextStyle: {
              fontSize: 16,
              color: '#053061',
              bold: true,
              italic: false
            }
          },
          vAxis: {
            title: 'Qtde Completadas',
            textStyle: {
              fontSize: 16,
              color: '#67001f',
              bold: false,
              italic: false
            },
            titleTextStyle: {
              fontSize: 16,
              color: '#67001f',
              bold: true,
              italic: false
            }
          }
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
  
  
  google.charts.load('current', {'packages':['corechart']});
  google.charts.setOnLoadCallback(drawChart);

  function drawChart() {
	
	var output = pizza.map(function(item) { return [item.bairro, item.quantidade]; });

	output.unshift(["Bairro", "Nº de Fotos"]);

	var data = google.visualization.arrayToDataTable(output);

    var options = {
      title: 'Envios de foto por bairro'
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data, options);
  }
  </script>
 </head>
  	<body>
		<%@include file="/jsp/inc/menu.jsp" %>
				
<div class="container-fluid">
  
  <div class="row">
  	<div class="col-md-2"> 
		<button type="button" class="btn btn-default btn-lg">
			<a href="<%=request.getContextPath()%>/evento?acao=visualizar&id=${id}" class="btn btn-primary" role="button">
				<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Voltar
			</a>
		</button>
	</div>
  </div>
  <br/>
  <br/>
  <div class="row">
    <div class="col-sm-4">
    
      <div class="aw-box">
        <div class="aw-box__icon">
          <i class="glyphicon glyphicon-user  gi-3x"></i>
        </div>
        <div class="aw-box__value">${dashboard.participantes}</div>
        <div class="aw-box__title">Usuários participantes</div>
      </div>
    
    </div>

    <div class="col-sm-4">
    
      <div class="aw-box">
        <div class="aw-box__icon">
          <i class="glyphicon glyphicon-globe gi-3x"></i>
        </div>
        <div class="aw-box__value">${dashboard.bairro}</div>
        <div class="aw-box__title">Bairros participantes</div>
      </div>
    
    </div>
    
    <div class="col-sm-4">
    
      <div class="aw-box">
        <div class="aw-box__icon">
          <i class="glyphicon glyphicon-time  gi-3x"></i>
        </div>
        <div class="aw-box__value">${dashboard.tempoTotal}</div>
        <div class="aw-box__title">Horas acumuladas de atividades</div>
      </div>
    
    </div>
  </div>

  <div class="row">
  
     <div class="col-sm-4">
    
	    <div class="aw-box">
	        <div class="aw-box__icon">
	          <i class="glyphicon glyphicon-ban-circle gi-3x"></i>
	        </div>
	        <div class="aw-box__value">${dashboard.banidos}</div>
	        <div class="aw-box__title">Usuários banidos</div>
      	</div>
    
    </div>
         
    <div class="col-sm-4">
    
      <div class="aw-box">
        <div class="aw-box__icon">
          <i class="glyphicon glyphicon-camera  gi-3x"></i>
        </div>
        <div class="aw-box__value">${dashboard.numeroFotos}</div>
        <div class="aw-box__title">Total de Fotos Enviadas</div>
      </div>
    
    </div>
    
    
    <div class="col-sm-4">
    
      <div class="aw-box">
        <div class="aw-box__icon">
          <i class="glyphicon glyphicon-ok-circle  gi-3x"></i>
        </div>
        <div class="aw-box__value"><fmt:formatNumber type = "percent" 
         maxIntegerDigits="3" minFractionDigits = "2" value = "${dashboard.mediaFotos}" /></div>
        <div class="aw-box__title">Porcentagem do evento finalizado</div>
      </div>
    
    </div>
    
   
  </div>

  <div class="row">
  	<div class="col-sm-6">
      <div class="aw-graph-box">
        <div class="aw-graph-box__header">
          <h2 class="aw-graph-box__title">Bairros x nº Fotos <small>fotos avaliadas corretas</small></h2>
        </div>
        <div class="aw-graph-box__content">
          <div class="aw-graph-box__no-data">
              <i class="fa  fa-line-chart  fa-2x"></i> 
          </div>

          <div>
              <div id="piechart" style="width: 450px; height: 250px;"></div>
          </div>
        </div>
      </div>
   	</div>
    <div class="col-md-6">
		<fieldset>
		<legend>Ranking usuários</legend>
			<table class="table table-bordered">
 						<thead>
 							 <tr>
 							 	<th>#</th>
 							 	<th>Login</th>
 							 	<th>Nome</th>
 							 	<th>Bairro</th>
 							 	<th>Cidade</th>
 							 	<th>UF</th>
 							 	<th>Tempo Gasto</th>
 							 	<th>Atividades Completas</th>
 							 	<th>Banido</th>
 							  </tr>
 						</thead>
 						<tbody>
 							<c:forEach  var="ranking" items="${ranking}" varStatus="loop">
  						 	<tr>
  						 		<th scope="row">${loop.count}</th>
  						 		<td class="text-center">${ranking.login}</td>
  						 		<td class="text-center">${ranking.nome}</td>
  						 	 	<td class="text-center">${ranking.bairro}</td>
  						 	 	<td class="text-center">${ranking.cidade}</td>
  						 	 	<td class="text-center">${ranking.estado}</td>
  						 	 	<td class="text-center">${ranking.tempoTotal}</td>
  						 	 	<td class="text-center">${ranking.totalAtividade}</td>
  						 	 	<td class="text-center"> ${ranking.banido eq  1  ? 'Sim' : 'Nao'}</td>
  						 	 </tr>
 						 	 </c:forEach>
					</tbody>
			</table>
		</fieldset>
	</div>	
    </div>
 <div class="row">  
    <div class="col-sm-12">
      <div class="aw-graph-box">
        <div class="aw-graph-box__header">
          <h2 class="aw-graph-box__title"> Atividade Completas <small>idade dos usuários</small></h2>
        </div>
        <div class="aw-graph-box__content" style="position: relative">
          <div class="aw-graph-box__no-data">
              <i class="fa  fa-line-chart  fa-2x"></i>
              <span>Não há dados</span>
          </div>

          <div>
            <div id="chart_div" style="height:400px;"></div>
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

