<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
	.navbar{
		padding-left:20px;
		padding-right:20px;
	}
</style>

<div class="navbar navbar-default navbar-fixed-top">
  <nav>
  <!-- 
    <ul class="nav nav-pills pull-right">
	    <li class="dropdown">
		<a href="#" id="drop0" role="button" class="dropdown-toggle" data-toggle="dropdown">Cadastrar Envento<b class="caret"></b></a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="drop2">
		   	    <li role="presentation">
		   	    	<a href="<%=request.getContextPath()%>/evento?acao=cadastrar">
		   	    		<span class="text-primary"><i class="glyphicon glyphicon-plus primary"></i> Evento</span>
		   	    	</a>
		   	    </li>
			</ul>
	  	</li>	  	
	  	<li class="dropdown">
		<a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">User<b class="caret"></b></a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="drop2">
				<li role="presentation"><a href="<%=request.getContextPath()%>/login?acao=logout">Logout</a></li>
			</ul>
	  	</li>
	  	
    </ul>
    -->
    <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cadastrar Evento <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<%=request.getContextPath()%>/evento?acao=cadastrar">
		   	    		<span class="text-primary"><i class="glyphicon glyphicon-plus primary"></i> Evento</span>
		   	    	</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<%=request.getContextPath()%>/login?acao=logout">Logout</a></li>
          </ul>
        </li>
      </ul>
    
    
  </nav>
  <h3 class="titulo"><a href="<%=request.getContextPath()%>/">Sistema Aedes</a></h3>
</div>