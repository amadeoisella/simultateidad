package executorservice;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) {
        // Crear un ExecutorService con un pool de un solo hilo
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Crear una instancia de Callable
        Callable<String> callableTask = new CallableTask();

        // Enviar la tarea callable al ExecutorService
        Future<String> future = executorService.submit(callableTask);

        try {
            // Obtener el resultado de la tarea callable
            String result = future.get();
            System.out.println("Resultado de la tarea callable: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Apagar el ExecutorService
        executorService.shutdown();
    }

    static class CallableTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(2000); // Simular una tarea que toma tiempo
            return "Tarea completada!";
        }
    }
}
