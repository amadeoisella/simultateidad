package sincronizadores;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        // Crear un CyclicBarrier para 3 hilos con una tarea de barrera
        CyclicBarrier barrier = new CyclicBarrier(3, new BarrierAction());

        // Crear y empezar tres hilos
        Thread t1 = new Thread(new Task(barrier), "Worker 1");
        Thread t2 = new Thread(new Task(barrier), "Worker 2");
        Thread t3 = new Thread(new Task(barrier), "Worker 3");

        t1.start();
        t2.start();
        t3.start();
    }

    static class Task implements Runnable {
        private CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                // Simular una tarea
                System.out.println(Thread.currentThread().getName() + " está trabajando");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " ha terminado su tarea");

                // Esperar a que otros hilos lleguen a la barrera
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " ha cruzado la barrera");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class BarrierAction implements Runnable {
        @Override
        public void run() {
            System.out.println("Todos los hilos han alcanzado la barrera. Continuando...");
        }
    }
}
