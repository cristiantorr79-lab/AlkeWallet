package alkewallet.model;

import java.math.BigDecimal;

public class Cuenta {

    private String titular;
    private BigDecimal saldo;
    private Moneda moneda;

    public Cuenta(String titular, BigDecimal saldo, Moneda moneda) {
        this.titular = titular;
        this.saldo = saldo != null ? saldo : BigDecimal.ZERO;
        this.moneda = moneda;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void recargar(BigDecimal monto) {
        validarMonto(monto);
        saldo = saldo.add(monto);
    }

    public void descontar(BigDecimal monto) {
        validarMonto(monto);

        if (saldo.compareTo(monto) < 0) {
            throw new IllegalStateException("Saldo insuficiente.");
        }

        saldo = saldo.subtract(monto);
    }

    private void validarMonto(BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }
    }
}