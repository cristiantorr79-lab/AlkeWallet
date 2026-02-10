package alkewallet.model;

public class Cuenta {

    private String titular;
    private double saldo;
    private Moneda moneda;

    public Cuenta(String titular, double saldoInicial, Moneda moneda) {
        this.titular = titular;
        this.moneda = moneda;

        if (saldoInicial > 0) {
            this.saldo = saldoInicial;
        } else {
            this.saldo = 0;
        }
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    public boolean retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            return true;
        }
        return false;
    }
}
