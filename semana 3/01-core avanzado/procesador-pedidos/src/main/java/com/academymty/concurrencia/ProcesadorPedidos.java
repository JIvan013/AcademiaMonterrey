package com.academymty.concurrencia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Procesa una lista de pedidos EN PARALELO con un ExecutorService, y por
 * cada uno escribe un archivo de resultado en disco.
 *
 * Tres piezas de concurrencia trabajando juntas, cada una resolviendo un
 * problema distinto:
 *
 *   ExecutorService        -> quien corre el trabajo (un pool de hilos reales,
 *                              no "un hilo por pedido" sin control)
 *   AtomicInteger          -> contar exitos/fallos sin condicion de carrera
 *                              (varios hilos incrementan el MISMO contador)
 *   ConcurrentLinkedQueue  -> guardar las rutas de los archivos escritos sin
 *                              condicion de carrera (varios hilos agregan a
 *                              la MISMA coleccion; una ArrayList normal aqui
 *                              puede corromperse o lanzar
 *                              ConcurrentModificationException)
 */
public class ProcesadorPedidos {

    private final Path carpetaSalida;
    private final int hilos;

    private final AtomicInteger exitosos = new AtomicInteger(0);
    private final AtomicInteger fallidos = new AtomicInteger(0);
    private final ConcurrentLinkedQueue<Path> archivosGenerados = new ConcurrentLinkedQueue<>();

    public ProcesadorPedidos(Path carpetaSalida, int hilos) {
        this.carpetaSalida = carpetaSalida;
        this.hilos = hilos;
    }

    /**
     * Procesa todos los pedidos y bloquea hasta que todos terminaron.
     * Devuelve el resumen final (listo para serializar).
     */
    public ResumenProcesamiento procesar(List<Pedido> pedidos) throws InterruptedException, IOException {

        Files.createDirectories(carpetaSalida); // no falla si ya existe

        ExecutorService pool = Executors.newFixedThreadPool(hilos);

        for (Pedido pedido : pedidos) {
            pool.submit(() -> procesarUno(pedido));
        }

        // Cerramos la aceptacion de tareas nuevas y esperamos a que las
        // que ya estan encoladas terminen. Sin este await, el programa
        // podria seguir e intentar leer el resumen antes de que los
        // hilos hayan escrito todos sus archivos.
        pool.shutdown();
        boolean terminoATiempo = pool.awaitTermination(30, TimeUnit.SECONDS);
        if (!terminoATiempo) {
            System.err.println("ADVERTENCIA: no todos los pedidos terminaron en 30s, se forzo el cierre.");
            pool.shutdownNow();
        }

        double montoTotal = pedidos.stream().mapToDouble(Pedido::monto).sum();

        return new ResumenProcesamiento(pedidos.size(), exitosos.get(), fallidos.get(), montoTotal);
    }

    /**
     * El trabajo de UN hilo: "procesa" un pedido (aqui, formatear un texto)
     * y lo escribe a disco. Cada hilo escribe su PROPIO archivo, asi que no
     * hay dos hilos tocando el mismo Path -- el unico dato compartido de
     * verdad son los contadores y la cola de arriba.
     */
    private void procesarUno(Pedido pedido) {
        Path archivo = carpetaSalida.resolve("pedido-" + pedido.id() + ".txt");

        String contenido = "pedido=%d%ncliente=%s%nmonto=%.2f%nprocesadoPor=%s%n"
                .formatted(pedido.id(), pedido.cliente(), pedido.monto(), Thread.currentThread().getName());

        // try-with-resources: el BufferedWriter (y el stream de abajo) se
        // cierra solo al salir del bloque, incluso si write() lanza una
        // excepcion. Sin esto habria que acordarse de cerrar a mano en un
        // finally, y un archivo abierto que nunca se cierra es un file
        // handle que se queda ocupado hasta que el sistema operativo lo
        // recupere -- con suficientes pedidos, el programa se queda sin
        // handles disponibles y empieza a fallar al abrir archivos nuevos.
        try (BufferedWriter escritor = Files.newBufferedWriter(archivo, StandardCharsets.UTF_8)) {
            escritor.write(contenido);
            exitosos.incrementAndGet();
            archivosGenerados.add(archivo);
        } catch (IOException e) {
            fallidos.incrementAndGet();
            System.err.println("No se pudo escribir " + archivo + ": " + e.getMessage());
        }
    }

    public List<Path> archivosGenerados() {
        return List.copyOf(archivosGenerados);
    }
}
