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

    private Semaphore platos;
    private Semaphore turnosPerros;
    private Semaphore turnosGatos;

    private int maxTurno;
    private int cantidadPerrosComieron;
    private int cantidadGatosComieron;

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
        this.cantidadPerrosComieron = 0;
        this.cantidadGatosComieron = 0;
        this.perrosComiendo = 0;
        this.gatosComiendo = 0;
        this.perrosEsperando = 0;
        this.gatosEsperando = 0;
    }

    public void perrosComer() throws InterruptedException {

        this.mutex1.acquire();
        if (perrosComiendo == 0 && gatosComiendo == 0 && perrosEsperando == 0 && gatosEsperando == 0) {
            this.turnosPerros.release(maxTurno);

        }
        this.mutex1.release();

        this.mutex1.acquire();
        this.perrosEsperando++;
        this.mutex1.release();

        this.turnosPerros.acquire();

        this.mutex1.acquire();
        this.perrosEsperando--;

        this.platos.acquire(); // TODO: Consultar si este semaforo puede producir un deadlock.

        this.perrosComiendo++;
        this.mutex1.release();

    }

    public void perrosDejarComer() throws InterruptedException {

        this.mutex1.acquire();
        this.perrosComiendo--;
        this.cantidadPerrosComieron++;

        if ((this.perrosComiendo == 0) && // No hay mas perros comiendo...
                ((this.cantidadPerrosComieron == maxTurno) || this.perrosEsperando == 0)){  // Y la cantidad de perros que
            this.mutex1.release();                                                          // comieron es igual a la
            // Consumo los turnos sobrantes.                                               // cantidad de turnos o no hay
            this.turnosPerros.acquire(this.turnosPerros.availablePermits());              // perros esperando
            this.mutex1.acquire();
            this.cantidadPerrosComieron = 0;
            if (this.gatosEsperando > 0) {
                this.mutex1.release();
                this.turnosGatos.release(maxTurno);
            }
            
            

        }

        this.mutex1.release();
        this.platos.release();

    }

    public void gatosComer() throws InterruptedException{
        this.mutex1.acquire();
        if (perrosComiendo == 0 && gatosComiendo == 0 && perrosEsperando == 0 && gatosEsperando == 0) {
            this.turnosGatos.release(maxTurno);

        }
        this.mutex1.release();

        this.mutex1.acquire();
        this.gatosEsperando++;
        this.mutex1.release();

        this.turnosGatos.acquire();

        this.mutex1.acquire();
        this.gatosEsperando--;

        this.platos.acquire(); // TODO: Consultar si este semaforo puede producir un deadlock.

        this.gatosComiendo++;
        this.mutex1.release();
    }


    public void gatosDejarComer() throws InterruptedException{
        this.mutex1.acquire();
        this.gatosComiendo--;
        this.cantidadGatosComieron++;

        if ((this.gatosComiendo == 0) && // No hay mas perros comiendo...
                ((this.cantidadGatosComieron == maxTurno) || this.gatosEsperando == 0)){  // Y la cantidad de perros que
            this.mutex1.release();                                                          // comieron es igual a la
            // Consumo los turnos sobrantes.                                               // cantidad de turnos o no hay
            this.turnosGatos.acquire(this.turnosGatos.availablePermits());              // perros esperando
            this.mutex1.acquire();
            this.cantidadGatosComieron = 0;
            if (this.perrosEsperando > 0) {
                this.mutex1.release();
                this.turnosPerros.release(maxTurno);
            }
            
            

        }

        this.mutex1.release();
        this.platos.release();

    }

}
