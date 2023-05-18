package com.mycompany.grafosproyectofinal;

import com.mycompany.grafosproyectofinal.folder.Ciudad;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {

    private Map<Ciudad, List<Colindancia>> grafo;

    public Graph() {
        grafo = new HashMap<>();
    }

    public void agregarCiudad(Ciudad ciudad) {
        grafo.put(ciudad, new ArrayList<>());
    }

    public void agregarColindancia(Ciudad ciudadOrigen, Ciudad ciudadDestino, int distancia) {
        List<Colindancia> colindancias = grafo.getOrDefault(ciudadOrigen, new ArrayList<>());
        colindancias.add(new Colindancia(ciudadDestino, distancia));
        grafo.put(ciudadOrigen, colindancias);
    }

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

    public List<Ciudad> calcularRutaMasCorta(Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        Map<Ciudad, Integer> distancias = new HashMap<>();
        Map<Ciudad, Ciudad> rutasPrevias = new HashMap<>();
        PriorityQueue<Colindancia> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(Colindancia::getDistancia));

        // Inicializar distancias
        for (Ciudad ciudad : grafo.keySet()) {
            if (ciudad.equals(ciudadOrigen)) {
                distancias.put(ciudad, 0);
            } else {
                distancias.put(ciudad, Integer.MAX_VALUE);
            }
        }

        colaPrioridad.offer(new Colindancia(ciudadOrigen, 0));

        while (!colaPrioridad.isEmpty()) {
            Colindancia colindanciaActual = colaPrioridad.poll();
            Ciudad ciudadActual = colindanciaActual.getCiudadDestino();

            if (distancias.get(ciudadActual) < colindanciaActual.getDistancia()) {
                continue;
            }

            List<Colindancia> colindancias = grafo.get(ciudadActual);
            if (colindancias != null) {
                for (Colindancia colindancia : colindancias) {
                    int distanciaNueva = distancias.get(ciudadActual) + colindancia.getDistancia();
                    if (distanciaNueva < distancias.get(colindancia.getCiudadDestino())) {
                        distancias.put(colindancia.getCiudadDestino(), distanciaNueva);
                        rutasPrevias.put(colindancia.getCiudadDestino(), ciudadActual);
                        colaPrioridad.offer(new Colindancia(colindancia.getCiudadDestino(), distanciaNueva));
                    }
                }
            }
        }

        List<Ciudad> rutaMasCorta = new ArrayList<>();
        Ciudad ciudadActual = ciudadDestino;

        while (ciudadActual != null) {
            rutaMasCorta.add(0, ciudadActual);
            ciudadActual = rutasPrevias.get(ciudadActual);
        }

        return rutaMasCorta;
    }

    public List<Colindancia> obtenerColindanciasDeCiudades() {
        List<Colindancia> listaColindancias = new ArrayList<>();

        for (List<Colindancia> colindancias : grafo.values()) {
            listaColindancias.addAll(colindancias);
        }

        return listaColindancias;
    }

    private static class Colindancia {

        private Ciudad ciudadDestino;
        private int distancia;

        public Colindancia(Ciudad ciudadDestino, int distancia) {
            this.ciudadDestino = ciudadDestino;
            this.distancia = distancia;
        }

        public Ciudad getCiudadDestino() {
            return ciudadDestino;
        }

        public int getDistancia() {
            return distancia;
        }
    }
}
