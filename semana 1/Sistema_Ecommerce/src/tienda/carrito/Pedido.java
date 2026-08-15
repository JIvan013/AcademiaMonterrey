package tienda.carrito;

import tienda.modelo.Cliente;
import tienda.modelo.DetallePedido;
import tienda.pago.MetodoPago;
import tienda.util.Constantes;

public class Pedido {
	private static int totalPedidosGenerados = 0;

	private Cliente cliente;
	private Carrito carrito;
	private MetodoPago metodoPago;

	public Pedido(Cliente cliente, Carrito carrito) {
		this.cliente = cliente;
		this.carrito = carrito;
	}

	public void setMetodoPago(MetodoPago metodoPago) {
		this.metodoPago = metodoPago;
	}

	public DetallePedido procesarPedido() {
		if (metodoPago == null) throw new IllegalStateException("Debe elegir un método de pago");

		double subtotal = carrito.calcularTotal();
		double total = subtotal * (1 + Constantes.IVA);

		boolean pagoExitoso = metodoPago.pagar(total);
		if (!pagoExitoso) throw new IllegalStateException("El pago no pudo procesarse");

		totalPedidosGenerados++;
		return new DetallePedido(totalPedidosGenerados, cliente, carrito.getProductos(), total);
	}

	public static int getTotalPedidosGenerados() {
		return totalPedidosGenerados;
	}
}