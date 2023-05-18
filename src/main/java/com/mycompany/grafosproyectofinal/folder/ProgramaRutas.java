/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafosproyectofinal.folder;

/**
 *
 * @author naely
 */
import java.util.*;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

public class ProgramaRutas {

    class Ciudad {

    }
    String nombre;
    List<Colindancia> colindancias;

    public ProgramaRutas (String nombre) {
        this.nombre = nombre;
        this.colindancias = new ArrayList<>();
    }

    
}

class Colindancia {
    Ciudad ciudad;
    int distancia;
    int cuotaPeaje;

    public Colindancia(Ciudad ciudad, int distancia, int cuotaPeaje) {
        this.ciudad = ciudad;
        this.distancia = distancia;
        this.cuotaPeaje = cuotaPeaje;
    }
}

   
