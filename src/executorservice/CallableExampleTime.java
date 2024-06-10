package executorservice;

import java.util.concurrent.*;

public class CallableExampleTime {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Crear un Callable que simula una tarea larga
        Callable<String> longRunningTask = () -> {
            // Simular tarea que dura 5 segundos
            Thread.sleep(5000);
            return "Tarea completada";
        };

        // Enviar la tarea al executorService
        Future<String> future = executorService.submit(longRunningTask);

        try {
            // Intentar obtener el resultado con un timeout de 2 segundos
            String result = future.get(3, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (TimeoutException e) {
            System.out.println("TimeoutException: La tarea tomó más tiempo del esperado. Accion cancelada");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
