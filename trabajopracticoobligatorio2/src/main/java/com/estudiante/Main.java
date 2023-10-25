package com.estudiante;

public class Main {
    public static void main(String[] args) {
        
        Comedor comedor = new Comedor();
        int CANTGATOS = 20; 
        int CANTPERROS = 10;
        
        Thread hilos[] = new Thread[CANTGATOS+CANTPERROS];

        for (int i = 0; i < CANTPERROS; i++) {

            hilos[i]= new Thread(new Animal(comedor,true),"Perro "+i);
            
        }

        for (int i = CANTPERROS; i < CANTPERROS+CANTGATOS; i++) {

            hilos[i]= new Thread(new Animal(comedor,false),"Gato "+(i-CANTPERROS));
            
        }

        for (int j = 0; j < hilos.length; j++) {
            hilos[j].start();
        }
    }
}