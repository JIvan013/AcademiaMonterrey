package tienda.pago;

public class PagoTarjeta implements MetodoPago {
    private String numeroTarjeta;

    public PagoTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    @Override
    public boolean pagar(double monto) {
        System.out.printf("Cobrando $%.2f a la tarjeta %s%n", monto, numeroTarjeta);
        return true;
    }
}