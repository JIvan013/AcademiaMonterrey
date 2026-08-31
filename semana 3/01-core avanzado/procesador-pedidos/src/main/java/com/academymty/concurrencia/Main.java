package com.academymty.concurrencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * Punto de entrada. Corre las tres partes del ejercicio en orden:
 *
 *   1. DemoCondicionCarrera -- por que necesitamos AtomicInteger/synchronized
 *   2. ProcesadorPedidos    -- hilos reales escribiendo archivos con NIO
 *   3. Serializacion        -- el resumen final se guarda y se vuelve a leer
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {

        // ---- 1. Condicion de carrera: el porque de todo lo demas ----
        new DemoCondicionCarrera().ejecutar();

        // ---- 2. Procesamiento concurrente con archivos ----
        System.out.println();
        System.out.println("== Procesando pedidos con un pool de hilos ==");

        List<Pedido> pedidos = GeneradorPedidos.generar(20);
        Path carpetaSalida = Path.of("salida", "pedidos");

        ProcesadorPedidos procesador = new ProcesadorPedidos(carpetaSalida, 4);
        ResumenProcesamiento resumen = procesador.procesar(pedidos);

        System.out.println("Pedidos procesados: " + resumen.totalPedidos()
                + " (exitosos=" + resumen.exitosos() + ", fallidos=" + resumen.fallidos() + ")");
        System.out.println("Monto total: " + resumen.montoTotal());
        System.out.println("Archivos escritos en: " + carpetaSalida.toAbsolutePath());
        System.out.println("Antes de serializar, hiloQueLoGenero = " + resumen.hiloQueLoGenero());

        // ---- 3. Serializacion: guardar el objeto y volverlo a leer ----
        Path archivoResumen = Path.of("salida", "resumen.dat");

        // ObjectOutputStream dentro de try-with-resources: si algo falla a
        // media escritura, el stream se cierra solo y no se queda el
        // archivo a medio escribir con el handle abierto.
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivoResumen.toFile()))) {
            salida.writeObject(resumen);
        }
        System.out.println("Resumen serializado en: " + archivoResumen.toAbsolutePath());

        ResumenProcesamiento resumenLeido;
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivoResumen.toFile()))) {
            resumenLeido = (ResumenProcesamiento) entrada.readObject();
        }

        System.out.println();
        System.out.println("== Resumen leido de vuelta desde disco ==");
        System.out.println(resumenLeido);
        System.out.println("hiloQueLoGenero despues de deserializar = "
                + resumenLeido.hiloQueLoGenero() + "   <-- transient: se perdio a proposito");
    }
}
