/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafosproyectofinal.folder;

/**
 * @author Guimel Naely Rubio Morillon - 229324
 */
public class Colindancia {
    
    //Atributos privados de la clase.
    private Ciudad ciudadDestino;
    private int distancia;
    private int costo;

    /**
     * Costructor que recibe parametros tipo, distancia, ciudad y costo.
     * @param ciudadDestino Destuno de la colindancia
     * @param distancia distancia entre la ciudad actual y la ciudad destino
     * @param costo costo asociado a la colindancia
     */
    public Colindancia(Ciudad ciudadDestino, int distancia, int costo) {
        this.ciudadDestino = ciudadDestino;
        this.distancia = distancia;
        this.costo = costo;
    }

    /**
     * Metodo que obtiene el ciudad de destino
     * @return ciudad destino
     */
    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    /**
     * Metodo que obtiene la distancia
     * @return distancia
     */
    public int getDistancia() {
        return distancia;
    }

    /**
     * Metodo que obtiene el costo
     * @return costo
     */
    public int getCosto() {
        return costo;
    }
}