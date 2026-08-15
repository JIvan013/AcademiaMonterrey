package tienda;

import tienda.carrito.Carrito;
import tienda.carrito.Pedido;
import tienda.inventario.Inventario;
import tienda.modelo.*;
import tienda.pago.PagoTarjeta;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Inventario inventario = Inventario.getInstance();

        Producto laptop = new ProductoFisico("Laptop", 800, 10, 2.5);
        Producto mouse = new ProductoFisico("Mouse", 20, 50, 0.2);
        Producto cursoJava = new ProductoDigital("Curso de Java", 50, 100);

        inventario.registrarProducto(laptop);
        inventario.registrarProducto(mouse);
        inventario.registrarProducto(cursoJava);

        Cliente cliente = new Cliente("Ana Pérez", "ana@example.com");
        Carrito carrito = new Carrito();
        carrito.agregarProducto(laptop);
        carrito.agregarProducto(mouse);
        carrito.agregarProducto(cursoJava);

        List<Producto> productos = carrito.getProductos();

        productos.sort(null);
        System.out.println("Ordenado por precio (Comparable):");
        productos.forEach(System.out::println);

        Comparator<Producto> porNombreAnonimo = new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return p1.getNombre().compareTo(p2.getNombre());
            }
        };
        productos.sort(porNombreAnonimo);
        System.out.println("\nOrdenado por nombre (clase anónima):");
        productos.forEach(System.out::println);

        Comparator<Producto> porNombreLambda = (p1, p2) -> p1.getNombre().compareTo(p2.getNombre());
        productos.sort(porNombreLambda);

        for (Producto p : productos) {
            p.aplicarDescuento(10);
        }

        Pedido pedido = new Pedido(cliente, carrito);
        pedido.setMetodoPago(new PagoTarjeta("4111-2222-3333-4444"));

        DetallePedido detalle = pedido.procesarPedido();

        System.out.println("\n--- FACTURA ---");
        System.out.println("Pedido N°: " + detalle.getNumeroPedido());
        System.out.println("Cliente: " + detalle.getCliente());
        System.out.printf("Total (con IVA): $%.2f%n", detalle.getTotal());

        for (Producto p : detalle.getProductos()) {
            if (p instanceof ProductoFisico) {
                ProductoFisico pf = (ProductoFisico) p;
                System.out.println(p.getNombre() + " pesa " + pf.getPesoKg() + " kg");
            }
        }

        System.out.println("\nTotal de pedidos generados: " + Pedido.getTotalPedidosGenerados());
    }
}