package com.estudiante;

import java.util.logging.Level;
import java.util.logging.Logger;

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

            if (tipo) {

                try {

                    comedor.perrosComer();
                    System.out.println( Thread.currentThread().getName() + " esta comiendo" );
                    Thread.currentThread().sleep(Math.round(Math.random() * 1000));
                    System.out.println( Thread.currentThread().getName() + " termino de comer" );
                    comedor.perrosDejarComer();

                } catch (InterruptedException ex) {

                    Logger.getLogger(Animal.class.getName()).log(Level.SEVERE, null, ex);

                }

            } else {

                try {

                    comedor.gatosComer();
                    System.out.println( Thread.currentThread().getName() + " esta comiendo" );
                    Thread.currentThread().sleep(Math.round(Math.random() * 1000));
                    System.out.println( Thread.currentThread().getName() + " termino de comer" );
                    comedor.gatosDejarComer();

                } catch (InterruptedException ex) {

                    Logger.getLogger(Animal.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

            
        }

    }

    
}