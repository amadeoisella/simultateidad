package sincronizadores;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) {
        // Crear un CountDownLatch con el contador inicial de 3
        CountDownLatch latch = new CountDownLatch(3);

        // Crear y empezar tres hilos
        Thread t1 = new Thread(new Worker(latch), "Worker 1");
        Thread t2 = new Thread(new Worker(latch), "Worker 2");
        Thread t3 = new Thread(new Worker(latch), "Worker 3");

        t1.start();
        t2.start();
        t3.start();

        try {
            latch.await();
            System.out.println("Todos los trabajadores han terminado. Continuando con la tarea principal.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Worker implements Runnable {
        private CountDownLatch latch;

        public Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // Simular una tarea
                System.out.println(Thread.currentThread().getName() + " está trabajando");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " ha terminado");

                // Disminuir el contador del CountDownLatch
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
