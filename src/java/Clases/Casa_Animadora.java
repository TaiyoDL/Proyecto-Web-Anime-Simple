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
public class Casa_Animadora {
    private String id_casa;
    private String nombre;
    private ArrayList<Anime> animes;

    public Casa_Animadora(String id_casa, String nombre, ArrayList<Anime> animes) {
        this.id_casa = id_casa;
        this.nombre = nombre;
        this.animes = animes;
    }

    public Casa_Animadora(String id_casa, String nombre) {
        this.id_casa = id_casa;
        this.nombre = nombre;
        this.animes=new ArrayList<>();
    }

    public String getId_casa() {
        return id_casa;
    }

    public void setId_casa(String id_casa) {
        this.id_casa = id_casa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
