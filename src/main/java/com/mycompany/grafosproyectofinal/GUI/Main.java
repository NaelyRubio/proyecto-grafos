/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafosproyectofinal.GUI;

import com.mycompany.grafosproyectofinal.Graph;
import com.mycompany.grafosproyectofinal.folder.Ciudad;
import java.util.List;
import java.util.Scanner;

/**
 * @author Guimel Naely Rubio Morillon - 229324
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

    private void agregarCiudad(int indice) {
        grafo.agregarCiudad(ciudades[indice]);
        System.out.println("Se agrego la ciudad " + ciudades[indice].getNombre());
    }

    
    private void agregarColindancia(int indiceUno, int indiceDos, int distancia) {
        grafo.agregarColindancia(ciudades[indiceUno], ciudades[indiceDos], distancia);
    }

    private List<Ciudad> calcularDistanciaMasCorta(int indiceUno, int indiceDos) {
        return grafo.calcularRutaMasCortaBellmanFord(ciudades[indiceUno], ciudades[indiceDos]);
    }
    
    private List<Ciudad> calcularDistanciaMasBarata(int indiceUno, int indiceDos) {
        return grafo.calcularRutaMasBarataBellmanFord(ciudades[indiceUno], ciudades[indiceDos]);
    }

    private void mostrarMenuOpciones() {
        int opcion = 0;
        int opcionSeleccionada = 0;
        int opcionUno = 0, opcionDos = 0;

        do {
            System.out.println("*---------------------------------------------------------------------*");
            System.out.println("Bienvenidos al sistema.");
            System.out.println("1) Agregar una ciudad");
            System.out.println("2) Registrar una colindancia entre dos ciudades");
            System.out.println("3) Registrar distancia y costo de pasaje entre dos ciudades colindantes");
            System.out.println("4) Modificar distancia y costo de pasaje entre dos ciudades colindantes");
            System.out.println("5) Consultar ruta mas corta entre dos ciudades");
            System.out.println("6) Consultar ruta mas barata entre dos ciudades");
            System.out.println("7) Salir del sistema");
            System.out.println("Ingrese su opcion");
            System.out.println("*---------------------------------------------------------------------*");
            opcionSeleccionada = sc.nextInt();

            switch (opcionSeleccionada) {
                case 1:
                    System.out.println("1) Agregar una ciudad");
                    System.out.println("Ciudades en lista");
                    mostrarTablaCiudades();
                    System.out.println("Introduzca el numero de una ciudad");
                    opcionSeleccionada = sc.nextInt() - 1;
                    agregarCiudad(opcionSeleccionada);
                    break;
                case 2:
                    System.out.println("2) Registrar una colindancia entre dos ciudades");
                    mostrarTablaCiudades();
                    System.out.println("Ingrese el indice de la primera ciudad:");
                    opcionUno = sc.nextInt() - 1;
                    System.out.println("Ingrese el indice de la segunda ciudad:");
                    opcionDos = sc.nextInt();
                    System.out.println("Ingrese la distancia entre ciudad 1 y ciudad 2:");
                    int distancia = sc.nextInt();
                    agregarColindancia(opcionUno, opcionDos, distancia);
                    break;
                case 3:
                    System.out.println("3) Registrar distancia y costo de pasaje entre dos ciudades colindantes");
                    break;
                case 4:
                    System.out.println("4) Modificar distancia y costo de pasaje entre dos ciudades colindantes");
                    break;
                case 5:
                    System.out.println("5) Consultar ruta mas corta entre dos ciudades");
                    System.out.println("Ingrese el indice de la ciudad 1.");
                    opcionUno = sc.nextInt();
                    System.out.println("Ingrese el indice de la ciudad 2.");
                    opcionDos = sc.nextInt();
                    List<Ciudad> rutaMasCorta = calcularDistanciaMasCorta(opcionUno, opcionDos);
                    for (Ciudad ciudad : rutaMasCorta) {
                        System.out.println(ciudad.getNombre());
                    }
            
                    break;
                case 6:
                    System.out.println("6) Consultar ruta mas barata entre dos ciudades");
                    System.out.println("Ingrese el indice de la ciudad 1.");
                    opcionUno = sc.nextInt();
                    System.out.println("Ingrese el indice de la ciudad 2.");
                    opcionDos = sc.nextInt();
                    List<Ciudad> rutaMasBarata = calcularDistanciaMasBarata(opcionUno, opcionDos);
                    for (Ciudad ciudad : rutaMasBarata) {
                        System.out.println(ciudad.getNombre());
                    }
                    break;
                case 7:
                    System.out.println("7) Salir del sistema");
                    opcion++;
                    break;
                default:
                    System.out.println("Ingresa una opcion valida.");

            }
        } while (opcion != 1);

    }

}
