package com.estudiante;

/**
 *
 * @author Kolia
 */
public class Animal implements Runnable{

    private Comedor comedor;
    private boolean tipo; //true=perro;false=gato

    public Animal (Comedor come, boolean t){

        this.comedor = come;
        this.tipo = t;

    }

    public void run(){

        while (true) {

            if(comedor.puedoComer(tipo)){

                comedor.comer(tipo);
                
                Thread.sleep(3000);

                comedor.terminarComer(tipo);

            }else{

                comedor.esperarTurno(tipo);

            }

            
        }

    }

    
}