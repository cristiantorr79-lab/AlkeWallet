package alkewallet.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alkewallet.convertidor.ConvertidorMoneda;
import alkewallet.convertidor.ConvertidorMonedaSimple;

public class WalletTest {

    private Wallet wallet;
    private ConvertidorMoneda convertidor;

    @BeforeEach
    void setUp() {
        Cuenta cuenta = new Cuenta("Cristian", 100000, Moneda.CLP);
        convertidor = new ConvertidorMonedaSimple();
        wallet = new Wallet(cuenta, convertidor);
    }

    @Test
    void obtenerSaldoDevuelveElSaldoDeLaCuenta() {
        // Act
        double saldo = wallet.getSaldo();

        // Assert
        assertEquals(100000, saldo);
    }

    @Test
    void obtenerMonedaDevuelveLaMonedaDeLaCuenta() {
        // Act
        Moneda moneda = wallet.getMoneda();

        // Assert
        assertEquals(Moneda.CLP, moneda);
    }

    @Test
    void depositarAumentaElSaldoDeLaCuenta() {
        // Act
        wallet.depositar(50000);

        // Assert
        assertEquals(150000, wallet.getSaldo());
    }

    @Test
    void retirarDisminuyeElSaldoDeLaCuenta() {
        // Act
        boolean resultado = wallet.retirar(30000);

        // Assert
        assertTrue(resultado);
        assertEquals(70000, wallet.getSaldo());
    }

    @Test
    void retirarRetornaFalseSiNoHayFondosSuficientes() {
        // Act
        boolean resultado = wallet.retirar(150000);

        // Assert
        assertFalse(resultado);
        assertEquals(100000, wallet.getSaldo());
    }
}
