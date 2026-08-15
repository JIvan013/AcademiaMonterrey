package tienda.modelo;

public abstract class Producto implements Comparable<Producto>, Descontable  {

	private static int contadorId = 1; 
	private final int id;
	private String nombre;
	private double precio;
	protected int stock;

	public Producto(String nombre, double precio, int stock) {
		this.id = contadorId++;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	public abstract double calcularPrecioFinal();

	public int getId() { return id; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public double getPrecio() { return precio; }
	public void setPrecio(double precio) {
		if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo");
		this.precio = precio;
	}

	public int getStock() { return stock; }
	public void reducirStock(int cantidad) {
		if (cantidad > stock) throw new IllegalStateException("Stock insuficiente de " + nombre);
		stock -= cantidad;
	}

	@Override
	public int compareTo(Producto otro) {
		return Double.compare(this.calcularPrecioFinal(), otro.calcularPrecioFinal());
	}

	@Override
	public double aplicarDescuento(double porcentaje) {
		double nuevoPrecio = this.precio * (1 - porcentaje / 100.0);
		setPrecio(nuevoPrecio);
		return nuevoPrecio;
	}

	@Override
	public String toString() {
		return String.format("[%d] %s - $%.2f (stock: %d)", id, nombre, calcularPrecioFinal(), stock);
	}
}


