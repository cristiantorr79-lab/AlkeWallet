package alkewallet.model;

import java.math.BigDecimal;

import alkewallet.convertidor.ConvertidorMoneda;

public class Wallet {

    private Cuenta cuenta;
    private ConvertidorMoneda convertidor;

    public Wallet(Cuenta cuenta, ConvertidorMoneda convertidor) {
        this.cuenta = cuenta;
        this.convertidor = convertidor;
    }

    public BigDecimal consultarSaldo() {
        return cuenta.getSaldo();
    }

    public void recargarSaldo(BigDecimal monto) {
        cuenta.recargar(monto);
    }

    public BigDecimal convertirSaldo(Moneda monedaDestino) {
        return convertidor.convertir(
                cuenta.getSaldo(),
                cuenta.getMoneda(),
                monedaDestino
        );
    }
}