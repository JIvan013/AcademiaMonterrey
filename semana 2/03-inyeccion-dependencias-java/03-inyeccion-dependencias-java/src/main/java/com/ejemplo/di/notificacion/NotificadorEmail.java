package com.ejemplo.di.notificacion;

/**
 * Una implementación real de Notificador. Simula el envío de un email
 * (aquí solo imprime, pero podría hablarle a un servidor SMTP de verdad).
 *
 * Lo importante: nadie que use Notificador necesita saber que esta clase
 * existe. Solo necesita saber que "algo que sabe enviar()" le va a llegar
 * por constructor.
 */
public class NotificadorEmail implements Notificador {

    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("[EMAIL] Para: " + destinatario + " | Mensaje: " + mensaje);
    }
}
