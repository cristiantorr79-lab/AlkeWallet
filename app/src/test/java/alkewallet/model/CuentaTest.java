package alkewallet.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class CuentaTest {

    @Test
    void saldoInicialSeAsignaCorrectamente() {
        // Arrange (preparar el escenario)
        String titular = "Cristian";
        double saldoInicial = 100000;
        Moneda moneda = Moneda.CLP;

        Cuenta cuenta = new Cuenta(titular, saldoInicial, moneda);

        // Act + Assert (verificación)
        assertEquals(saldoInicial, cuenta.getSaldo());
    }

    @Test
    void depositarAumentaElSaldo() {
        // Arrange
        String titular = "Cristian";
        double saldoInicial = 100000;
        Moneda moneda = Moneda.CLP;

        Cuenta cuenta = new Cuenta(titular, saldoInicial, moneda);

        // Act
        double montoDeposito = 50000;
        cuenta.depositar(montoDeposito);

        // Assert
        double saldoEsperado = saldoInicial + montoDeposito;
        assertEquals(saldoEsperado, cuenta.getSaldo());
    }

    @Test
    void noPermiteDepositarMontoNegativo() {
        // Arrange
        String titular = "Cristian";
        double saldoInicial = 100000;
        Moneda moneda = Moneda.CLP;

        Cuenta cuenta = new Cuenta(titular, saldoInicial, moneda);

        // Act
        double montoDeposito = -50000;
        cuenta.depositar(montoDeposito);

        // Assert
        assertEquals(saldoInicial, cuenta.getSaldo());
    }

    @Test
    void retirarDisminuyeElSaldo() {
        // Arrange
        String titular = "Cristian";
        double saldoInicial = 100000;
        Moneda moneda = Moneda.CLP;

        Cuenta cuenta = new Cuenta(titular, saldoInicial, moneda);

        // Act
        double montoRetiro = 50000;
        boolean resultadoRetiro = cuenta.retirar(montoRetiro);

        // Assert
        double saldoEsperado = saldoInicial - montoRetiro;
        assertEquals(saldoEsperado, cuenta.getSaldo());
        assertTrue(resultadoRetiro);
    }

    @Test
    void noPermiteRetirarMasQueElSaldo() {
        // Arrange
        String titular = "Cristian";
        double saldoInicial = 100000;
        Moneda moneda = Moneda.CLP;

        Cuenta cuenta = new Cuenta(titular, saldoInicial, moneda);

        // Act
        double montoRetiro = 150000;
        boolean resultadoRetiro = cuenta.retirar(montoRetiro);

        // Assert
        assertEquals(saldoInicial, cuenta.getSaldo());
        assertFalse(resultadoRetiro);
    }

    @Test
    void noPermiteRetirarMontoNegativo() {
        // Arrange
        String titular = "Cristian";
        double saldoInicial = 100000;
        Moneda moneda = Moneda.CLP;

        Cuenta cuenta = new Cuenta(titular, saldoInicial, moneda);

        // Act
        double montoRetiro = -50000;
        boolean resultadoRetiro = cuenta.retirar(montoRetiro);

        // Assert
        assertEquals(saldoInicial, cuenta.getSaldo());
        assertFalse(resultadoRetiro);
    }
}
