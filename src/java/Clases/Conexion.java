/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Usuario
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    public static Connection conectar() throws SQLException {
        try {
            // Forzamos a GlassFish a registrar el driver de SQLite en memoria
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error: No se encontró el driver de SQLite en WEB-INF/lib", e);
        }
        
        // Ahora sí, realizamos la conexión
        return DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Prueba1\\BD\\Anime.bd");
    }
    
}
