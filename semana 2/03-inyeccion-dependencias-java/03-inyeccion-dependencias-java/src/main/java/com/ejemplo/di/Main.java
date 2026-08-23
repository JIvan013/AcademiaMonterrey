package com.ejemplo.di;

import com.ejemplo.di.cliente.Customer;
import com.ejemplo.di.notificacion.Notificador;
import com.ejemplo.di.notificacion.NotificadorEmail;
import com.ejemplo.di.notificacion.NotificadorSms;
import com.ejemplo.di.pedidos.ServicioPedidos;

/**
 * 
 * Main = el contenedor hecho manualmente.
 *
 * Cuando usamos Spring, cosas como @Autowired y @Service hacen
 * algo parecido a lo que hacemos aquí.
 *
 * Main decide qué implementación de Notificador usar, crea el objeto
 * y se lo pasa a ServicioPedidos por el constructor.
 *
 * En este ejemplo no usamos Spring. Nosotros hacemos el trabajo
 * manualmente.
 *
 * El mismo ServicioPedidos se puede crear con diferentes tipos de
 * Notificador. La clase ServicioPedidos no necesita cambiar para eso.
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Ensamblado 1: notificar por EMAIL ===");
        // "El contenedor" decide: hoy, ServicioPedidos trabaja con email.
        Notificador porEmail = new NotificadorEmail();
        ServicioPedidos serviciosPorEmail = new ServicioPedidos(porEmail);
        Customer ana = new Customer("Ana", "ana@correo.com");
        serviciosPorEmail.confirmarPedido(ana, "PED-001");
        serviciosPorEmail.avisarPedidoRetrasado(ana, "PED-001");

        System.out.println();
        System.out.println("=== Ensamblado 2: notificar por SMS ===");
        // Cambiamos UNA línea aquí abajo. ServicioPedidos.java: cero cambios.
        Notificador porSms = new NotificadorSms();
        ServicioPedidos serviciosPorSms = new ServicioPedidos(porSms);
        Customer luis = new Customer("Luis", "+52 55 1234 5678");
        serviciosPorSms.confirmarPedido(luis, "PED-002");
        serviciosPorSms.avisarPedidoRetrasado(luis, "PED-002");

        System.out.println();
        System.out.println("Fíjate: ServicioPedidos.confirmarPedido() y");
        System.out.println("avisarPedidoRetrasado() se ejecutaron sin cambios en");
        System.out.println("ambos casos. Lo único que cambió fue QUÉ objeto le");
        System.out.println("entregamos al construirlo. Eso es toda la idea.");
    }
}
