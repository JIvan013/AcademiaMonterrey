package tienda;

import tienda.carrito.Carrito;
import tienda.carrito.Pedido;
import tienda.inventario.Inventario;
import tienda.modelo.*;
import tienda.pago.MetodoPago;
import tienda.pago.PagoEfectivo;
import tienda.pago.PagoTarjeta;

import java.util.List;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static Inventario inventario = Inventario.getInstance();

	public static void main(String[] args) {
		Carrito carrito = new Carrito();

		boolean salir = false;
		while (!salir) {
			mostrarMenu();
			System.out.print("Elige una opción: ");
			String entrada = sc.nextLine();

			switch (entrada) {
			case "1" -> { mostrarCatalogo(); pausar(); }
			case "2" -> { agregarProductoNuevo(); pausar(); }
			case "3" -> { agregarAlCarrito(carrito); pausar(); }
			case "4" -> { mostrarCarrito(carrito); pausar(); }
			case "5" -> {
				boolean compraExitosa = procesarCompra(carrito);
				pausar();
				if (compraExitosa) salir = true;
			}
			case "6" -> salir = true;
			default -> { System.out.println("Opción no válida."); pausar(); }
			}
		}
		System.out.println("¡Gracias por usar la tienda!");
	}

	static void mostrarMenu() {
		System.out.println("\n==============================");
		System.out.println("           MENÚ");
		System.out.println("==============================");
		System.out.println("1. Ver catálogo");
		System.out.println("2. Agregar producto nuevo");
		System.out.println("3. Agregar producto al carrito");
		System.out.println("4. Ver mi carrito");
		System.out.println("5. Pagar y generar pedido");
		System.out.println("6. Salir sin comprar");
		System.out.println("==============================");
	}

	static void mostrarCatalogo() {
		List<Producto> productos = inventario.getRepositorio().listar();
		if (productos.isEmpty()) {
			System.out.println("El catálogo está vacío. Agrega un producto con la opción 2.");
			return;
		}
		productos.forEach(System.out::println);
	}

	static void agregarProductoNuevo() {
		System.out.println("¿Qué tipo de producto? \n 1) Físico \n 2) Digital \n");
		System.out.print("Opción: ");
		String tipo = sc.nextLine();

		System.out.print("Nombre: ");
		String nombre = sc.nextLine();

		double precio;
		int stock;
		try {
			System.out.print("Precio: ");
			precio = Double.parseDouble(sc.nextLine());
			System.out.print("Stock: ");
			stock = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Precio o stock inválido. Producto no agregado.");
			return;
		}

		if (tipo.equals("1")) {
			double peso;
			try {
				System.out.print("Peso en kg: ");
				peso = Double.parseDouble(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Peso inválido. Producto no agregado.");
				return;
			}
			inventario.registrarProducto(new ProductoFisico(nombre, precio, stock, peso));
		} else {
			inventario.registrarProducto(new ProductoDigital(nombre, precio, stock));
		}
		System.out.println("Producto agregado al catálogo.");
	}

	static void agregarAlCarrito(Carrito carrito) {
		List<Producto> productos = inventario.getRepositorio().listar();
		if (productos.isEmpty()) {
			System.out.println("El catálogo está vacío. Agrega productos primero (opción 2).");
			return;
		}
		mostrarCatalogo();
		System.out.print("Escribe el ID del producto a agregar: ");
		String entrada = sc.nextLine();

		int id;
		try {
			id = Integer.parseInt(entrada);
		} catch (NumberFormatException e) {
			System.out.println("Eso no es un ID válido.");
			return;
		}

		Producto p = inventario.getRepositorio().buscarPorId(id);
		if (p == null) {
			System.out.println("No existe un producto con ese ID.");
			return;
		}
		carrito.agregarProducto(p);
		System.out.println(p.getNombre() + " agregado al carrito.");
	}

	static void mostrarCarrito(Carrito carrito) {
		if (carrito.getProductos().isEmpty()) {
			System.out.println("Tu carrito está vacío.");
			return;
		}
		carrito.getProductos().forEach(System.out::println);
		System.out.printf("Subtotal: $%.2f%n", carrito.calcularTotal());
	}

	static boolean procesarCompra(Carrito carrito) {
		if (carrito.getProductos().isEmpty()) {
			System.out.println("No puedes pagar con el carrito vacío. Agrega productos primero (opción 3).");
			return false;
		}

		System.out.println("Antes de pagar, necesitamos tus datos:");
		System.out.print("Tu nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Tu email: ");
		String email = sc.nextLine();
		Cliente cliente = new Cliente(nombre, email);

		System.out.println("Método de pago: \n 1) Tarjeta \n  2) Efectivo \n");
		System.out.print("Opción: ");
		String metodo = sc.nextLine();

		Pedido pedido = new Pedido(cliente, carrito);
		MetodoPago pago;
		if (metodo.equals("1")) {
			System.out.print("Número de tarjeta: ");
			pago = new PagoTarjeta(sc.nextLine());
		} else {
			pago = new PagoEfectivo();
		}
		pedido.setMetodoPago(pago);

		try {
			DetallePedido detalle = pedido.procesarPedido();
			System.out.println("\n--- FACTURA ---");
			System.out.println("Pedido N°: " + detalle.getNumeroPedido());
			System.out.println("Cliente: " + detalle.getCliente());
			System.out.printf("Total (con IVA): $%.2f%n", detalle.getTotal());
			return true;
		} catch (IllegalStateException e) {
			System.out.println("No se pudo procesar el pedido: " + e.getMessage());
			return false;
		}
	}

	static void pausar() {
		System.out.print("\nPresiona Enter para continuar...");
		sc.nextLine();
	}
}