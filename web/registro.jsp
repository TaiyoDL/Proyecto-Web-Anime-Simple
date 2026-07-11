<%-- 
    Document   : registro
    Created on : 11 jul 2026, 20:40:27
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Clases.*, java.util.ArrayList" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.SQLException, java.sql.PreparedStatement, java.sql.ResultSet, java.time.LocalDate, java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
        Connection conexion = Conexion.conectar();
        
        String nombre = request.getParameter("usuario");
        String password = request.getParameter("contrasenia");
        String nombreC = request.getParameter("nombre");
        
        PreparedStatement ps = conexion.prepareStatement("Select count(*) as existe from Usuario where usuario=?");
        ps.setString(1, nombre);
        
        ResultSet rs = ps.executeQuery();

       
        if (rs.next()) {
            if (rs.getInt("existe") > 0) {
                out.print("<script>");
                out.print("alert('Usuario ya existente');");
                out.print("window.location='index.jsp';");
                out.print("</script>");
            } else {
                ps = conexion.prepareStatement("Insert into Usuario(usuario,nombre,contrasenia) values(?,?,?)");
                ps.setString(1, nombre);
                ps.setString(2, nombreC);
                ps.setString(3, password);
                
                ps.execute();

                out.print("<script>");
                out.print("alert('Usuario registrado con éxito');");
                out.print("window.location='index.jsp';");
                out.print("</script>");
            }
        }   
        
        // Buena práctica: liberar los recursos abiertos
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (conexion != null) conexion.close();
        %>
        
    </body>
</html>
