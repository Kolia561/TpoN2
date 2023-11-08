package com.SalaEstudio;

public class Sala {

    private Integer mesa;

    public Sala(int nuMesas) {
        this.mesa = nuMesas;
    }

    public synchronized boolean entrarSala(){

        boolean entro = false;

        if (mesa > 0) {
            mesa--;
            entro = true;
        }

        return entro;

    }

    public synchronized void esperarSala()throws InterruptedException{
       
        System.out.println("Estudiante " + Thread.currentThread().getName() + " esta esperando");
        this.wait();
    }

    public synchronized void salirSala() {

       mesa++ ;
        System.out.println("Cantidad de mesas: " + mesa);
        this.notifyAll();
    }

}
