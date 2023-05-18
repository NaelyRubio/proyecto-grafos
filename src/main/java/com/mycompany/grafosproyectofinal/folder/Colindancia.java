/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafosproyectofinal.folder;

public class Colindancia {
    private Ciudad ciudadDestino;
    private int distancia;
    private int costo;

    public Colindancia(Ciudad ciudadDestino, int distancia, int costo) {
        this.ciudadDestino = ciudadDestino;
        this.distancia = distancia;
        this.costo = costo;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public int getDistancia() {
        return distancia;
    }

    public int getCosto() {
        return costo;
    }
}