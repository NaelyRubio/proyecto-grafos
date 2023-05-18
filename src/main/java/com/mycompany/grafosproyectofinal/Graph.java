package com.mycompany.grafosproyectofinal;

import com.mycompany.grafosproyectofinal.folder.Ciudad;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa un grafo que costa de ciudades y sus cardinalidades.
 *
 * @author Guimel Naely Rubio Morillon - 229324
 */
public class Graph {

    //Mapa que mapea instancias de la clase ciudad a las listas de colindancias
    private Map<Ciudad, List<Colindancia>> grafo;

    //Constructor graph que inicializa el mapa grafo como una nueva instancoa HashMap
    public Graph() {
        grafo = new HashMap<>();
    }

    //Metodo agregarCiudad que toma como instancia la clase ciudad como parametro y agrega una entrada al mapa grafo
    public void agregarCiudad(Ciudad ciudad) {
        grafo.put(ciudad, new ArrayList<>());
    }

    //Mwrodo agregar colindancia que permite agregar una colindancia entre dos ciudades existenres el grafo
    public void agregarColindancia(Ciudad ciudadOrigen, Ciudad ciudadDestino, int distancia) {
        List<Colindancia> colindancias = grafo.getOrDefault(ciudadOrigen, new ArrayList<>());
        colindancias.add(new Colindancia(ciudadDestino, distancia));
        grafo.put(ciudadOrigen, colindancias);
    }

    //Metodo obtener distancia que permite obteher la distancia entre dos ciudades en el grafo. 
    public int obtenerDistancia(Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        List<Colindancia> colindancias = grafo.get(ciudadOrigen);
        if (colindancias != null) {
            for (Colindancia colindancia : colindancias) {
                if (colindancia.getCiudadDestino().equals(ciudadDestino)) {
                    return colindancia.getDistancia();
                }
            }
        }
        return -1; // Si no hay conexión directa entre las ciudades
    }

    public List<Ciudad> calcularRutaMasCortaBellmanFord(Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        Map<Ciudad, Integer> distancias = new HashMap<>();
        Map<Ciudad, Ciudad> rutasPrevias = new HashMap<>();

        // Inicializar distancias
        for (Ciudad ciudad : grafo.keySet()) {
            if (ciudad.equals(ciudadOrigen)) {
                distancias.put(ciudad, 0);
            } else {
                distancias.put(ciudad, Integer.MAX_VALUE);
            }
        }

        // Relajación de aristas
        for (int i = 0; i < grafo.size() - 1; i++) {
            for (Map.Entry<Ciudad, List<Colindancia>> entry : grafo.entrySet()) {
                Ciudad ciudad = entry.getKey();
                List<Colindancia> colindancias = entry.getValue();
                if (colindancias != null) {
                    for (Colindancia colindancia : colindancias) {
                        ciudadDestino = colindancia.getCiudadDestino();
                        Integer distance = distancias.get(ciudad);
                        Integer destinationDistance = distancias.get(ciudadDestino);
                        if (distance != null && destinationDistance != null) {
                            int distanciaNueva = distance.intValue() + colindancia.getDistancia();
                            if (distanciaNueva < destinationDistance.intValue()) {
                                distancias.put(ciudadDestino, distanciaNueva);
                                rutasPrevias.put(ciudadDestino, ciudad);
                            }
                        }
                    }
                }
            }
        }

        // Detectar ciclos negativos
        for (Map.Entry<Ciudad, List<Colindancia>> entry : grafo.entrySet()) {
            Ciudad ciudad = entry.getKey();
            List<Colindancia> colindancias = entry.getValue();
            if (colindancias != null) {
                for (Colindancia colindancia : colindancias) {
                    ciudadDestino = colindancia.getCiudadDestino();
                    Integer distance = distancias.get(ciudad);
                    Integer destinationDistance = distancias.get(ciudadDestino);
                    if (distance != null && destinationDistance != null) {
                        int distanciaNueva = distance.intValue() + colindancia.getDistancia();
                        if (distanciaNueva < destinationDistance.intValue()) {
                            throw new RuntimeException("El grafo contiene un ciclo negativo.");
                        }
                    }
                }
            }
        }

        // Construir la ruta más corta
        List<Ciudad> rutaMasCorta = new ArrayList<>();
        Ciudad ciudadActual = ciudadDestino;

        while (ciudadActual != null) {
            rutaMasCorta.add(0, ciudadActual);
            ciudadActual = rutasPrevias.get(ciudadActual);
        }

        return rutaMasCorta;
    }

    public List<Ciudad> calcularRutaMasBarataBellmanFord(Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        Map<Ciudad, Integer> costos = new HashMap<>();
        Map<Ciudad, Ciudad> rutasPrevias = new HashMap<>();

        // Inicializar costos
        for (Ciudad ciudad : grafo.keySet()) {
            if (ciudad.equals(ciudadOrigen)) {
                costos.put(ciudad, 0);
            } else {
                costos.put(ciudad, Integer.MAX_VALUE);
            }
        }

        // Relajación de aristas
        for (int i = 0; i < grafo.size() - 1; i++) {
            for (Map.Entry<Ciudad, List<Colindancia>> entry : grafo.entrySet()) {
                Ciudad ciudad = entry.getKey();
                List<Colindancia> colindancias = entry.getValue();
                if (colindancias != null) {
                    for (Colindancia colindancia : colindancias) {
                        ciudadDestino = colindancia.getCiudadDestino();
                        Integer costoActual = costos.get(ciudad);
                        Integer costoDestino = costos.get(ciudadDestino);
                        if (costoActual != null && costoDestino != null) {
                            int costoNuevo = costoActual.intValue() + colindancia.getCosto();
                            if (costoNuevo < costoDestino.intValue()) {
                                costos.put(ciudadDestino, costoNuevo);
                                rutasPrevias.put(ciudadDestino, ciudad);
                            }
                        }
                    }
                }
            }
        }

        // Construir la ruta más barata
        List<Ciudad> rutaMasBarata = new ArrayList<>();
        Ciudad ciudadActual = ciudadDestino;

        while (ciudadActual != null) {
            rutaMasBarata.add(0, ciudadActual);
            ciudadActual = rutasPrevias.get(ciudadActual);
        }

        return rutaMasBarata;
    }

    //Metodo obtenerColindanciaDeCiudades devuelve una lista de objetos colindancia. 
    public List<Colindancia> obtenerColindanciasDeCiudades() {
        List<Colindancia> listaColindancias = new ArrayList<>();

        for (List<Colindancia> colindancias : grafo.values()) {
            listaColindancias.addAll(colindancias);
        }

        return listaColindancias;
    }

    //clase interna colindancia.
    private class Colindancia {

        //Atributos privados de la clase
        private Ciudad ciudadDestino;
        private int distancia;
        private int costo;

        //Constructor que recibe una instancia ciudad, la distancia y el costo y 
        //asigna valores a los atributos correspondientes.
        public Colindancia(Ciudad ciudadDestino, int distancia, int costo) {
            this.ciudadDestino = ciudadDestino;
            this.distancia = distancia;
            this.costo = costo;
        }

        public Colindancia(Ciudad ciudadDestino, int distancia) {
            this.ciudadDestino = ciudadDestino;
            this.distancia = distancia;
        }

        /**
         * Metodo para obetener distancia
         *
         * @return ciudadDestino
         */
        public Ciudad getCiudadDestino() {
            return ciudadDestino;
        }

        /**
         * Metodo para obtener distancia
         *
         * @return disancia
         */
        public int getDistancia() {
            return distancia;
        }

        /**
         * metodo para obtener el costo
         *
         * @return costo
         */
        public int getCosto() {
            return costo;
        }
    }

}
