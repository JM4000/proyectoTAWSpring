<%--
    Document   : verNotificaciones
    Created on : 15-may-2022, 22:05:48
    Author     : Agustín
--%>

<%@page import="java.util.List"%>
<%@ page import="es.proyectotawspring.dto.UsuarioDTO" %>
<%@ page import="es.proyectotawspring.dto.NotificacionDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Notificaciones</title>
</head>
<%
    UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
    if (user == null) {
        response.sendRedirect(request.getContextPath());
    }
%>

<body>

<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <img src="/Images/logoipsum-logo-50.svg" alt="..." width="32" height="32" class="rounded-circle">
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">

                <li><a href="/usuario/<%=user.getIdUsuario()%>/paginaPrincipal" class="nav-link px-2 link-primary">Página Principal</a></li>


            </ul>

            <div class="dropdown text-end">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="/Images/list.svg" alt="..." width="32" height="32" class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                    <li><a class="dropdown-item" href="/CerrarSesion">Cerrar Sesión</a></li> <%--servlet log out --%>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <div class="row">
        <div class="col col-6">


            <%    List<NotificacionDTO> notificaciones = (List)request.getAttribute("notificaciones");
                String idUsuario = request.getAttribute("idUsuario").toString();
            %>
            <div class="container p-3">
                <div class="row">
                    <%
                        for (NotificacionDTO notificacion : notificaciones) {
                    %>
                    <form action="/usuario/eliminarNotificacion">
                        <ul class="col col-12 list-group list-group-horizontal">
                            <li class="list-group-item col-8"><%= notificacion.getTexto() %></li>
                            <li class="p-2">
                                <input type="text" name="idUsuario" hidden="true" value="<%=idUsuario%>">
                                <button  type="submit" name="id" value="<%=notificacion.getIdNotificacion()%>" class="btn btn-warning" >Eliminar Notificación</button>
                            </li>
                        </ul>
                    </form>

                    <% }%>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>
