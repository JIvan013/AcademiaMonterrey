package com.academymty.concurrencia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ESTE es el corazon del ejercicio de concurrencia.
 *
 * Lanza el MISMO trabajo (incrementar un contador un millon de veces entre
 * varios hilos) de dos formas: con un {@code int} normal (inseguro) y con un
 * {@code AtomicInteger} (seguro). El resultado se imprime en consola para
 * que la diferencia se vea sin adivinar nada.
 *
 * Por que un {@code int} normal falla: "contador++" NO es una operacion
 * atomica. Son tres pasos (leer, sumar 1, escribir), y dos hilos pueden
 * intercalarse justo entre el "leer" y el "escribir" de modo que uno de los
 * dos incrementos se pierda. Con suficientes hilos e iteraciones, SIEMPRE
 * se pierden algunos: el resultado final es menor al esperado, y cambia
 * un poco en cada corrida porque el entrelazado de los hilos no es
 * determinista.
 *
 * NOTA sobre el Thread.yield() de abajo: en una maquina de un solo nucleo
 * (o si el scheduler es "amable" ese dia) los tres pasos de contador++
 * pueden completarse tan rapido que nunca se alcanzan a entrelazar, y la
 * carrera no se ve aunque el bug SIGUE AHI. El yield() separa a proposito
 * el "leer" del "escribir" para que el entrelazado sea visible siempre,
 * sin depender de cuantos nucleos tenga la maquina donde corras esto.
 */
public final class DemoCondicionCarrera {

    private static final int HILOS = 8;
    private static final int INCREMENTOS_POR_HILO = 2_000;

    // --- version INSEGURA: un int comun, compartido entre hilos sin proteccion ---
    private int contadorInseguro = 0;

    private void incrementarInseguro() {
        int actual = contadorInseguro;   // paso 1: leer
        Thread.yield();                  // le da chance a otro hilo de leer el MISMO valor
        contadorInseguro = actual + 1;   // paso 2: escribir (pisa lo que haya escrito el otro)
    }

    // --- version SEGURA: AtomicInteger, cuyo incrementAndGet() es atomico de verdad ---
    private final AtomicInteger contadorSeguro = new AtomicInteger(0);

    private void incrementarSeguro() {
        contadorSeguro.incrementAndGet();
    }

    public void ejecutar() throws InterruptedException {
        int esperado = HILOS * INCREMENTOS_POR_HILO;

        System.out.println("== Condicion de carrera: " + HILOS + " hilos x "
                + INCREMENTOS_POR_HILO + " incrementos cada uno ==");
        System.out.println("Resultado esperado en ambos casos: " + esperado);

        int resultadoInseguro = correr(this::incrementarInseguro);
        System.out.println("Contador SIN proteccion (int comun):     " + resultadoInseguro
                + (resultadoInseguro != esperado ? "  <-- se perdieron " + (esperado - resultadoInseguro) + " incrementos" : ""));

        int resultadoSeguro = correrSeguro();
        System.out.println("Contador CON AtomicInteger:              " + resultadoSeguro
                + (resultadoSeguro == esperado ? "  <-- correcto" : "  <-- deberia ser correcto siempre"));
    }

    private int correr(Runnable tarea) throws InterruptedException {
        contadorInseguro = 0;
        ExecutorService pool = Executors.newFixedThreadPool(HILOS);

        for (int h = 0; h < HILOS; h++) {
            pool.submit(() -> {
                for (int i = 0; i < INCREMENTOS_POR_HILO; i++) {
                    tarea.run();
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
        return contadorInseguro;
    }

    private int correrSeguro() throws InterruptedException {
        contadorSeguro.set(0);
        ExecutorService pool = Executors.newFixedThreadPool(HILOS);

        for (int h = 0; h < HILOS; h++) {
            pool.submit(() -> {
                for (int i = 0; i < INCREMENTOS_POR_HILO; i++) {
                    incrementarSeguro();
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
        return contadorSeguro.get();
    }
}
