package com.mycompany.grafosproyectofinal;

import com.mycompany.grafosproyectofinal.folder.Ciudad;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author naely
 */
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


        public List<Ciudad> consultarRutaMasCorta(Ciudad origen, Ciudad destino) {
            Map<Ciudad, Integer> distancias = new HashMap<>();
            Map<Ciudad, Ciudad> predecesores = new HashMap<>();
            Set<Ciudad> visitados = new HashSet<>();

            // Inicializar distancias con valor infinito excepto para el origen
            for (Ciudad ciudad : visitados) {
                distancias.put(ciudad, Integer.MAX_VALUE);
            }
            distancias.put(origen, 0);

            while (!visitados.contains(destino)) {
                Ciudad ciudadActual = obtenerCiudadMenorDistancia(distancias, visitados);
                visitados.add(ciudadActual);

                for (Ciudad colindante : ciudadActual.getColindancias()) {
                    if (!visitados.contains(colindante)) {
                        int nuevaDistancia = distancias.get(ciudadActual) + obtenerDistancia(ciudadActual, colindante);
                        if (nuevaDistancia < distancias.get(colindante)) {
                            distancias.put(colindante, nuevaDistancia);
                            predecesores.put(colindante, ciudadActual);
                        }
                    }
                }
            }

            List<Ciudad> ruta = construirRuta(origen, destino, predecesores);
            if (ruta != null) {
                int distanciaTotal = distancias.get(destino);
                int costoTotal = calcularCostoPeaje(ruta);
                return new Ruta(ruta, distanciaTotal, costoTotal);
            } else {
                return null;
            }
        }

        private Ciudad obtenerCiudadMenorDistancia(Map<Ciudad, Integer> distancias, Set<Ciudad> visitados) {
            int menorDistancia = Integer.MAX_VALUE;
            Ciudad ciudadMenorDistancia = null;
            for (Map.Entry<Ciudad, Integer> entry : distancias.entrySet()) {
                Ciudad ciudad = entry.getKey();
                int distancia = entry.getValue();
                if (distancia < menorDistancia && !visitados.contains(ciudad)) {
                    menorDistancia = distancia;
                    ciudadMenorDistancia = ciudad;
                }
            }
            return ciudadMenorDistancia;
        }

       

        private List<Ciudad> construirRuta(Ciudad origen, Ciudad destino, Map<Ciudad, Ciudad> predecesores) {
            List<Ciudad> ruta = new ArrayList<>();
            Ciudad ciudad = destino;
            while (ciudad != null) {
                ruta.add(0, ciudad);
                ciudad = predecesores.get(ciudad);
            }
            if (ruta.get(0) == origen) {
                return ruta;
            } else {
                return null; // No se encontró una ruta válida
            }
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
