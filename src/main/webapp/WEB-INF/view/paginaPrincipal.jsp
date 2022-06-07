<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : paginaPrincipal
    Created on : 26-abr-2022, 10:44:54
    Author     : Chris
    Autor notificaciones: Agustín
--%>



<%@page import="java.util.List"%>
<%@ page import="es.proyectotawspring.dto.CategoriaDTO" %>
<%@ page import="es.proyectotawspring.dto.UsuarioDTO" %>
<%@ page import="es.proyectotawspring.dto.SubastaDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Pagina principal</title>
</head>
<%
    List<CategoriaDTO> categorias = (List)request.getAttribute("categorias");


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

                <% if (user.getTipoUsuario().getTipoUsuario().equals("Administrador")){%>


                <li><a href="/admin/ListaUsuarios" class="nav-link px-2 link-dark">Clientes</a></li> <%-- solo puede ir si es admin --%>
                <li><a href="/admin/BusquedaProductos" class="nav-link px-2 link-dark">Productos</a></li> <%-- solo puede ir si es admin --%>
                <li><a href="/admin/BusquedaCategorias" class="nav-link px-2 link-dark">Categorías</a></li> <%-- solo puede ir si es admin --%>

                <%} else {%>
                <li><a href="/usuario/<%=user.getIdUsuario()%>/misProductos" class="nav-link px-2 link-dark">Mis Productos </a></li>
                <%
                    }
                %>
                <li> <p class="text-success">Bienvenido <%= user.getNombreUsuario() %></p> </li>
            </ul>

            <div class="dropdown text-end">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="/Images/list.svg" alt="..." width="32" height="32" class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                    <li><a class="dropdown-item" href="/usuario/<%=user.getIdUsuario()%>/notificaciones">Notificaciones</a></li> <%--notificaciones --%>
                    <li><a class="dropdown-item" href="/CerrarSesion">Cerrar Sesión</a></li> <%--servlet log out --%>
                </ul>
            </div>
        </div>
    </div>
</header>



<div class="container">
    <div><h5>FILTRO</h5></div>

    <div class="row">
        <div class="col col-6">
            <div class="container rows-2">
                <div class="input-group-prepend">
                    <form:form action="/usuario/<%=user.getIdUsuario()%>/filtroPaginaPrincipal" method="post" modelAttribute="filtroPaginaPrincipal">

                        <form:select path="filtro" class="custom-select" name="filtro" type="button" data-toggle="dropdown"
                                     aria-haspopup="true" aria-expanded="false">

                            <form:option selected="true" value="todos">Todos los productos</form:option>
                            <form:option value="favoritos">Favoritos</form:option>
                            <form:option value="comprados">Comprados</form:option>

                        </form:select>

                        <form:select path="categoriaNombre" class="custom-select" name="filtro" type="button" data-toggle="dropdown"
                                     aria-haspopup="true" aria-expanded="false">

                            <form:options items="${categorias}" itemValue="nombre" itemLabel="nombre"/>


                        </form:select>

                        <form:input class="form-control me-2" type="search" autocomplete="off" placeholder="Nombre de Producto"
                                    aria-label="Search" name="busqueda" path="busqueda"/>
                        <form:button class="btn btn-outline-success" type="submit">Filtrar</form:button>
                    </form:form>
                    <%--
                    <form class="d-flex" action="${pageContext.request.contextPath}/FiltroPaginaPrincipalServlet" method="get">

                        <input type="hidden"name="id" value ="<%=user.getIdUsuario()%>"/>
                        <select class="custom-select" name="filtro" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                            <option selected value ="todos"> Todos los productos </option>
                            <option  value="favoritos">Favoritos</option>
                            <option value="comprados">Comprados</option>



                        </select>
                        <select class="custom-select" name="categoria" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <option value="">Todas las categorias</option>
                            <%
                                for (CategoriaDTO c: categorias){
                            %>
                            <option value="<%=c.getNombre()%>"> <%= c.getNombre() %> </option>
                            <%
                                }
                            %>
                        </select>


                        <input type="search" autocomplete="off" placeholder="Nombre de producto" aria-label="Search" name="busqueda">

                        <button class="btn btn-outline-success" type="submit">Filtrar</button>

                    </form>
                    --%>
                </div>
            </div>
        </div>
    </div>


    <div class="row">
        <% String tit = (String)request.getAttribute("listaTipo"); %>
        <div><h5><%= tit %></h5> </div><%-- Poner que lista estas pasando --%>

        <%
            Boolean fav = (Boolean)request.getAttribute("fav");
            Boolean comp = (Boolean)request.getAttribute("comp");

            List<SubastaDTO> subastas = (List) request.getAttribute("subastas");
            if (subastas == null || subastas.isEmpty()){%>
        <div class="alert alert-danger" role="alert">
            NO HAY PRODUCTOS, PRUEBA CON OTRO FILTRO
        </div>
        <%}else{

            for (SubastaDTO subasta : subastas) {

        %>
        <div class="col col-4 p-3">
            <h3> Precio actual: <%= subasta.getPredioActual() %> €</h3>
            <ul class="list-group list-group-vertical">
                <li class="list-group-item"><%= subasta.getProducto().getTitulo()%></li>
                <img  class="fluid" src="<%= subasta.getProducto().getFoto()%>"/>
                <li class="p-2">
                    <a type="button" class="btn btn-danger"
                            <%if (!fav && !comp){ %> href="usuario/<%=user.getIdUsuario()%>/<%= subasta.getProducto().getIdProducto()%>/ponerFavoritos>">Poner a favoritos
                        <%}else if (fav && !comp){ %>  href="usuario/<%=user.getIdUsuario()%>/<%= subasta.getProducto().getIdProducto()%>/quitarFavoritos>"">Quitar de favoritos

                        <%}else{%>  hidden> <%}%>

                    </a>
                    <a type="button" class="btn btn-outline-success"
                            <% if (!comp){ %> href="/usuario/<%=user.getIdUsuario()%>/<%=subasta.getIdSubasta()%>/subasta"> Participar en subasta
                        <% } else{ %>  href="/usuario/<%=user.getIdUsuario() %>/<%= subasta.getProducto().getIdProducto()%>/quitarComprados" >Quitar de comprados
                        <%} %>
                    </a>


                </li>
            </ul>

        </div>
        <%
                }
            }
        %>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>