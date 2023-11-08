package com.SalaEstudio;

public class SalaDeEstudio {

    public static void main(String[] args) {

        Sala sala = new Sala(3);
        
        Thread hilos[] = new Thread[6];

        for (int i = 0; i < 6; i++) {

            hilos[i] = new Thread(new Estudiante(sala), "Estudiante " + i);

        }


        for (int j = 0; j < hilos.length; j++) {
            hilos[j].start();
        }

    }
}
