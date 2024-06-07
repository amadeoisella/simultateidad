package sincronizadores;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        // Crear y empezar dos hilos
        Thread t1 = new Thread(new Task(exchanger, "Mensaje de Worker 1"), "Worker 1");
        Thread t2 = new Thread(new Task(exchanger, "Mensaje de Worker 2"), "Worker 2");

        t1.start();
        t2.start();
    }

    static class Task implements Runnable {
        private Exchanger<String> exchanger;
        private String message;

        public Task(Exchanger<String> exchanger, String message) {
            this.exchanger = exchanger;
            this.message = message;
        }

        @Override
        public void run() {
            try {
                // Intercambiar mensajes
                System.out.println(Thread.currentThread().getName() + " está intercambiando: " + message);
                String receivedMessage = exchanger.exchange(message);
                System.out.println(Thread.currentThread().getName() + " recibió: " + receivedMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
