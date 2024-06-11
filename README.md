# Simultaneidad en Java

---

## Sincronizadores del Paquete java.util.concurrent

El paquete `java.util.concurrent` proporciona varias clases de sincronización que facilitan la coordinación entre hilos en aplicaciones concurrentes. A continuación se describen algunas de las principales herramientas de sincronización disponibles en este paquete:

### Semaphore

`Semaphore` es una herramienta de concurrencia clásica que mantiene un conjunto de permisos. Los hilos intentan adquirir permisos y pueden bloquearse hasta que los permisos sean liberados por otros hilos. Es útil para controlar el acceso a recursos limitados.

### CountDownLatch

`CountDownLatch` es una utilidad simple y común que permite a uno o más hilos esperar (bloquearse) hasta que un número determinado de señales, eventos o condiciones hayan ocurrido. Una vez que la cuenta regresiva llega a cero, todos los hilos en espera pueden continuar. Es importante tener en cuenta que `CountDownLatch` no se puede reutilizar después de que la cuenta regresiva haya finalizado.

### CyclicBarrier

`CyclicBarrier` es un punto de sincronización multidireccional reajustable que es útil en ciertos estilos de programación paralela. Se crea con un recuento de partes (hilos) que deben llegar a la barrera antes de que todos puedan continuar. Después de que el número especificado de partes ha llamado a `await()` en la barrera, todos los hilos en espera se liberan (desbloquean). `CyclicBarrier` puede reutilizarse para múltiples ciclos de sincronización.

### Phaser

`Phaser` proporciona una forma más flexible de barrera que se puede usar para controlar cálculos en fases entre varios hilos. Es una versión más versátil de `CyclicBarrier`, ya que permite que las partes (hilos) se registren y anulen su registro durante un período de tiempo, lo que provoca que el número de hilos necesarios antes de avanzar pueda cambiar dinámicamente.

### Exchanger

`Exchanger` permite a dos hilos intercambiar un par de objetos, bloqueándolos hasta que se realice el intercambio. Es una alternativa de memoria bidireccional eficiente a `SynchronousQueue`, facilitando el intercambio directo de datos entre dos hilos.

---

Estas herramientas de sincronización ofrecen diversas formas de controlar y coordinar la ejecución de hilos en aplicaciones concurrentes, cada una adecuada para diferentes escenarios y necesidades de sincronización.

---

## Alternativas de Threads de Alto Nivel

El paquete `java.util.concurrent.ExecutorService` sirve para gestionar y ejecutar tareas concurrentemente en un pool de hilos, simplificando la administracion de hilos y mejorando la eficiencia en aplicaciones concurrentes:

### ExecutorService

`availableProcessors` este ejemplo demuestra cómo utilizar ExecutorService para gestionar y ejecutar tareas concurrentemente en un pool de hilos, optimizando el uso de los recursos de la CPU disponibles en el sistema.

### Callable

`Callable` La interfaz Callable define una tarrea ejecutada en ExecutorService, es similar en naturaleza a Runnable, pero puede: Devolver un resultado mediante genericos y Devolver una excepcion comprobada.

---

Estas herramientas proporcionan una abstracción de alto nivel para la programación concurrente en Java. Con ExecutorService y Callable, los se pueden escribir aplicaciones concurrentes más robustas y escalables, aprovechando eficientemente los recursos del sistema y mejorando el rendimiento general de las aplicaciones.

---

## Marco Fork-Join

El Framework ForkJoin es una parte del paquete `java.util.concurrent` diseñada para aprovechar múltiples procesadores. Permite dividir una tarea grande en tareas más pequeñas (fork) y luego combinarlas (join).

### ForkJoinTask<V>

`ForkJoinTask` Representa una tarea que se va a ejecutar en un ForkJoinPool. Las dos subclases mas comunes son RecursiveAction y RecursiveTask. `RecursiveAction` se usa cuando una tarea no devuelve un resultado. `RecursiveTask` se usa cuando una tarea devuelve un resultado. `ForkJoinPool` es un pool de hilos que ejecuta tareas.

---

Estas herramientas ofrecen una manera poderosa de estructurar y ejecutar operaciones concurrentes en Java, adaptándose eficientemente a entornos de múltiples núcleos y mejorando el rendimiento de las aplicaciones mediante la ejecución paralela de tareas complejas.

---
