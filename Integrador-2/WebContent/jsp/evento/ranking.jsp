<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
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
  
  $.ajax({
		method: "GET",
		url: "<%=request.getContextPath()%>/evento?acao=pizza&id="+id,
		data: { acao: "pesquisar", fquery: $("#fquery").val()},
		success: function(data){
			alert(data);
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
			alert(data);
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
        data.addColumn('number', 'Atv. 1');
        data.addColumn('number', 'Atv. 2');
        data.addColumn('number', 'Atv. 3');
        data.addColumn('number', 'Atv. 4');
        data.addColumn('number', 'Atv. 5');
        data.addColumn('number', 'Atv. 6');
        

        data.addRows([
          [{v: 8, f: '8 anos'},  1,  2, 0, 1, 1, 1],
          [{v: 9, f: '9 anos'},  1,  2, 1, 1, 1, 1],
          [{v: 10, f: '10 anos'},  2,  2, 2, 1, 1, 1],
          [{v: 11, f: '11 anos'},  1,  1, 0, 1, 1, 1],
          [{v: 12, f: '12 anos'},  1,  2, 0, 1, 1, 1],
          [{v: 13, f: '13 anos'},  1,  2, 1, 1, 1, 1],
          [{v: 14, f: '14 anos'},  2,  2, 0, 1, 1, 1]
        ]);

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

    var data = google.visualization.arrayToDataTable([
      ['Bairro', 'Nº de Fotos'],
      ['Vila C',     5],
      ['Vila A',    0]
    ]);

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
    	<?php
defined('BASEPATH') OR exit('No direct script access allowed');

?>

<div class="container-fluid">
  
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
        <div class="aw-box__title">Horas acumuladas de atividaes</div>
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
        <div class="aw-box__value">${dashboard.mediaFotos}</div>
        <div class="aw-box__title">Média de atividades completas</div>
      </div>
    
    </div>
    
   
  </div>

  <div class="row">
    <div class="col-sm-6 col-sm-offset-4">
      <div class="aw-graph-box">
        <div class="aw-graph-box__header">
          <h2 class="aw-graph-box__title">Bairros x nº Fotos <small>fotos avaliadas corretas</small></h2>
        </div>
        <div class="aw-graph-box__content">
          <div class="aw-graph-box__no-data">
              <i class="fa  fa-line-chart  fa-2x"></i>
              <span>Não há dados</span>
          </div>

          <div>
              <div id="piechart" style="width: 450px; height: 250px;"></div>
          </div>
        </div>
      </div>
    </div>
    
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

