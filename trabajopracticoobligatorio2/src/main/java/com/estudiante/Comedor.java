package com.estudiante;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kolia
 */
public class Comedor {

    private final int N = 3;

    private final int YACOMIO = 20;

    private Semaphore platos;
    private Semaphore turnosPerros;
    private Semaphore turnosGatos;

    private int maxTurno;
    private int perrosComiendo;
    private int gatosComiendo;
    private int perrosEsperando;
    private int gatosEsperando;

    private Semaphore mutex1;

    public Comedor() {

        this.platos = new Semaphore(N);
        this.mutex1 = new Semaphore(1);
        this.turnosGatos = new Semaphore(0);
        this.turnosPerros = new Semaphore(0);

        this.maxTurno = 9;
        this.perrosComiendo = 0;
        this.gatosComiendo = 0;
        this.perrosEsperando = 0;
        this.gatosEsperando = 0;
    }

    public void perrosComer() throws InterruptedException {

        if (perrosComiendo ==0 && gatosComiendo==0 && perrosEsperando==0 && gatosEsperando ==0 ) {

            
            
        }

    }

}