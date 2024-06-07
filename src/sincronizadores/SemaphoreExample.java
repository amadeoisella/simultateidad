package sincronizadores;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    // Crear un semeforo con 1 permiso (bloqueo binario)
    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        // Crear y empezar dos hilos
        Thread t1 = new Thread(new Task(), "Thread 1");
        Thread t2 = new Thread(new Task(), "Thread 2");

        t1.start();
        t2.start();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                // Adquirir el permiso
                System.out.println(Thread.currentThread().getName() + " esperando el permiso");
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " obtuvo el permiso");

                // Simular una tarea
                Thread.sleep(2000);

                System.out.println(Thread.currentThread().getName() + " libera el permiso");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                // Liberar el permiso
                semaphore.release();
            }
        }
    }
}
