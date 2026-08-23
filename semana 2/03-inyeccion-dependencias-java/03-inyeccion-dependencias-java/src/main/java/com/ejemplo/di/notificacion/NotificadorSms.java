package com.ejemplo.di.notificacion;

/**
 * Segunda implementación intercambiable de Notificador. Simula el envío
 * de un SMS. Ni ServicioPedidos ni Notificador necesitan cambiar una
 * línea para que esta clase exista y funcione: basta con que cumpla el
 * contrato de la interfaz.
 */
public class NotificadorSms implements Notificador {

    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("[SMS] Para: " + destinatario + " | Mensaje: " + mensaje);
    }
}
