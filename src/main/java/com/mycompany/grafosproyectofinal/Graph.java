package com.mycompany.grafosproyectofinal;

import com.mycompany.grafosproyectofinal.folder.Ciudad;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Ciudad, List<Colindancia>> grafo;

    public Graph() {
        grafo = new HashMap<>();
    }

    public void agregarCiudad(Ciudad ciudad) {
        grafo.put(ciudad, new ArrayList<>());
    }

    public void agregarColindancia(Ciudad ciudadOrigen, Ciudad ciudadDestino, int distancia, int costo) {
        List<Colindancia> colindancias = grafo.getOrDefault(ciudadOrigen, new ArrayList<>());
        colindancias.add(new Colindancia(ciudadDestino, distancia, costo));
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
                        int distanciaNueva = distancias.get(ciudad) + colindancia.getDistancia();
                        if (distanciaNueva < distancias.get(ciudadDestino)) {
                            distancias.put(ciudadDestino, distanciaNueva);
                            rutasPrevias.put(ciudadDestino, ciudad);
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
                    int distanciaNueva = distancias.get(ciudad) + colindancia.getDistancia();
                    if (distanciaNueva < distancias.get(ciudadDestino)) {
                        throw new RuntimeException("El grafo contiene un ciclo negativo.");
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

    public List<Colindancia> obtenerColindanciasDeCiudades() {
        List<Colindancia> listaColindancias = new ArrayList<>();

        for (List<Colindancia> colindancias : grafo.values()) {
            listaColindancias.addAll(colindancias);
        }

        return listaColindancias;
    }

    private class Colindancia {

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

}
