<%-- 
    Document   : index
    Created on : 9 jul 2026, 20:26:28
    Author     : Usuario
--%>
<%@ page import="Clases.*, java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogo Animes</title>
        <link rel="icon" href="4043233-anime-away-face-no-nobody-spirited_113254.ico" type="image/x-icon">
        <link rel="stylesheet"  href="Estilos.css">
    </head>
    <body>
        
        

    <header>

        <div class="opcion">
            
            <a href="">Buscar Anime</a>
            
            
        </div>
        
        <div class="opcion"><a href="">Buscar Autor</a></div>
        
        <div class="opcion"><a href="">Buscar Casa Animadora</a></div>
            
            
        
    </header>

    <main>
        
        <div class="contenedorAnimes">
        
       <% for(Anime a : Funciones.cargarAnimes()){ %>
    <div class="Anime">

        <div class="cartel"><img src="<%= a.getCartel() %>"></div>

        <div class="datos">

            <div class="foto"><img src="<%= a.getFoto() %>"></div>

            <div class="infoPrincipal">
                <p class="nombre"><%= a.getNombre() %></p>
                <p class="descripcion"><%= a.getDescripcion() %></p>
            </div>

            <div class="contenido">
                <p class="capitulos">Capitulos: <%= a.getCapitulos() %></p>
                <p class="temporadas">Temporadas: <%= a.getTemporadas() %></p>
            </div>

            <div class="creacion">
                <p class="autor">Autor/a: <%= a.getAutor() != null ? a.getAutor().getNombre() : "" %></p>
                <p class="casa">Casa Animadora: <%= a.getCasa() != null ? a.getCasa().getNombre() : "" %></p>
            </div>

            <div class="extra">
                <div class="generos">
                    <% if(a.getGeneros() != null) { 
                        for(Genero g : a.getGeneros()){ %>
                            <p><%= g.getNombre() %></p>
                    <%  } 
                       } %>
                </div>
            </div>

            <div class="final">
                <p class="fecha">Fecha de Estreno: <%= a.getFecha_lanzamiento() %></p>
                <p class="nota"><%= a.getPuntuacion() %><img id="star" src="https://static.vecteezy.com/system/resources/thumbnails/021/508/043/small/black-star-black-shotting-star-transparent-black-bokeh-stars-free-free-png.png"></p>
            </div>

        </div>
    </div>
<% } %>
</div>
    </main>

    <footer></footer>
</body>
</html>
