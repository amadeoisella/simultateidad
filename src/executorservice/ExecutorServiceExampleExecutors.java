package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExampleExecutors {
    public static void main(String[] args) {
        // Obtener el número de procesadores disponibles
        int cpuCount = Runtime.getRuntime().availableProcessors();
        System.out.println("Número de procesadores disponibles: " + cpuCount);

        // Crear un ExecutorService con un pool de hilos igual al número de procesadores
        ExecutorService executorService = Executors.newFixedThreadPool(cpuCount);

        // Crear y enviar tantas tareas como procesadores disponibles al ExecutorService
        for (int i = 1; i <= cpuCount; i++) {
            Runnable task = new Task("Task" + i);
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
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ha terminado " + taskName);
        }
    }
}
