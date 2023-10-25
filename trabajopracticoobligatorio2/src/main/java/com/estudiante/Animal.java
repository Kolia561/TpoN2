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

        int j=0;
        int i=0;
        

            if (tipo) {

                while(i<5){
                try {

                    
                    comedor.perrosComer();
                    System.out.println( Thread.currentThread().getName() + " esta comiendo" );
                    
                    Thread.sleep(Math.round(Math.random() * 1000));
                    System.out.println( Thread.currentThread().getName() + " termino de comer" );
                    comedor.perrosDejarComer();

                    System.out.println("perro vuelta "+i);

                    i++;

                } catch (InterruptedException ex) {

                    Logger.getLogger(Animal.class.getName()).log(Level.SEVERE, null, ex);

                }
            }

            } else {
                while(j<3){

                try {

                    comedor.gatosComer();
                    System.out.println( Thread.currentThread().getName() + " esta comiendo" );
                    Thread.sleep(Math.round(Math.random() * 1000));
                    System.out.println( Thread.currentThread().getName() + " termino de comer" );
                    comedor.gatosDejarComer();
                    //System.out.println("gato vuelta "+j);
                    j++;
                } catch (InterruptedException ex) {

                    Logger.getLogger(Animal.class.getName()).log(Level.SEVERE, null, ex);

                }
                }
        }

            
        

    }

    
}