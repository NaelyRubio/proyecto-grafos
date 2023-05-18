/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafosproyectofinal.GUI;

import com.mycompany.grafosproyectofinal.Graph;
import com.mycompany.grafosproyectofinal.folder.Ciudad;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author naely
 */
public class Main {

    static Ciudad ciudades[] = new Ciudad[15];

    static Graph grafo = new Graph();

    static Ciudad obregon = new Ciudad("Obregon");
    static Ciudad guaymas = new Ciudad("Guaymas");
    static Ciudad empalme = new Ciudad("Empalme");
    static Ciudad hermosillo = new Ciudad("Hermosillo");
    static Ciudad navojoa = new Ciudad("Navojoa");
    static Ciudad huatabampo = new Ciudad("Huatabampo");
    static Ciudad alamos = new Ciudad("Alamos");
    static Ciudad yecora = new Ciudad("Yecora");
    static Ciudad santaAna = new Ciudad("Santa Ana");
    static Ciudad magdalena = new Ciudad("Magdalena");
    static Ciudad nogales = new Ciudad("Nogales");
    static Ciudad cananea = new Ciudad("Cananea");
    static Ciudad aguaPrieta = new Ciudad("Agua Prieta");
    static Ciudad puertoPenasco = new Ciudad("Puerto Peñasco");
    static Ciudad sanLuisRC = new Ciudad("San Luis Rio Colorado");
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();

        main.mostrarMenuOpciones();

        // Agregar conexiones (distancias) entre ciudades
        grafo.agregarCiudad(obregon);
        grafo.agregarCiudad(guaymas);
        grafo.agregarCiudad(empalme);
        grafo.agregarCiudad(hermosillo);
        grafo.agregarCiudad(navojoa);
        grafo.agregarCiudad(huatabampo);
        grafo.agregarCiudad(alamos);
        grafo.agregarCiudad(yecora);
        grafo.agregarCiudad(santaAna);
        grafo.agregarCiudad(magdalena);
        grafo.agregarCiudad(nogales);
        grafo.agregarCiudad(cananea);
        grafo.agregarCiudad(aguaPrieta);
        grafo.agregarCiudad(puertoPenasco);
        grafo.agregarCiudad(sanLuisRC);
        grafo.agregarColindancia(obregon, navojoa, 67);
        grafo.agregarColindancia(obregon, empalme, 119);
        grafo.agregarColindancia(empalme, guaymas, 12);
        grafo.agregarColindancia(guaymas, hermosillo, 134);
        grafo.agregarColindancia(hermosillo, santaAna, 171);
        grafo.agregarColindancia(santaAna, magdalena, 18);
        grafo.agregarColindancia(santaAna, puertoPenasco, 284);
        grafo.agregarColindancia(puertoPenasco, sanLuisRC, 238);
        grafo.agregarColindancia(magdalena, nogales, 87);
        grafo.agregarColindancia(nogales, cananea, 96);
        grafo.agregarColindancia(magdalena, cananea, 111);
        grafo.agregarColindancia(cananea, aguaPrieta, 85);
        grafo.agregarColindancia(hermosillo, yecora, 278);
        grafo.agregarColindancia(obregon, yecora, 215);
        grafo.agregarColindancia(navojoa, huatabampo, 37);
        grafo.agregarColindancia(navojoa, alamos, 52);

    }

    private static void mostrarTablaCiudades() {
        ciudades[0] = obregon;
        ciudades[1] = guaymas;
        ciudades[2] = empalme;
        ciudades[3] = hermosillo;
        ciudades[4] = navojoa;
        ciudades[5] = huatabampo;
        ciudades[6] = alamos;
        ciudades[7] = yecora;
        ciudades[8] = santaAna;
        ciudades[9] = magdalena;
        ciudades[10] = nogales;
        ciudades[11] = cananea;
        ciudades[12] = aguaPrieta;
        ciudades[13] = puertoPenasco;
        ciudades[14] = sanLuisRC;

        for (int i = 0; i < ciudades.length; i++) {
            System.out.println((i + 1) + " | " + ciudades[i].getNombre());
            System.out.println("*----------------------------------*");
        }
    }
    
    private void mostrarTablaColindancias() {
        grafo.c
    }

    private void agregarCiudad(int indice) {
        grafo.agregarCiudad(ciudades[indice]);
        System.out.println("Se agrego la ciudad " + ciudades[indice].getNombre());
    }
    
    private void agregarColindancia(int indiceUno, int indiceDos, int distancia) {
        grafo.agregarColindancia(ciudades[indiceUno], ciudades[indiceDos], distancia);
    }

    private void mostrarMenuOpciones() {
        int opcion = 0;
        int opcionSeleccionada = 0;
        
        do {
            System.out.println("*---------------------------------------------------------------------*");
            System.out.println("Bienvenidos al sistema.");
            System.out.println("Elija su opcion.");
            System.out.println("1) Agregar una ciudad");
            System.out.println("2) Registrar una colindancia entre dos ciudades");
            System.out.println("3) Registrar distancia y costo de pasaje entre dos ciudades colindantes");
            System.out.println("4) Modificar distancia y costo de pasaje entre dos ciudades colindantes");
            System.out.println("5) Consultar ruta mas corta entre dos ciudades");
            System.out.println("6) Consultar ruta mas barata entre dos ciudades");
            System.out.println("7) Salir del sistema");
            System.out.println("*---------------------------------------------------------------------*");
            opcionSeleccionada = sc.nextInt();

            switch (opcionSeleccionada) {
                case 1:
                    System.out.println("Agregar una ciudad");
                    System.out.println("Ciudades en lista");
                    mostrarTablaCiudades();
                    System.out.println("Introduzca el numero de una ciudad");
                    opcionSeleccionada = sc.nextInt() - 1;
                    agregarCiudad(opcionSeleccionada);
                    break;
                case 2:
                    System.out.println("Registrar una colindancia entre dos ciudades");
                    mostrarTablaCiudades();
                    System.out.println("Ingrese el indice de la primera ciudad:");
                    int opcionUno = sc.nextInt() - 1;
                    System.out.println("Ingrese el indice de la segunda ciudad:");
                    int opcionDos = sc.nextInt();
                    System.out.println("Ingrese la distancia entre ciudad 1 y ciudad 2:");
                    int distancia = sc.nextInt();
                    agregarColindancia(opcionUno, opcionDos, distancia);
                    break;
                case 3:
                    System.out.println("Registrar distancia y costo de pasaje entre dos ciudades colindantes");
                    break;
                case 4:
                    System.out.println("Registrar distancia y costo de pasaje entre dos ciudades colindantes");
                    break;
                case 5:
                    System.out.println("Consultar ruta mas corta entre dos ciudades ");
                
                
                    break;
                case 6:
                    System.out.println("Consultar ruta mas barata entre dos ciudades");
                    break;
                case 7:
                    System.out.println("Mostrar tabla de ciudades");
                    break;
                case 8:
                    System.out.println("Mostrar tabla de colindancias");
                    break;
                case 9:
                    System.out.println("Salir del sistema");
                    opcion++;
                    break;
                default:
                    System.out.println("Ingresa una opcion valida.");

            }
        } while (opcion != 1);

    }

}
