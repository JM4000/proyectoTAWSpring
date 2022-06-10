<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : crearProducto
    Created on : 20-abr-2022, 22:02:01
    Author     : Laura Plaza
--%>


<%@page import="java.util.Date"%>

<%@page import="java.util.List"%>
<%@ page import="es.proyectotawspring.dto.UsuarioDTO" %>
<%@ page import="es.proyectotawspring.dto.CategoriaDTO" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Subasta nuevo producto</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body class="bg-light">

<%

    //  Usuario usuario = (Usuario) request.getAttribute("usuario");
    // Producto producto = (Producto) request.getAttribute("producto");
    List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
    String error = (String)request.getAttribute("errorCategorias");
    String fecha = (String) request.getAttribute("fecha");

    UsuarioDTO usuario = (UsuarioDTO)session.getAttribute("usuario");


%>

<div class="container">
    <main class="row justify-content-md-center">

        <div class="py-5 text-center">
            <h2>Creación de productos</h2>
            <p class="lead">Por favor, introduzca todos los campos</p>

        </div>
        <div class="col-md-7 col-lg-8">
<form:form class="needs-validation" novalidate="true" method="post" action="/subasta/guardarSubasta" modelAttribute="producto">

                <div class="row g-3">
                    <div class="col-sm-12">
                        <label for="name" class="form-label">Titulo del Producto</label>
                        <div class="input-group has-validation">
    <form:input  type= "hidden" path="idProducto" />
    <form:input type="text" class="form-control" name="name" id="name" required="required" path="titulo"/>
                            <div class="invalid-feedback">
                                Título de producto obligatorio.
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <label for="descripcion" class="form-label">Descripción del Producto</label>
                        <div class="input-group has-validation">
                     <form:textarea type="text" class="comment" name="descripcion" id="descripcion" rows="10" cols='120' required="required" path="descripcion"/>
                    <div class="invalid-feedback">
                                Descripción del producto obligatorio.
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <label for="image" class="form-label">URL de imagen</label>
                        <div class="input-group has-validation">
                <form:input type="text" class="form-control" name="image" id="image" path="foto" required="required"/>

                         <div class="invalid-feedback">
                                Imagen necesaria.
                            </div>
                        </div>
                    </div>

                    <div class="col-md-5">
                        <label for="price" class="form-label">Precio Inicial</label>
           <%--             <input type="number" class="form-control" name="price" id="price" min="0" value="" required></input> --%>
                        <form:input path="precioSalida" type="number" class="form-control" name="price" id="price" min="0"  required="required"/>
                        <div class="invalid-feedback">
                            Precio inicial obligatorio.
                        </div>
                    </div>

                    <div class="col-md-8">
                        <label for="fecha" class="form-label">Fecha de cierre de subasta</label>
                    <input type="date"  id="start" name="fecha" min="2022-05-05" value="<%=fecha==null ? "" : fecha%>"  required="required" />

                         <div class="invalid-feedback">
                            Fecha de cierre de subasta obligatorio
                        </div>


    <div class="col-md-12" name="categorias">
    <label class="form-label">Categorias</label></br>

    <div class="form-check form-check-inline">
    <form:checkboxes class="m-2" path="categoriaList" items="${categorias}" itemLabel="nombre" itemValue="nombre"/>
    </div>
    </div>

                            <% if (error!=null && !error.isEmpty()) {%>

                        <h4 style="color:#FF0000">  <%=error%> </h4>
                            <% }%>

                        <hr class="my-4">




                        <button class="w-100 btn btn-primary btn-lg" type="submit" name="idFinalizar" value="Finalizar Edición" >Finalizar Edición</button>
                        </form:form>
        </div>
</div>
</main>

<footer class="my-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">&copy; 2021–2022 Proyecto Tecnologías Aplicaciones Web</p>
</footer>
</div>

<script>// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict'
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')
    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }
                form.classList.add('was-validated')
            }, false)
        })
})()</script>
<script src="/docs/5.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>
