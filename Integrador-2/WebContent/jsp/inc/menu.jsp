<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="header clearfix">
  <nav>



    <ul class="nav nav-pills pull-right">
		<li role="presentation" class="disabled"><a href="#">Bem Vindo: ${user.login}</a></li>   
	    <li class="dropdown">
		<a href="#" id="drop0" role="button" class="dropdown-toggle" data-toggle="dropdown">Pesquisa Simples<b class="caret"></b></a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="drop2">
		   	    <li role="presentation"><a href="<%=request.getContextPath()%>/filme?acao=pagePesquisa">Pesquisa Filme</a></li>
			    <li role="presentation"><a href="<%=request.getContextPath()%>/artista?acao=pagePesquisa">Pesquisa Artista</a></li>
				<li role="presentation"><a href="<%=request.getContextPath()%>/categoria?acao=pagePesquisa">Pesquisa Categoria</a></li>
				<c:if test="${user.tipo eq 'admin'}">
					<li role="presentation"><a href="<%=request.getContextPath()%>/usuario?acao=pagePesquisa">Pesquisa Usuario</a></li>
				</c:if>
				<li role="presentation"><a href="<%=request.getContextPath()%>/estudio?acao=pagePesquisa">Pesquisa Estúdio</a></li>
			</ul>
	  	</li>
	  	<c:if test="${user.tipo eq 'admin'}">
		    <li class="dropdown">
			<a href="#" id="drop1" role="button" class="dropdown-toggle" data-toggle="dropdown">Manter/CRUD<b class="caret"></b></a>
				<ul class="dropdown-menu" role="menu" aria-labelledby="drop2">
					<li role="presentation"><a href="<%=request.getContextPath()%>/filme?acao=manter">Manter Filme</a></li>
					<li role="presentation"><a href="<%=request.getContextPath()%>/artista?acao=manter">Manter Artista</a></li>
					<li role="presentation"><a href="<%=request.getContextPath()%>/categoria?acao=manter">Manter Categoria</a></li>
					<li role="presentation"><a href="<%=request.getContextPath()%>/usuario?acao=manter">Manter Usuario</a></li>
					<li role="presentation"><a href="<%=request.getContextPath()%>/estudio?acao=manter">Manter Estúdio</a></li>
				</ul>
		  	</li>
	  	</c:if>
	  
	  	<li class="dropdown">
		<a href="#" id="drop2" role="button" class="dropdown-toggle" data-toggle="dropdown">User Tools<b class="caret"></b></a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="drop2">
	   	    	<li role="presentation"><a href="<%=request.getContextPath()%>/pdv?acao=comprar">Comprar Filmes</a></li>
	   	    	<li role="presentation"><a href="<%=request.getContextPath()%>/pdv?acao=listar">Listar Filmes</a></li>
			</ul>
	  	</li>
	  	
	  	<li class="dropdown">
		<a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">User<b class="caret"></b></a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="drop2">
				<li role="presentation"><a href="<%=request.getContextPath()%>/login?acao=logout">Deslogar</a></li>
			</ul>
	  	</li>
    </ul>
  </nav>
  <h3 class="text-muted" ><a href="<%=request.getContextPath()%>/">Cinema7</a></h3>
</div>