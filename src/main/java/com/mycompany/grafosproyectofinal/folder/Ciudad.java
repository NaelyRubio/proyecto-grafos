/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafosproyectofinal.folder;

import java.util.Objects;

/**
 *
 * @author naely
 */
public class Ciudad {

    //Atributos privados de la clase
    private String nombre;

    /*
     * Constructor que recibe parametro nombre que representa el nombre
    de la ciudad. 
     */
    public Ciudad(String nombre) {
        this.nombre = nombre;

    }

    /**
     * Metodo que obtiene el nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que establece el nombre
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;

    }

    
    /**
     * Metodo hashCode para generar un hash a partir de un int 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    /**
     * Metodo equals que compara dos objetos para identificar que sean iguales
     * @param obj
     * @return boolean 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ciudad other = (Ciudad) obj;
        return Objects.equals(this.nombre, other.nombre);
    }

    /**
     * Metodo toString qque regressa un String del objeto
     * @return String
     */
    @Override
    public String toString() {
        return "Ciudad{" + "nombre=" + nombre + '}';
    }

}
