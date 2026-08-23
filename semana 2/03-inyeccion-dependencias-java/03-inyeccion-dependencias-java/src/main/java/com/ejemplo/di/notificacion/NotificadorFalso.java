package com.ejemplo.di.notificacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta es la implementación que justifica la palabra "testeabilidad".
 *
 * No envía nada de verdad: solo RECUERDA qué le pidieron enviar, para que
 * una prueba pueda preguntarle después "¿te llegó el mensaje correcto?"
 * sin depender de una bandeja de email real, un módem, ni una red.
 *
 * Esto solo es posible porque ServicioPedidos depende de la interfaz
 * Notificador y recibe la implementación por constructor. Si
 * ServicioPedidos hiciera "new NotificadorEmail()" por dentro (como en
 * antes/ServicioPedido.java), no habría forma de colar este objeto
 * falso en su lugar.
 */
public class NotificadorFalso implements Notificador {

    private final List<String> mensajesEnviados = new ArrayList<>();

    @Override
    public void enviar(String destinatario, String mensaje) {
        mensajesEnviados.add(destinatario + ": " + mensaje);
    }

    public List<String> getMensajesEnviados() {
        return mensajesEnviados;
    }

    public boolean fueEnviadoA(String destinatario) {
        return mensajesEnviados.stream().anyMatch(m -> m.startsWith(destinatario + ":"));
    }
}
