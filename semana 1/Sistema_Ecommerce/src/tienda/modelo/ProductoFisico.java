package tienda.modelo;

public class ProductoFisico extends Producto {

	private double pesoKg;
	private static final double COSTO_POR_KG = 15.0;

	public ProductoFisico(String nombre, double precio, int stock, double pesoKg) {
		super(nombre, precio, stock);
		this.pesoKg = pesoKg;
	}

	public double getPesoKg() { return pesoKg; }

	@Override
	public double calcularPrecioFinal() {
		return getPrecio() + (pesoKg * COSTO_POR_KG);
	}
}