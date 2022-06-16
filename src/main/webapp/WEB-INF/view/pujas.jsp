<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : pujas
    Created on : 27-abr-2022, 23:21:24
    Author     : Laura Plaza
--%>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@ page import="es.proyectotawspring.dto.UsuarioDTO" %>
<%@ page import="es.proyectotawspring.dto.ProductoDTO" %>
<%@ page import="es.proyectotawspring.dto.SubastaDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Puja</title>
</head>
<body>


<%
    ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
    SubastaDTO s = (SubastaDTO) request.getAttribute("subasta");
    UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
    int idUsuario = user.getIdUsuario();
    String error = (String) request.getAttribute("error");
    if (error == null) error = "";
    Date fecha = s.getFechaCierre();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String stringFecha = formato.format(fecha);
    SimpleDateFormat formatoHora= new SimpleDateFormat("HH:mm");
    String stringHora = formatoHora.format(fecha);


%>
<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <img src="<%= request.getContextPath()%>/Images/logoipsum-logo-50.svg" alt="No File" width="32" height="32" class="rounded-circle">
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/usuario/<%=user.getIdUsuario()%>/paginaPrincipal" class="nav-link px-2 link-secondary">Página Principal</a></li>

                <%
                    if(user.getTipoUsuario().getTipoUsuario().toString().equals("Administrador")){
                %>
                <li><a href="/admin/ListaUsuarios" class="nav-link px-2 link-dark">Clientes</a></li>
                <li><a href="/admin/ListaProductos"class="nav-link px-2 link-dark">Productos</a></li>
                <li><a href="/admin/EditorCategorias" class="nav-link px-2 link-dark">Categorías</a></li>


                <% } %>

                <li><a href="/usuario/<%=user.getIdUsuario()%>/misProductos" class="nav-link px-2 link-primary">Mis productos</a></li>

            </ul>

            <div class="dropdown text-end">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="<%= request.getContextPath()%>/Images/list.svg" alt="No File" width="32" height="32" class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                    <li><a class="dropdown-item" href="/CerrarSesion">Cerrar Sesión</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container">

    <div class="row">

        <div class="col col-10 p-3">
            <form class="border"action="/subasta/guardarPuja" method="post">
                <form:form class="border" action="/subasta/guardarPuja" method="post" modelAttribute="objetoPuja">
                <form:input path="idSubasta" type="hidden"/>
                 <form:input path="idMayorPostor" type="hidden"/>
                <ul class="list-group list-group-vertical">
                    <li class="list-group-item"><%= producto.getTitulo()%></li>
                    <ul class="p-3">
                        <img  class="fluid" src="<%= producto.getFoto()%>">
                        <ul class="p-3">

                            <li class="list-group-item"><%= producto.getDescripcion() %></li>
                            <ul class="p-3">
                                <li class="list-group-item"> Fecha de cierre de subasta : <%= stringFecha + " a las  " + stringHora %></li>

                                <li class="list-group-item"> Puja mayor : <%= s.getPredioActual() %> € </li>
                                <ul class="p-3">
                                    Cantidad a pujar :  <form:input path="cantidad" type="text"/>  €
                                    <button class="btn btn-outline-success" type="submit">Pujar</button>

                                    <lu class="p-3">  <%=error%></lu>


                                    </li>
                                </ul>
                                </form:form>
        </div>

    </div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>