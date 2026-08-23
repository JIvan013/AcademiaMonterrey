package com.ejemplo.di.notificacion;

/**
 * Este es el contrato. Es la única cosa de la que ServicioPedidos va a
 * depender — nunca de una implementación concreta como NotificadorEmail
 * o NotificadorSms.
 *
 * "Depender de una interfaz, no de una implementación" es, junto con
 * "recibir la dependencia en vez de crearla", la otra mitad de qué es
 * inyección de dependencias. Sin este contrato, aunque recibieras el
 * objeto por constructor, seguirías atado a una clase concreta.
 */
public interface Notificador {
    void enviar(String destinatario, String mensaje);
}
