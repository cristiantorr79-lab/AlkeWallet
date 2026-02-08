package alkewallet.convertidor;

public class ConvertidorMonedaSimple implements ConvertidorMoneda {

    @Override
    public double convertir(double monto, String monedaOrigen, String monedaDestino) {
        if (monedaOrigen.equalsIgnoreCase(monedaDestino)) {
            return monto; // No se necesita conversión
        }
        // Implementación simple de conversión de moneda
        if (monedaOrigen.equalsIgnoreCase("CLP") && monedaDestino.equalsIgnoreCase("USD")) {
            return monto / 900; // Ejemplo de tasa de cambio
        } else if (monedaOrigen.equalsIgnoreCase("USD") && monedaDestino.equalsIgnoreCase("CLP")) {
            return monto * 900; // Ejemplo de tasa de cambio
        }
        // Si no se reconoce la combinación de monedas, devolver el monto sin convertir
        return monto;
    }

}
