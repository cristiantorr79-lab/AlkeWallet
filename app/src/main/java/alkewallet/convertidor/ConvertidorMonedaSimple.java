package alkewallet.convertidor;

import java.math.BigDecimal;
import java.math.RoundingMode;

import alkewallet.model.Moneda;

public class ConvertidorMonedaSimple implements ConvertidorMoneda {

    private static final BigDecimal CLP_USD = new BigDecimal("900");
    private static final BigDecimal CLP_EUR = new BigDecimal("980");

    @Override
    public BigDecimal convertir(BigDecimal monto, Moneda origen, Moneda destino) {

        if (origen == destino) {
            return monto;
        }

        if (origen == Moneda.CLP && destino == Moneda.USD) {
            return monto.divide(CLP_USD, 2, RoundingMode.HALF_UP);
        }

        if (origen == Moneda.CLP && destino == Moneda.EUR) {
            return monto.divide(CLP_EUR, 2, RoundingMode.HALF_UP);
        }

        if (origen == Moneda.USD && destino == Moneda.CLP) {
            return monto.multiply(CLP_USD);
        }

        if (origen == Moneda.EUR && destino == Moneda.CLP) {
            return monto.multiply(CLP_EUR);
        }

        throw new IllegalArgumentException("Conversión no soportada.");
    }
}