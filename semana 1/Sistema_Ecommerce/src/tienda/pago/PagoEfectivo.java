package tienda.pago;

public class PagoEfectivo implements MetodoPago {
    @Override
    public boolean pagar(double monto) {
        System.out.printf("Pago en efectivo registrado: $%.2f%n", monto);
        return true;
    }
}