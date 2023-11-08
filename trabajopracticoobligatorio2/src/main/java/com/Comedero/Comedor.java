package com.Comedero;

import java.util.concurrent.Semaphore;

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
    private boolean turno;

    private Semaphore mutex;

    public Comedor() {

        this.platos = new Semaphore(N);
        this.mutex = new Semaphore(1);

        this.turnosGatos = new Semaphore(0);
        this.turnosPerros = new Semaphore(0);

        this.maxTurno = 9;
        this.cantidadPerrosComieron = 0;
        this.cantidadGatosComieron = 0;
        this.perrosComiendo = 0;
        this.gatosComiendo = 0;
        this.perrosEsperando = 0;
        this.gatosEsperando = 0;
        this.turno=true;

    }

    public void perrosComer() throws InterruptedException {

        this.mutex.acquire();
        if (perrosComiendo == 0 && gatosComiendo == 0 && perrosEsperando == 0 && gatosEsperando == 0 && turno) {
            this.turnosPerros.release(maxTurno);
            this.turno=false;

        }
        this.mutex.release();

        this.mutex.acquire();
        this.perrosEsperando++;
        this.mutex.release();

        this.turnosPerros.acquire();

        this.mutex.acquire();
        this.perrosEsperando--;
        this.mutex.release();

        this.platos.acquire(); // TODO: Consultar si este semaforo puede producir un deadlock.

        this.mutex.acquire();
        this.perrosComiendo++;
        this.mutex.release();

    }

    public void perrosDejarComer() throws InterruptedException {

        this.mutex.acquire();
        this.perrosComiendo--;
        this.cantidadPerrosComieron++;

        this.mutex.release();
        this.platos.release();

        this.mutex.acquire();
        if (this.perrosComiendo == 0){// No hay mas perros comiendo...

            if(this.cantidadPerrosComieron == maxTurno){
            
                this.cantidadPerrosComieron = 0;
            
                if (this.gatosEsperando > 0) {
                    this.mutex.release();
                    this.turnosGatos.release(maxTurno);
                }else{
                    if(this.perrosEsperando==0){
                        this.mutex.release();
                        this.turno=true;
                    }else{
                        this.mutex.release();
                        this.turnosPerros.release(maxTurno);
                    }
                    
                }

            }else{

                if(perrosEsperando==0){

                    this.turnosPerros.acquire(maxTurno-cantidadPerrosComieron);
                    this.cantidadPerrosComieron = 0;
            
                    if (this.gatosEsperando > 0) {
                        this.mutex.release();
                        this.turnosGatos.release(maxTurno);
                    }else{
                        if(this.perrosEsperando==0){
                            this.mutex.release();
                            this.turno=true;
                        }else{
                            this.mutex.release();
                            this.turnosPerros.release(maxTurno);
                        }
                    } 

                }

            }         
            
            

        }else{
            this.mutex.release();
        }



    }

    

    public void gatosComer() throws InterruptedException{
        this.mutex.acquire();
        if (perrosComiendo == 0 && gatosComiendo == 0 && perrosEsperando == 0 && gatosEsperando == 0 && turno) {
            this.turnosGatos.release(maxTurno);
            this.turno=false;

        }
        this.mutex.release();

        this.mutex.acquire();
        this.gatosEsperando++;
        this.mutex.release();

        this.turnosGatos.acquire();

        this.mutex.acquire();
        this.gatosEsperando--;
        this.mutex.release();

        this.platos.acquire(); // TODO: Consultar si este semaforo puede producir un deadlock.

        this.mutex.acquire();
        this.gatosComiendo++;
        this.mutex.release();
    }


    public void gatosDejarComer() throws InterruptedException{
        this.mutex.acquire();
        this.gatosComiendo--;
        this.cantidadGatosComieron++;

        this.mutex.release();
        this.platos.release();

        this.mutex.acquire();

        if (this.gatosComiendo == 0){// No hay mas perros comiendo...

            if(this.cantidadGatosComieron == maxTurno){
            
                this.cantidadGatosComieron = 0;
            
                if (this.perrosEsperando > 0) {
                    this.mutex.release();
                    this.turnosPerros.release(maxTurno);
                }else{

                    if (this.gatosEsperando==0) {
                        this.mutex.release();
                        this.turno=true;

                    }else{
                        this.mutex.release();
                        this.turnosGatos.release(maxTurno);
                    }
                    
                   
                }

            }else{

                if(gatosEsperando==0){

                    this.turnosGatos.acquire(maxTurno-cantidadGatosComieron);
                    this.cantidadGatosComieron = 0;
            
                    if (this.perrosEsperando > 0) {
                        this.mutex.release();
                        this.turnosPerros.release(maxTurno);
                    }else{
                        if (this.gatosEsperando==0) {
                            this.mutex.release();
                            this.turno=true;

                        }else{
                            this.mutex.release();
                            this.turnosGatos.release(maxTurno);
                        }
                    } 

                }

            }         
            
            

        }else{
            this.mutex.release();
        }

        

    }

}
