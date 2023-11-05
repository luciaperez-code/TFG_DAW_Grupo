<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Mis pedidos</title>
</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		
		<div class="page-header">
			<h1 class="container">Mis pedidos</h1>
			<h2 class="container">Mensaje: ${mensaje }</h2>
		</div>
		
		<main class="my-5 container">
			<table class="table table-striped table-sm " >
				<th class="text-uppercase">Id</th>
				<th class="text-uppercase">Usuario</th>
				<th class="text-uppercase">Estado</th>
				<th class="text-uppercase">Fecha</th>
				<th class="text-uppercase">Dirección</th>
				<th class="text-uppercase">Tarjeta</th>
				<th class="text-uppercase">Total</th>
				
				<c:forEach var="ele" items="${pedidosUsuario }" >
					<tr>
						<td class="align-middle">${ele.idPedido }</td>
						<td class="align-middle">${ele.usuario.nombre }</td>
						<td class="align-middle">${ele.estado }</td>
						<td class="align-middle">${ele.fechaRealizacion }</td>
						<td class="align-middle">${ele.direccion.calle }, ${ele.direccion.letra }, ${ele.direccion.numero }</td>
						<td class="align-middle">${ele.tarjeta.numero }</td>
						<td class="align-middle">${ele.totalPedido }</td>
					</tr>
				</c:forEach>
			</table>
		</main>
		
		<jsp:include page="footer.jsp"></jsp:include>
	
	</body>
</html>