package tienda.carrito;

import tienda.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double calcularTotal() {
        return productos.stream()
                .mapToDouble(Producto::calcularPrecioFinal)
                .sum();
    }
}