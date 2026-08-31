package com.academymty.concurrencia;

/**
 * Un pedido de entrada. Vive solo en memoria mientras corre el programa:
 * NO se serializa (para eso esta ResumenProcesamiento, que es el resultado
 * final que si nos interesa conservar en disco).
 */
public record Pedido(int id, String cliente, double monto) {
}
