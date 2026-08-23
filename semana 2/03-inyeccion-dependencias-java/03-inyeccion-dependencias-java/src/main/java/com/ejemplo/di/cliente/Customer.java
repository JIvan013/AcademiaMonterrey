package com.ejemplo.di.cliente;

/**
 * Entidad simple del dominio. No tiene ninguna anotación porque este
 * ejemplo es Java puro — sin JPA, sin Spring, sin persistencia de ningún
 * tipo. Es solo el objeto que representa a la persona que recibe la
 * notificación.
 *
 * "contacto" guarda el destino del mensaje, sea un email o un número de
 * teléfono: a ServicioPedidos no le importa cuál de los dos es, solo se
 * lo pasa tal cual al Notificador que le hayan inyectado.
 */
public class Customer {

    private final String nombre;
    private final String contacto;

    public Customer(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }
}
