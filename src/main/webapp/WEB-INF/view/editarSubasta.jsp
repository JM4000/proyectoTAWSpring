<%--
    Document   : editorProductoSubasta
    Created on : 26-abr-2022, 8:46:43
    Author     : Laura Plaza
--%>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@page import="java.util.List"%>
<%@ page import="es.proyectotawspring.dto.UsuarioDTO" %>
<%@ page import="es.proyectotawspring.dto.ProductoDTO" %>
<%@ page import="es.proyectotawspring.dto.CategoriaDTO" %>
<%@ page import="es.proyectotawspring.dto.SubastaDTO" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Productos</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body class="bg-light">

<%


    UsuarioDTO usuario = (UsuarioDTO)session.getAttribute("usuario");
    ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
    List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
    SubastaDTO subasta = (SubastaDTO) request.getAttribute("subasta");
    String error = (String)request.getAttribute("errorCategorias");



    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    String fecha = formato.format(subasta.getFechaCierre());


%>

<div class="container">
    <main class="row justify-content-md-center">

        <div class="py-5 text-center">
            <h2>Edición de productos</h2>
            <p class="lead">Por favor, introduzca los campos a editar</p>

        </div>
        <div class="col-md-7 col-lg-8">
            <form class="needs-validation" novalidate action="${pageContext.request.contextPath}/GuardarProductoServlet">
                <div class="row g-3">
                    <div class="col-sm-12">
                        <label for="name" class="form-label">Titulo del Producto</label>
                        <div class="input-group has-validation">
                            <input type="text" class="form-control" name="name" id="name" value="<%= producto.getTitulo()%>" required></input>
                            <div class="invalid-feedback">
                                Título de producto obligatorio.
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <label for="descripcion" class="form-label">Descripción del Producto</label>
                        <div class="input-group has-validation">
                            <textarea type="text" class="comment" name="descripcion" id="descripcion" rows="10" cols='120' required><%= producto.getDescripcion()%></textarea>
                            <div class="invalid-feedback">
                                Descripción del producto obligatorio.
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <label for="image" class="form-label">URL de imagen</label>
                        <div class="input-group has-validation">
                            <input type="text" class="form-control" name="image" id="image" value="<%= producto.getFoto()%>" required></input>
                            <div class="invalid-feedback">
                                Imagen necesaria.
                            </div>
                        </div>
                    </div>

                    <div class="col-md-5">
                        <label for="price" class="form-label">Precio en subasta actual : <%=  subasta.getPredioActual()%> €</label>

                    </div>
                </div>
                <div class="col-md-8">
                    <label for="fecha" class="form-label">Fecha de cierre de subasta</label>
                    <input type="date" id="start" name="fecha" value="<%=fecha%>" min="2022-05-05"  required> </input>
                    <div class="invalid-feedback">
                        Fecha de cierre de subasta obligatorio
                    </div>
                </div>

                <div class="col-md-12" name="categorias">
                        <label for="categorias" class="form-label">Categorias</label></br>
                        <%
                            for (CategoriaDTO c : categorias) {
                        %>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" name="categorias" type="checkbox" value="<%= c.getIdCategoria()%>" <%= !producto.containsCategory(c)? "": "checked" %>>
                            <label class="form-check-label" for="<%= c.getIdCategoria()%>">
                                <%= c.getNombre()%>
                            </label>
                        </div>

                        <% } %>
                    </div>


                <% if (error!=null && !error.isEmpty()) {%>

                <h4 style="color:#FF0000">  <%=error%> </h4>
                <% }%>
                    <hr class="my-4">

                    <button class="w-100 btn btn-primary btn-lg" type="submit" name="id" value="<%= producto.getIdProducto()%>">Finalizar Edición</button>
            </form>

            <th></th>
            <form action="CerrarSubastaServlet" method="POST">
                <button class="w-100 btn btn-primary btn-lg" type="submit" name="subastaId" value="<%= subasta.getIdSubasta()%>">Cerrar Subasta</button>

            </form>



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