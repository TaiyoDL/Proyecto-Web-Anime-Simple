/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Usuario {
    
    private String nombre;
    private String usuario;
    private String contrasenia;
    private ArrayList<Anime> animes;

    public Usuario(String nombre, String usuario, String contrasenia) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.animes=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public void anadirAnime(Anime a){
    this.animes.add(a);
    }
    
    public void cargarAnimes()  {
    Connection conexion = null;
        try {
            conexion = Conexion.conectar();
        } catch (SQLException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    
    PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("Select * from Anime left join Relacion_AU on Relacion_AU.id_anime=Anime.id_anime where Relacion_AU.usuario=?");
        } catch (SQLException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        try {
            ps.setString(1, this.getUsuario());
        } catch (SQLException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    
    ResultSet rs = null;
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    
        try {
            while(rs.next()){
                ArrayList<Genero> generos = new ArrayList<>();
                
                PreparedStatement ps2 = conexion.prepareStatement("Select * from Genero left join Relacion_AG on Relacion_AG.nombre_genero=Genero.nombre where Relacion_AG.id_anime=?");
                ps2.setString(1, rs.getString("id_anime"));
                
                ResultSet rs2 = ps2.executeQuery();
                
                while(rs2.next()){
                    generos.add(new Genero(rs2.getString("nombre")));
                }
                rs2.close();
                ps2.close();
                
                Autor autor = null;
                String idAutor = rs.getString("id_autor");
                if (idAutor != null) {
                    PreparedStatement psAutor = conexion.prepareStatement("Select * from Autor where id_autor=?");
                    psAutor.setString(1, idAutor);
                    ResultSet rsAutor = psAutor.executeQuery();
                    if (rsAutor.next()) {
                        autor = new Autor(rsAutor.getString("id_autor"), rsAutor.getString("nombre"), rsAutor.getInt("edad"));
                    }
                    rsAutor.close();
                    psAutor.close();
                }
                
                Casa_Animadora casa = null;
                String idCasa = rs.getString("id_casa");
                if (idCasa != null) {
                    PreparedStatement psCasa = conexion.prepareStatement("Select * from Casa_Animadora where id_casa=?");
                    psCasa.setString(1, idCasa);
                    ResultSet rsCasa = psCasa.executeQuery();
                    if (rsCasa.next()) {
                        casa = new Casa_Animadora(rsCasa.getString("id_casa"), rsCasa.getString("nombre"));
                    }
                    rsCasa.close();
                    psCasa.close();
                }
                
                String fechaStr = rs.getString("fecha_lanzamiento");
                LocalDate fechaLanzamiento = (fechaStr != null) ? LocalDate.parse(fechaStr) : null;
                
                this.animes.add(new Anime(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("puntuacion"),
                        rs.getInt("capitulos"),
                        rs.getInt("temporadas"),
                        fechaLanzamiento,
                        rs.getString("foto"),
                        rs.getString("cartel"),
                        autor,
                        generos,
                        casa
                ));
            }   } catch (SQLException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        try {
            ps.close();
        } catch (SQLException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
}
    
}
