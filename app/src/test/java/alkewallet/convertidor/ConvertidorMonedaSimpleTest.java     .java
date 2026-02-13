package alkewallet.convertidor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.annotations.VisibleForTesting;

public class CnvertidorMonedaSimpleTest {

    private ConvertidorMoneda convertidor;

    @BeforeEach
    void setUp() {
        convertidor = new ConvertidorMonedaSimple();
    }

    @Test
    void convertirCLPaUSD() {
        // Arrange
        double monto = 850000; // 850,000 CLP
        // Act
        double resultado = convertidor.convertir(monto, "CLP", "USD");

        // Assert
        double esperado = 1000.0; // 850,000 CLP = 1,000 USD
        assertEquals(esperado, resultado, 0.01);
    }

    @Test
    void convertirUSDaCLP() {

        // Arrange
        double monto = 100; // 100 USD
        // Act
        double resultado = convertidor.convertir(monto, "USD", "CLP");

        // Assert
        double esperado = 85000.0; // 100 USD = 85,000 CLP
        assertEquals(esperado, resultado, 0.01);
    }

    @Test
    void convertirCLPaEUR() {
        // Arrange
        double monto = 900000; // 900,000 CLP
        // Act
        double resultado = convertidor.convertir(monto, "CLP", "EUR");

        // Assert
        double esperado = 1000.0; // 900,000 CLP = 1,000 EUR
        assertEquals(esperado, resultado, 0.01);
    }
}
