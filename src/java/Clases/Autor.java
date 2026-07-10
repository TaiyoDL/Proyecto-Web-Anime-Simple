/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

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
public class Autor {
    
    private String id_autor;
    private String nombre;
    private int edad;
    private ArrayList<Anime> animes;

    public Autor(String id_autor, String nombre, int edad, ArrayList<Anime> animes) {
        this.id_autor = id_autor;
        this.nombre = nombre;
        this.edad = edad;
        this.animes = animes;
    }
    
     public Autor(String id_autor, String nombre, int edad) {
        this.id_autor = id_autor;
        this.nombre = nombre;
        this.edad = edad;
        this.animes = new ArrayList<>();
    }

    public String getId_autor() {
        return id_autor;
    }

    public void setId_autor(String id_autor) {
        this.id_autor = id_autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Anime> getAnimes() {
        return animes;
    }

    public void setAnimes(ArrayList<Anime> animes) {
        this.animes = animes;
    }
     
      public void anadirAnime(Anime a){
    this.animes.add(a);
    }
    
    
    
    
    
}
