<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : editorLista
    Created on : 15-may-2022, 16:37:41
    Author     : Agustín
--%>

<%@page import="java.util.List"%>
<%@ page import="es.proyectotawspring.dto.ListaDTO" %>
<%@ page import="es.proyectotawspring.dto.UsuarioDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Usuarios de la lista</title>
</head>
<body>
<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <img src="/Images/logoipsum-logo-50.svg" alt="No File" width="32" height="32" class="rounded-circle">
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/marketing/listasMarketing" class="nav-link px-2 link-dark">Listas de Categorias</a></li>
            </ul>

            <div class="dropdown text-end">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="/Images/list.svg" alt="No File" width="32" height="32" class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                    <li><a class="dropdown-item" href="/CerrarSesion">Cerrar Sesión</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <div class="row d-flex">
        <div class="col col-6">
            <div class="container rows-2">
                <div class="input-group-prepend">

                    <%
                        ListaDTO lista = (ListaDTO) request.getAttribute("lista");
                    %>
                    <form:form class="d-flex" action="/marketing/BusquedaUsuarios" method="post" modelAttribute="busqueda">
                        <form:hidden path="idlist"/>
                        <form:select class="custom-select" name="filtro" type="button" data-toggle="dropdown"
                                     aria-haspopup="true" aria-expanded="false" path="filtro">
                            <form:option selected="true" value="1">Tipo de Búsqueda</form:option>
                            <form:option value="1">Nombre de usuario</form:option>
                            <form:option value="2">Nombre</form:option>
                            <form:option value="3">Apellidos</form:option>
                            <form:option value="4">Domicilio</form:option>
                            <form:option value="5">Ciudad</form:option>
                            <form:option value="6">Edad</form:option>
                            <form:option value="7">Género</form:option>
                            <form:option value="8">Tipo de Usuario</form:option>
                        </form:select>

                        <form:input class="form-control me-2" type="search" autocomplete="off" placeholder="Busqueda..."
                                    aria-label="Search" name="busqueda" path="busqueda"/>
                        <form:button class="btn btn-outline-success" type="submit">Buscar</form:button>
                    </form:form>
                </div>

                <%
                    List<UsuarioDTO> usuarios = lista.getusuarioList();
                %>
                <div class="container p-3">
                    <div class="row">
                        <%
                            if (!usuarios.isEmpty()){
                        %>
                        <form action="/marketing/notificarLista/<%=lista.getidlista()%>" method="post">
                            <textarea name="mensaje"></textarea>
                            <button  type="submit" name="id" class="btn btn-primary">Notificar Lista</button>
                        </form>
                        <%
                            }
                            for (UsuarioDTO usuario : usuarios) {
                        %>
                            <ul class="col col-12 list-group list-group-horizontal">
                                <li class="list-group-item col-8"><%= usuario.toString() %></li>
                                <li class="p-2">
                                    <a type="button" class="btn btn-danger" href="/marketing/eliminarUsuarioLista/<%=usuario.getIdUsuario()%>/<%=lista.getidlista()%>">Eliminar</a>
                                </li>
                            </ul>

                        <% }%>
                    </div>
                </div>
            </div>
        </div>
        <div class="col col-6">
            <div class="container rows-2">
                <div class="container align-items-right">
                    <form class="d-flex" action="${pageContext.request.contextPath}/AnadirUsuarioServlet?idlista=<%=lista.getidlista()%>" method="post">
                        <button class="btn btn-outline-success" type="submit">Añadir Usuario</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>
