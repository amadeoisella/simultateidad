package forkjoin;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

// Clase para sumar un arreglo usando RecursiveAction (sin devolver resultado)
class SumArrayAction extends RecursiveAction {
    private final int[] array;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 10;

    public SumArrayAction(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if ((end - start) <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                array[i] += 1;
            }
        } else {
            int middle = (start + end) / 2;
            SumArrayAction leftTask = new SumArrayAction(array, start, middle);
            SumArrayAction rightTask = new SumArrayAction(array, middle, end);
            invokeAll(leftTask, rightTask);
        }
    }
}

// Clase para sumar un arreglo usando RecursiveTask (devolviendo el resultado)
class SumArrayTask extends RecursiveTask<Integer> {
    private final int[] array;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 10;

    public SumArrayTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - start) <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int middle = (start + end) / 2;
            SumArrayTask leftTask = new SumArrayTask(array, start, middle);
            SumArrayTask rightTask = new SumArrayTask(array, middle, end);
            leftTask.fork();
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();
            return leftResult + rightResult;
        }
    }
}

public class ForkJoinExample {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();

        // Usando RecursiveAction
        SumArrayAction action = new SumArrayAction(array,0, array.length);
        pool.invoke(action);

        // Usando RecursiveTask
        SumArrayTask task = new SumArrayTask(array, 0, array.length);
        int sum = pool.invoke(task);

        System.out.println("Suma de elementos del arreglo: " + sum);
    }
}
