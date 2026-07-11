/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Usuario
 */
public class Funciones {
    public static ArrayList<Anime> cargarAnimes() {
    ArrayList<Anime> catalogo = new ArrayList<>();
    String sql = "Select Anime.nombre,Anime.descripcion,Anime.puntuacion,Anime.capitulos,Anime.temporadas,Anime.fecha_lanzamiento,Anime.foto,Anime.cartel,Autor.nombre as nombreAutor,Casa_Animadora.nombre as nombreCasa from Anime left join Autor on Autor.id_autor =Anime.id_autor left join Casa_Animadora on Casa_Animadora.id_casa =Anime.id_casa";

    try (Connection conexion = Conexion.conectar();
         PreparedStatement ps = conexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String nombreAnime = rs.getString("nombre");
            String fechaStr = rs.getString("fecha_lanzamiento");
            LocalDate fechaLanzamiento = (fechaStr != null) ? LocalDate.parse(fechaStr) : null;

            catalogo.add(new Anime(
                nombreAnime,
                rs.getString("descripcion"),
                rs.getDouble("puntuacion"),
                rs.getInt("capitulos"),
                rs.getInt("temporadas"),
                fechaLanzamiento,
                rs.getString("foto"),
                rs.getString("cartel"),
                autorAnime(conexion, nombreAnime),
                generosAnime(conexion, nombreAnime),
                CasaAnime(conexion, nombreAnime)
            ));
        }
    } catch (SQLException ex) {
        System.getLogger(Anime.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }

    return catalogo;
}
    
    
    public static ArrayList<Anime> cargarAnimes(String usuario) {
    ArrayList<Anime> catalogo = new ArrayList<>();
    
    
    String sql = "SELECT Anime.nombre, Anime.descripcion, Anime.puntuacion, Anime.capitulos, Anime.temporadas, "
               + "Anime.fecha_lanzamiento, Anime.foto, Anime.cartel, Autor.nombre AS nombreAutor, "
               + "Casa_Animadora.nombre AS nombreCasa "
               + "FROM Anime "
               + "LEFT JOIN Autor ON Autor.id_autor = Anime.id_autor "
               + "LEFT JOIN Casa_Animadora ON Casa_Animadora.id_casa = Anime.id_casa "
               + "LEFT JOIN relacion_au ON relacion_au.id_anime = Anime.id_anime "
               + "LEFT JOIN Usuario ON Usuario.usuario = relacion_au.usuario " 
               + "WHERE Usuario.usuario = ?"; 

    
    try (Connection conexion = Conexion.conectar();
         PreparedStatement ps = conexion.prepareStatement(sql)) {
        
        
        ps.setString(1, usuario);
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String nombreAnime = rs.getString("nombre");
                String fechaStr = rs.getString("fecha_lanzamiento");
                LocalDate fechaLanzamiento = (fechaStr != null) ? LocalDate.parse(fechaStr) : null;

                catalogo.add(new Anime(
                    nombreAnime,
                    rs.getString("descripcion"),
                    rs.getDouble("puntuacion"),
                    rs.getInt("capitulos"),
                    rs.getInt("temporadas"),
                    fechaLanzamiento,
                    rs.getString("foto"),
                    rs.getString("cartel"),
                    autorAnime(conexion, nombreAnime),
                    generosAnime(conexion, nombreAnime),
                    CasaAnime(conexion, nombreAnime)
                ));
            }
        }
    } catch (SQLException ex) {
        System.getLogger(Anime.class.getName()).log(System.Logger.Level.ERROR, "Error al cargar los animes del usuario", ex);
    }

    return catalogo;
}
private static ArrayList<Genero> generosAnime(Connection conexion, String nombreAnime) throws SQLException {
    String sql = "Select Genero.nombre from Genero left join Relacion_AG on Relacion_AG.nombre_genero=Genero.nombre left join Anime on Anime.id_anime=Relacion_AG.id_anime where Anime.nombre=?";
    ArrayList<Genero> generos = new ArrayList<>();

    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, nombreAnime);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                generos.add(new Genero(rs.getString("nombre")));
            }
        }
    }
    return generos;
}

private static Autor autorAnime(Connection conexion, String nombre) throws SQLException {
    String sql = "Select * from Autor left join Anime on Anime.id_autor=Autor.id_Autor where Anime.nombre=?";

    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, nombre);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Autor(rs.getString("id_Autor"), rs.getString("nombre"), rs.getInt("edad"));
            }
        }
    }
    return null;
}

private static Casa_Animadora CasaAnime(Connection conexion, String nombre) throws SQLException {
    String sql = "Select * from Casa_Animadora left join Anime on Anime.id_casa=Casa_Animadora.id_casa where Anime.nombre=?";

    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, nombre);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Casa_Animadora(rs.getString("id_casa"), rs.getString("nombre"));
            }
        }
    }
    return null;
}
}
