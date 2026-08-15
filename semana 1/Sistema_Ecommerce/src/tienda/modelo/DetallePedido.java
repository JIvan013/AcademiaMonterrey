package tienda.modelo;

import java.util.Collections;
import java.util.List;

public final class DetallePedido {
    private final int numeroPedido;
    private final Cliente cliente;
    private final List<Producto> productos;
    private final double total;

    public DetallePedido(int numeroPedido, Cliente cliente, List<Producto> productos, double total) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.productos = Collections.unmodifiableList(productos);
        this.total = total;
    }

    public int getNumeroPedido() { return numeroPedido; }
    public Cliente getCliente() { return cliente; }
    public List<Producto> getProductos() { return productos; }
    public double getTotal() { return total; }
}