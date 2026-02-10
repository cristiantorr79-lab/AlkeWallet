package alkewallet.model;

import alkewallet.convertidor.ConvertidorMoneda;

public class Wallet {

    private Cuenta cuenta;
    private ConvertidorMoneda convertidor;

    public Wallet(Cuenta cuenta, ConvertidorMoneda convertidor) {
        this.cuenta = cuenta;
        this.convertidor = convertidor;

    }

    public Moneda getMoneda() {
        return cuenta.getMoneda();
    }

    public double getSaldo() {
        return cuenta.getSaldo();
    }

    public void depositar(double monto) {
        cuenta.depositar(monto);
    }

    public boolean retirar(double monto) {
        return cuenta.retirar(monto);
    }

    public double convertirSaldo(String monedaDestino) {
        return convertidor.convertir(cuenta.getSaldo(), cuenta.getMoneda().getCodigo(), monedaDestino);
    }

}
