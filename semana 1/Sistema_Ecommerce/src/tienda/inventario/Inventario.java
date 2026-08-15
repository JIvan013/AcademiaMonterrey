package tienda.inventario;

import tienda.modelo.Producto;

public class Inventario {
	private static Inventario instancia;
	private final Repositorio<Producto> productos;

	private Inventario() {
		productos = new Repositorio<>(Producto::getId);
	}

	public static Inventario getInstance() {
		if (instancia == null) {
			instancia = new Inventario();
		}
		return instancia;
	}

	public void registrarProducto(Producto p) {
		productos.agregar(p);
	}

	public Repositorio<Producto> getRepositorio() {
		return productos;
	}
}