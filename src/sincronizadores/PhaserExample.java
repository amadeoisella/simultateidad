package sincronizadores;

import java.util.concurrent.Phaser;

public class PhaserExample {
    public static void main(String[] args) {
        // Crear un Phaser con 1 registro (el hilo principal)
        Phaser phaser = new Phaser(1);

        // Crear y empezar tres hilos
        for (int i = 0; i <+ 3; i++) {
            phaser.register(); // Registrar cada nuevo hilo en el phaser
            new Thread(new Task(phaser), "Worker " + i).start();
        }

        // Esperar a que todos los hilos terminen la fase 1
        phaser.arriveAndAwaitAdvance();
        System.out.println("Todos los trabajadores han terminado la fase 1. Continuando con la fase 2...");

        // Esperar a que todos los hilos terminen la fase 2
        phaser.arriveAndAwaitAdvance();
        System.out.println("Todos los trabajadores han terminado la fase 2. Continuando con la fase 3...");

        // Finalizar el phaser
        phaser.arriveAndDeregister();
        System.out.println("Todos los trabajadores han terminado. Finalizando...");
    }

    static class Task implements Runnable {
        private Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }


        @Override
        public void run() {
            // Fase 1
            System.out.println(Thread.currentThread().getName() + " está trabajando en la fase 1");
            try {
                Thread.sleep(1000); // Simular trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance(); // Llegar y esperar a que otros hilos terminen la fase 1

            // Fase 2
            System.out.println(Thread.currentThread().getName() + " está trabajando en la fase 2");
            try {
                Thread.sleep(2000); // Simular trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance(); // Llegar y esperar a que otros hilos terminen la fase 2

            // Fase 3
            System.out.println(Thread.currentThread().getName() + " está trabajando en la fase 3");
            try {
                Thread.sleep(3000); // Simular trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndDeregister(); // Llegar y desregistrarse del phaser
        }
    }
}
