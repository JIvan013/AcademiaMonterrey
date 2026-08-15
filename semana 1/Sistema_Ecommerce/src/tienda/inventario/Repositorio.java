package tienda.inventario;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Repositorio<T> {
	private final List<T> items = new ArrayList<>();
	private final Function<T, Integer> extractorId;

	public Repositorio(Function<T, Integer> extractorId) {
		this.extractorId = extractorId;
	}

	public void agregar(T item) {
		items.add(item);
	}

	public T buscarPorId(int id) {
		return items.stream()
				.filter(item -> extractorId.apply(item) == id)
				.findFirst()
				.orElse(null);
	}

	public List<T> listar() {
		return new ArrayList<>(items);
	}

	public List<T> filtrar(Predicate<T> condicion) {
		List<T> resultado = new ArrayList<>();
		for (T item : items) {
			if (condicion.test(item)) resultado.add(item);
		}
		return resultado;
	}
}