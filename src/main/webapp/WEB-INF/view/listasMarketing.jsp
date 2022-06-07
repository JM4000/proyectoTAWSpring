<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : listasCompradores
    Created on : 25 abr 2022, 11:31:57
    Author     : Agustín
--%>


<%@page import="java.util.List"%>
<%@ page import="es.proyectotawspring.dto.ListaDTO" %>
<%@ page import="es.proyectotawspring.dto.CategoriaDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Listas de Compradores</title>
</head>
<body>
<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <img src="/Images/logoipsum-logo-50.svg" alt="No File" width="32" height="32" class="rounded-circle">
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/ListasMarketingServlet" class="nav-link px-2 link-dark">Listas de Categorias</a></li>
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
    <div class="row">
        <div class="col col-6">
            <div class="container rows-2">
                <div class="container align-items-right">
                    <form:form class="d-flex" action="/marketing/busquedaListas" method="post" modelAttribute="busqueda">
                        <form:input path="busqueda"/>
                        <button class="btn btn-outline-success" type="submit">Buscar</button>
                    </form:form>
                </div>
                <%    List<ListaDTO> listas = (List) request.getAttribute("listas");
                %>
                <div class="container p-3">
                    <div class="row">
                        <%
                            for (ListaDTO lista : listas) {
                        %>
                        <form action="/marketing/editorLista">
                            <ul class="col col-12 list-group list-group-horizontal">
                                <input name="edit" id="myInputID" type="text" class="list-group-item col-8" value="<%=lista.getnombre()%>"></input>
                                <li class="p-2">
                                    <button  type="submit" name="id" value="<%=lista.getidlista()%>" class="btn btn-warning" >Editar</button>
                                    <a type="button" class="btn btn-danger" href="marketing/<%=lista.getidlista()%>/eliminarLista">Eliminar</a>
                                    <a type="button" class="btn btn-danger" href="/marketing/<%=lista.getidlista()%>/verLista">Ver lista de compradores</a>
                                </li>
                            </ul>
                        </form>

                        <% }%>
                    </div>
                </div>
            </div>
        </div>
        <div class="col col-6">
            <div class="container rows-2">
                <div class="container align-items-right">
                    <form class="d-flex" action="/marketing/nuevaLista" method="post">
                        <input class="form-control me-2" type="text"autocomplete="off" placeholder="Nueva Lista" aria-label="New" name="nombre">
                        <select class="form-control me-2" name="categoria">
                            <%    List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
                                for (CategoriaDTO categoria : categorias) {
                            %>
                            <option><%=categoria.getNombre()%></option>

                            <%
                                }
                            %>
                        </select>
                        <button class="btn btn-outline-success" type="submit">Crear</button>
                    </form>
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