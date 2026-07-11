<%-- 
    Document   : iniciar
    Created on : 11 jul 2026, 22:19:32
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Clases.*, java.util.ArrayList" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.SQLException, java.sql.PreparedStatement, java.sql.ResultSet, java.time.LocalDate, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sesión</title>
        <link rel="stylesheet" href="EstilosBoton.css">
    </head>
    <body>
        
        <%
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("contrasenia");

        Connection conexion = Conexion.conectar();

        PreparedStatement ps = conexion.prepareStatement("Select count(*) as existe from usuario where usuario=?");
        ps.setString(1, usuario);

        ResultSet rs = ps.executeQuery();

        // 💡 PRIMERA CORRECCIÓN: Avanzamos el cursor para verificar si el usuario existe
        if (rs.next()) {
            if (rs.getInt("existe") == 0) {
                out.print("<script>");
                out.print("alert('El usuario no está registrado');");
                out.print("window.location='index.jsp';"); // Redirige a tu nuevo Login (Index.jsp)
                out.print("</script>");
            } else {
                // Si el usuario sí existe, buscamos su contraseña
                ps = conexion.prepareStatement("Select contrasenia from usuario where usuario=?");
                ps.setString(1, usuario);

                rs = ps.executeQuery();

                // 💡 SEGUNDA CORRECCIÓN: Avanzamos el cursor antes de leer la contraseña
                if (rs.next()) {
                    if (!rs.getString("contrasenia").equals(password)) {
                        out.print("<script>");
                        out.print("alert('Contraseña incorrecta');");
                        out.print("window.location='index.jsp';");
                        out.print("</script>");
                    } else {
                        // Todo correcto: Pintamos el formulario con el botón decorado hacia el catálogo
                        out.print("<form method='post' action='Animes.jsp'>");
                        out.print("    <input type='text' name='datoUsuario' placeholder='Introduce el texto aquí' required value='" + usuario + "'>");
                        out.print("    <input type='submit' value='Entrar al Catálogo'>");
                        out.print("</form>");
                    }
                }
            }
        }
        
        // Buena práctica: Limpieza y cierre de conexiones
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (conexion != null) conexion.close();
        %>
        
    </body>
</html>