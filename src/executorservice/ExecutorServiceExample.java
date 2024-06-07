package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        // Crear un ExecutorService con un pool de 3 hilos
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Crear y enviar tres tareas al ExecutorService
        for (int i = 1; i <= 3; i++) {
            Runnable task = new Task("Task " + i);
            executorService.execute(task);
        }

        // Apagar el ExecutorService
        executorService.shutdown();
    }

    static class Task implements Runnable {
        private String taskName;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " esta ejecutando " + taskName);
            try {
                Thread.sleep(2000); // Simular una tarea que toma tiempo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ha terminado " + taskName);
        }
    }
}
