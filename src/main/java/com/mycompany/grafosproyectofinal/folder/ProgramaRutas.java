/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafosproyectofinal.folder;

/**
 *
 * @author Guimel Naely Rubio Morillon - 229324
 */
import java.util.*;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

public class ProgramaRutas {

    //Clase interna ciudad. 
    class Ciudad {

        
    //atributos de la clase interna
    }
    String nombre;
    List<Colindancia> colindancias;

    
    /**
     * Constructor que recibe paramos de nombre 
     * @param nombre asigna el valor al parametro nombre
     */
    public ProgramaRutas (String nombre) {
        this.nombre = nombre;
        //crea una nueva instancia de arraylist y le asigna el atributo colindancia
        this.colindancias = new ArrayList<>();
    }

    
}

/**
 * Clase colindancia
 * @author Guimel Naely Rubio Morillon - 229324
 */
class Colindancia {
    
    //Areibutos de la clase 
    Ciudad ciudad;
    int distancia;
    int cuotaPeaje;

    /*
    * Constructor que recibe parametros de tipo ciudad, dostancia, cuotapeaje
    *
    */
    public Colindancia(Ciudad ciudad, int distancia, int cuotaPeaje) {
        this.ciudad = ciudad;
        this.distancia = distancia;
        this.cuotaPeaje = cuotaPeaje;
    }
}

   
