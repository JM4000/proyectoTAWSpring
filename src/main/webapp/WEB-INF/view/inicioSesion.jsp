<%-- 
    Document   : inicioSesion
    Created on : 10-abr-2022, 20:28:57
    Author     : Agustín
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <title>Inicio Sesión</title>
    </head>
     <%
        String strError = (String)request.getAttribute("error");
        if (strError == null) strError = "";
    %>   
    
     <header class="p-3 mb-3 border-bottom">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <a class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                        <img src="/Images/logoipsum-logo-50.svg" alt="No File" width="32" height="32" class="rounded-circle">
                    </a>

                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="<%= request.getContextPath()%>/NuevoUsuarioServlet" class="nav-link px-2 link-secondary">No tengo cuenta</a></li>
                        
                    </ul>

               
                </div>
            </div>
        </header>
    <body>
        <%= strError %>
        <form method="POST" action="iniciarSesion" class="row g-3 needs-validation" novalidate>
            <span class="input-group-text justify-content-center ">
                <div class="col-md-3">

                  * Nombre de usuario
                  <input type="text" class="form-control" name="userName" required>
                </div>

                <div class="col-md-3 offset-md-1">
                  * Contraseña
                  <input type="password" class="form-control" name="inputPassword" required>
                </div>
                
               
            </span>
             <div class="col-md-6">
                    <button type="submit" class="btn btn-primary">Iniciar Sesión!</button>
                 </div>
        </form>
            
            
      <script>    // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function () {
        'use strict'
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.querySelectorAll('.needs-validation')
            // Loop over them and prevent submission
            Array.prototype.slice.call(forms).forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                    }

                     form.classList.add('was-validated')
            }, false)

            })
        })()</script>
    </body>
</html>
