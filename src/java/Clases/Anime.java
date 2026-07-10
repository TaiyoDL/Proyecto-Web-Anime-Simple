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

public class Anime {
    
    private String id_anime;
    private String nombre;
    private String descripcion;
    private double puntuacion;
    private int capitulos;
    private int temporadas;
    private LocalDate fecha_lanzamiento;
    private String foto;
    private String cartel;
    private Autor autor;
    private ArrayList<Genero> generos;
    private ArrayList<Usuario> usuarios;
    private Casa_Animadora casa;

    public Anime(String id_anime, String nombre, String descripcion, double puntuacion, int capitulos, int temporadas, LocalDate fecha_lanzamiento, String foto, String cartel, Autor autor, ArrayList<Genero> generos, ArrayList<Usuario> usuarios, Casa_Animadora casa) {
        this.id_anime = id_anime;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.capitulos = capitulos;
        this.temporadas = temporadas;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.foto = foto;
        this.cartel = cartel;
        this.autor = autor;
        this.generos = generos;
        this.usuarios = usuarios;
        this.casa = casa;
    }

    public String getId_anime() {
        return id_anime;
    }

    public void setId_anime(String id_anime) {
        this.id_anime = id_anime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public LocalDate getFecha_lanzamiento() {
        return fecha_lanzamiento;
    }

    public void setFecha_lanzamiento(LocalDate fecha_lanzamiento) {
        this.fecha_lanzamiento = fecha_lanzamiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCartel() {
        return cartel;
    }

    public void setCartel(String cartel) {
        this.cartel = cartel;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public ArrayList<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<Genero> generos) {
        this.generos = generos;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Casa_Animadora getCasa() {
        return casa;
    }

    public void setCasa(Casa_Animadora casa) {
        this.casa = casa;
    }

    public Anime(String nombre, String descripcion, double puntuacion, int capitulos, int temporadas, LocalDate fecha_lanzamiento, String foto, String cartel, Autor autor, ArrayList<Genero> generos, Casa_Animadora casa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.capitulos = capitulos;
        this.temporadas = temporadas;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.foto = foto;
        this.cartel = cartel;
        this.autor = autor;
        this.generos = generos;
        this.casa = casa;
    }
    
    
    
   public void cargarUsuarios() {
   Connection conexion = null;
        try {
            conexion = Conexion.conectar();
        } catch (SQLException ex) {
            System.getLogger(Anime.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
   
   PreparedStatement  ps = null;
        try {
            ps = conexion.prepareStatement("Select * from Usuario left join Relacion_AU on Usuario.usuario=Relacion_AU.usuario where Relacion_AU.id_Anime=?");
        } catch (SQLException ex) {
            System.getLogger(Anime.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        try {
            ps.setString(1, this.getId_anime());
        } catch (SQLException ex) {
            System.getLogger(Anime.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
   
   ResultSet rs = null;
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.getLogger(Anime.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
   
        try {
            while(rs.next()){
                
                this.usuarios.add(new Usuario(rs.getString("nombre"),rs.getString("usuario"),rs.getString("contrasenia")));
            }    } catch (SQLException ex) {
            System.getLogger(Anime.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
   }
    
    
    
    
}
