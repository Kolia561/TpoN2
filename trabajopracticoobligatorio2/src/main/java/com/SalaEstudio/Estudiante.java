package com.SalaEstudio;

public class Estudiante implements Runnable {

    private Sala _sala;

    public Estudiante(Sala s) {
        this._sala = s;

    }

    @Override
    public void run() {

        int i = 0;
        while (i < 2) {
            try {

                if (!this._sala.entrarSala()) {
                    this._sala.esperarSala();
                } else {
                    System.out.println("Estudiante " + Thread.currentThread().getName() + " esta estudiando");
                    Thread.sleep((long) (Math.random() * 2500));
                    this._sala.salirSala();
                    System.out.println("Estudiante " + Thread.currentThread().getName() + " termino de estudiar");
                    Thread.sleep((long) (Math.random() * 2500));
                    i++;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

}
