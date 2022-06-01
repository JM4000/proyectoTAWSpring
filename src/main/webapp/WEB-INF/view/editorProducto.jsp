<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : editorProducto
    Created on : 15-abr-2022, 4:35:10
    Author     : juanm
--%>

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

        <div class="container">
            <main class="row justify-content-md-center">

                <div class="py-5 text-center">
                    <h2>Edición de productos</h2>
                    <p class="lead">Por favor, introduzca los campos a editar</p>

                </div>
                <div class="col-md-7 col-lg-8">
                    <form:form class="needs-validation" novalidate="true" method="post" action="/admin/GuardarProducto" modelAttribute="producto">
                        <form:hidden path="idProducto"/>
                        <div class="row g-3">
                            <div class="col-sm-12">
                                <label for="name" class="form-label">Titulo del Producto</label>
                                <div class="input-group has-validation">
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
                                <form:input type="number" class="form-control" name="price" id="price" min="0" path="precioSalida" required="required"/>
                                <div class="invalid-feedback">
                                    Precio inicial obligatorio.
                                </div>
                            </div> 

                            <div class="col-md-12" name="categorias">
                                <label class="form-label">Categorias</label></br>

                                <div class="form-check form-check-inline">
                                    <form:checkboxes class="m-2" path="categoriaList" items="${categorias}" itemLabel="nombre" itemValue="nombre"/>
                                </div>
                            </div>

                            <hr class="my-4">

                            <form:button class="w-100 btn btn-primary btn-lg" type="submit" name="id">Finalizar Edición</form:button>
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
