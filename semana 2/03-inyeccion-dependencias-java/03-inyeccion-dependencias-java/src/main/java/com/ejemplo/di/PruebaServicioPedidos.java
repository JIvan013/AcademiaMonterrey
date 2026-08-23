package com.ejemplo.di;

import com.ejemplo.di.cliente.Customer;
import com.ejemplo.di.notificacion.NotificadorFalso;
import com.ejemplo.di.pedidos.ServicioPedidos;

/**
 * 
 * Esto NO es JUnit. Es una prueba hecha manualmente usando "assert"
 * de Java.
 *
 * La idea es mostrar que
 * 
 */
public class PruebaServicioPedidos {

    public static void main(String[] args) {

        // 1) En vez de un Notificador real, inyectamos uno de prueba.
        //    Esto solo es posible porque ServicioPedidos pide la
        //    interfaz Notificador por constructor, no una clase concreta.
        NotificadorFalso notificadorDePrueba = new NotificadorFalso();
        ServicioPedidos servicio = new ServicioPedidos(notificadorDePrueba);

        // 2) Ejecutamos el comportamiento real de la clase bajo prueba.
        Customer clienteDePrueba = new Customer("Cliente de prueba", "cliente@correo.com");
        servicio.confirmarPedido(clienteDePrueba, "PED-777");

        // 3) Verificamos, sin haber tocado red, disco, ni consola de
        //    verdad, que el mensaje correcto llegó al destinatario
        //    correcto.
        assert notificadorDePrueba.fueEnviadoA(clienteDePrueba.getContacto())
                : "Se esperaba un mensaje para " + clienteDePrueba.getContacto();

        assert notificadorDePrueba.getMensajesEnviados().get(0)
                .contains("PED-777")
                : "El mensaje debía mencionar el pedido PED-777";

        assert notificadorDePrueba.getMensajesEnviados().size() == 1
                : "Debía haberse enviado exactamente un mensaje";

        System.out.println("OK: las 3 aserciones pasaron.");
        System.out.println("Mensajes capturados por el notificador falso:");
        notificadorDePrueba.getMensajesEnviados().forEach(m -> System.out.println("  - " + m));

        System.out.println();
        System.out.println("Ninguna de estas verificaciones necesitó una bandeja");
        System.out.println("de email real, un módem SMS, ni ningún recurso externo.");
        System.out.println("Eso es lo que 'testeabilidad' quiere decir en la práctica.");
    }
}
