<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>

<%
    Map<String, String> errores = (Map<String,String>) request.getAttribute("errores");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header" id="div1">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#" id="enlace1">Javaflix</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Suscripciones<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/suscripciones/listar">Lista Suscripciones</a></li>
                            <li><a href="<%=request.getContextPath()%>/suscripciones/alta">Alta Suscripciones</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Usuarios<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/usuarios/listar">Lista Usuarios</a></li>
                            <li><a href="<%=request.getContextPath()%>/usuarios/alta">Alta Usuarios</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                         <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                             aria-haspopup="true" aria-expanded="false">Categorias<span
                                 class="caret"></span></a>
                         <ul class="dropdown-menu">
                             <li><a href="<%=request.getContextPath()%>/categorias/listar">Lista Categorias</a></li>
                             <li><a href="<%=request.getContextPath()%>/categorias/alta">Alta Categorias</a></li>
                         </ul>
                     </li>

                     <li class="dropdown">
                         <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                             aria-haspopup="true" aria-expanded="false">Media<span
                                 class="caret"></span></a>
                         <ul class="dropdown-menu">
                             <li><a href="<%=request.getContextPath()%>/media/listar">Lista Media</a></li>
                             <li><a href="<%=request.getContextPath()%>/media/alta">Alta Media</a></li>
                             <li><a href="<%=request.getContextPath()%>/comics">Comics</a></li>
                         </ul>
                     </li>

                     <li class="dropdown">
                         <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                             aria-haspopup="true" aria-expanded="false">Historial<span
                                 class="caret"></span></a>
                         <ul class="dropdown-menu">
                             <li><a href="<%=request.getContextPath()%>/historial/listar">Historial</a></li>
                             <li><a href="<%=request.getContextPath()%>/historial/alta">Registro Historial</a></li>
                         </ul>
                     </li>

                     <li class="dropdown">
                         <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                             aria-haspopup="true" aria-expanded="false">Comentarios<span
                                 class="caret"></span></a>
                         <ul class="dropdown-menu">
                             <li><a href="<%=request.getContextPath()%>/comentarios/listar">Lista Comentarios</a></li>
                             <li><a href="<%=request.getContextPath()%>/comentarios/alta">Registro Comentarios</a></li>
                         </ul>
                     </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
     </nav>

    <div class="container">
        <div class="row">
            <div class="col-12">
                <h2>Formulario Alta Suscripcion</h2>
            </div>
        </div>

        <br>
        <% if(errores != null && errores.size()>0){ %>
            <ul class="alert alert-danger mx-5 px5">
                <% for(String error: errores.values()){ %>
                    <li><%=error%></li>
                <% } %>
            </ul>
        <% } %>

        <div class="row">
            <form action="<%=request.getContextPath()%>/suscripciones/alta" method="post">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="">Nombre</label>
                        <input type="text" name="nombre" id="nombre"
                            class="form-control" value="${param.nombre}">
                            <%
                                if(errores != null && errores.containsKey("nombre")){
                                    out.println("<span class='text-danger'>" + errores.get("nombre")
                                        + "</span>");
                                }
                            %>
                    </div>

                    <div class="form-group">
                        <label for="">Cantidad de Dispositvos</label>
                        <input type="text" name="cantDispositivos" id="cantDispositivos"
                            class="form-control" value="${param.cantDispositivos}">
                            <%
                                if(errores != null && errores.containsKey("cantDispositivos")){
                                    out.println("<span class='text-danger'>" + errores.get("cantDispositivos")
                                        + "</span>");
                                }
                            %>
                    </div>

                    <div class="form-group">
                        <label for="">Costo</label>
                        <input type="text" name="costo" id="costo"
                            class="form-control" value="${param.costo}">
                            <%
                                if(errores != null && errores.containsKey("costo")){
                                    out.println("<span class='text-danger'>" + errores.get("costo")
                                        + "</span>");
                                }
                            %>
                    </div>

                    <div class="form-group">
                        <label for="">Calidad Máxima</label>
                        <input type="text" name="calidadMax" id="calidadMax"
                            class="form-control" value="${param.calidadMax}">
                            <%
                                if(errores != null && errores.containsKey("calidadMax")){
                                    out.println("<span class='text-danger'>" + errores.get("calidadMax")
                                        + "</span>");
                                }
                            %>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Guardar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>