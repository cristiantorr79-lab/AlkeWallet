package alkewallet.convertidor;

import java.math.BigDecimal;

import alkewallet.model.Moneda;

public interface ConvertidorMoneda {

    BigDecimal convertir(BigDecimal monto, Moneda origen, Moneda destino);
    
}
