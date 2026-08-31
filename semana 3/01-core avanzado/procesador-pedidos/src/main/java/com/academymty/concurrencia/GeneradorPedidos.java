package com.academymty.concurrencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Fabrica pedidos falsos para tener algo que procesar en paralelo.
 * No hay nada concurrente aqui todavia; es solo la materia prima.
 */
public final class GeneradorPedidos {

    private GeneradorPedidos() {
    }

    public static List<Pedido> generar(int cantidad) {
        Random random = new Random(42); // semilla fija: resultados reproducibles entre corridas
        List<Pedido> pedidos = new ArrayList<>();

        for (int i = 1; i <= cantidad; i++) {
            String cliente = "cliente-" + i;
            double monto = 100 + random.nextInt(900); // entre 100 y 999
            pedidos.add(new Pedido(i, cliente, monto));
        }

        return pedidos;
    }
}
