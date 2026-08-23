package antes;

/**
 * 
 * VERSIÓN "ANTES" — sin inyección de dependencias
 *
 * Este archivo está hecho para comparar con la versión que usa
 * inyección de dependencias.
 *
 * El problema está en esta línea:
 *
 * ```
 * this.notificador = new NotificadorEmail();
 * ```
 *
 * Aquí, ServicioPedido decide qué notificador usar y además
 * se encarga de crearlo.
 *
 * Esto causa algunos problemas:
 *
 * 1. DEPENDE DIRECTAMENTE DE UNA CLASE
 * ServicioPedido está ligado a NotificadorEmail. Si después
 * queremos usar SMS en lugar de correo, tenemos que modificar
 * esta clase para cambiar el código.
 *
 * 2. ES MÁS DIFÍCIL DE PROBAR
 * Para probar confirmarPedido(), también se necesita usar
 * NotificadorEmail. No podemos cambiarlo fácilmente por uno
 * falso para hacer pruebas.
 *
 * 3. LOS CAMBIOS PUEDEN AFECTAR ESTA CLASE
 * Si cambia el constructor de NotificadorEmail, por ejemplo,
 * si necesita un parámetro nuevo, ServicioPedido también tendrá
 * que modificarse aunque su lógica no haya cambiado.
 *
 * Customer solo está aquí para que el archivo pueda funcionar
 * sin depender de otras clases. El problema principal es que
 * ServicioPedido crea directamente el NotificadorEmail.
 */

public class ServicioPedido {

    // Entidad del dominio, embebida aquí solo para que este archivo
    // compile solo (ver nota más arriba).
    static class Customer {
        private final String nombre;
        private final String contacto;

        Customer(String nombre, String contacto) {
            this.nombre = nombre;
            this.contacto = contacto;
        }

        String getContacto() {
            return contacto;
        }
    }

    // Implementación concreta, embebida aquí solo para que este archivo compile solo
    //  En un proyecto real viviría en su propio archivo,
    // pero el problema de fondo es el mismo.
    static class NotificadorEmail {
        void enviar(String destinatario, String mensaje) {
            System.out.println("[EMAIL] Para: " + destinatario + " | " + mensaje);
        }
    }

    private final NotificadorEmail notificador;

    public ServicioPedido() {
        // <<< Aqui la clase arma su propia dependencia
        // en lugar de que alguien externo se la entregue ya lista.
        this.notificador = new NotificadorEmail();
    }

    public void confirmarPedido(Customer cliente, String pedido) {
        String mensaje = "Tu pedido " + pedido + " fue confirmado.";
        notificador.enviar(cliente.getContacto(), mensaje);
    }

    public static void main(String[] args) {
        ServicioPedido servicio = new ServicioPedido();
        Customer ana = new Customer("Ana", "ana@correo.com");
        servicio.confirmarPedido(ana, "PED-001");

        // Si ahora quisieras notificar por SMS en vez de email, no hay
        // ninguna línea que puedas cambiar "desde afuera": tendrías que
        // editar el código fuente de ServicioPedido. Compáralo con
        // Main.java en src/, donde el cambio es una sola línea y no toca
        // ServicioPedidos en absoluto.
    }
}
