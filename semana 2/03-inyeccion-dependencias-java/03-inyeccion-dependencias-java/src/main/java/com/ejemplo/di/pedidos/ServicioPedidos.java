package com.ejemplo.di.pedidos;

import com.ejemplo.di.cliente.Customer;
import com.ejemplo.di.notificacion.Notificador;

/**
 * 
 * CLASE CON INYECCIÓN DE DEPENDENCIAS
 *
 * Aquí ocurre la inyección de dependencias.
 *
 * Hay dos cosas importantes:
 *
 * 1. Se usa la interfaz Notificador
 * ServicioPedidos no depende directamente de una clase como
 * NotificadorEmail. Solo sabe que existe un método enviar(...).
 * Esto permite usar diferentes tipos de notificadores, como
 * Email, SMS o uno creado para hacer pruebas.
 *
 * 2. El notificador se recibe por el constructor
 * ServicioPedidos no crea el objeto con "new NotificadorEmail()".
 * El objeto se crea afuera y se pasa al constructor.
 *
 * Por ejemplo:
 * new ServicioPedidos(notificador);
 *
 * Esto es Inyección de Dependencias. La clase recibe lo que necesita
 * en lugar de crearlo por sí misma.
 *
 * En este ejemplo no usamos ningún framework. Main.java se encarga
 * de crear el notificador y pasarlo a ServicioPedidos.
 *
 * Spring hace algo parecido automáticamente usando cosas como
 * @Autowired, pero la idea principal es la misma.
 *
 * Customer solo representa al cliente que recibe la notificación.
 * No es parte de la inyección de dependencias.
 *
 * La inyección ocurre en el atributo "notificador".
 */

public class ServicioPedidos {

    private final Notificador notificador;

    // La dependencia entra por aquí. ServicioPedidos no la busca,
    // no la construye, no sabe de dónde vino. Solo la usa.
    public ServicioPedidos(Notificador notificador) {
        this.notificador = notificador;
    }

    public void confirmarPedido(Customer cliente, String pedido) {
        String mensaje = "Tu pedido " + pedido + " fue confirmado.";
        notificador.enviar(cliente.getContacto(), mensaje);
    }

    public void avisarPedidoRetrasado(Customer cliente, String pedido) {
        String mensaje = "Tu pedido " + pedido + " está retrasado. Disculpa las molestias.";
        notificador.enviar(cliente.getContacto(), mensaje);
    }
}
