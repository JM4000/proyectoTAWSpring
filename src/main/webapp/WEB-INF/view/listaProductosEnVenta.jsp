<%--
    Document   : listaProductosEnVenta
    Created on : 20-abr-2022, 12:01:08
    Author     : Laura Plaza
--%>


<%@page import="java.util.List"%>
<%@ page import="es.proyectotawspring.dto.UsuarioDTO" %>
<%@ page import="es.proyectotawspring.dto.ProductoDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Lista de Productos en Venta</title>
</head>
<body>
<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <img src="<%= request.getContextPath()%>/Images/logoipsum-logo-50.svg" alt="No File" width="32" height="32" class="rounded-circle">
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="<%= request.getContextPath()%>/PaginaPrincipalServlet" class="nav-link px-2 link-secondary">Página Principal</a></li>

                <li><a href="#" class="nav-link px-2 link-primary">Mis productos</a></li>

            </ul>

            <div class="dropdown text-end">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="<%= request.getContextPath()%>/Images/list.svg" alt="No File" width="32" height="32" class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">

                    <li><a class="dropdown-item" href="<%= request.getContextPath()%>/CerrarSesionServlet">Cerrar Sesión</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <div class="row">
        <div class="col col-6">
            <div class="container rows-2">
                <div class="input-group-prepend">
                    <form class="d-flex" action="${pageContext.request.contextPath}/BusquedaProductosVendedorServlet?id=1" method="get">
                        <select class="custom-select" name="filtro" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <option selected value="1">Tipo de Búsqueda</option>
                            <option value="1">Nombre</option>
                            <option value="2">Categoría</option>
                        </select>

                        <input class="form-control me-2" type="search" autocomplete="off" placeholder="Busqueda..." aria-label="Search" name="busqueda">
                        <button class="btn btn-outline-success" type="submit">Buscar</button>

                        <a type="button" class="btn btn-danger" href="/subasta/crearSubasta">Subastar producto</a>


                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <%

            UsuarioDTO usuario =  (UsuarioDTO) session.getAttribute("usuario");



            List<ProductoDTO> productos = (List) request.getAttribute("productos");
            if(productos!=null) {
                for (ProductoDTO producto : productos) {


        %>
        <div class="col col-4 p-3">
            <ul style="list-style: none;" class="list-group list-group-vertical">
                <li class="list-group-item"><%= producto.getTitulo()%>
                </li>
                <img class="fluid" src="<%= producto.getFoto()%>">
                <li class="p-2"></li>
                <a type="button" class="btn btn-warning"
                   href="/subasta/<%=producto.getIdProducto()%>/EditarSubasta" method="get">Editar</a>
                <a type="button" class="btn btn-danger"
                   href="/subasta/<%=producto.getIdProducto()%>/EliminarSubasta" method="get">Eliminar</a>
                </li>
            </ul>
        </div>

        <% }
        }%>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>