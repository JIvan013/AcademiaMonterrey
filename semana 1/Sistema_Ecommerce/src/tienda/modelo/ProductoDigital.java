package tienda.modelo;

public class ProductoDigital extends Producto {

	private String licencia;

	public ProductoDigital(String nombre, double precio, int stock) {
		this(nombre, precio, stock, "Licencia estándar");
	}

	public ProductoDigital(String nombre, double precio, int stock, String licencia) {
		super(nombre, precio, stock);
		this.licencia = licencia;
	}

	public String getLicencia() { return licencia; }

	@Override
	public double calcularPrecioFinal() {
		return getPrecio();
	}
}