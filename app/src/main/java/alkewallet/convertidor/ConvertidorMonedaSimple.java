package alkewallet.convertidor;

public class ConvertidorMonedaSimple implements ConvertidorMoneda {

    // Constantes para las tasas de cambio
    private static final double CLP_TO_USD = 1.0 / 850.0;
    private static final double USD_TO_CLP = 850.0;
    private static final double CLP_TO_EUR = 1.0 / 900.0;
    private static final double EUR_TO_CLP = 900.0;
    private static final double USD_TO_EUR = 1.0 / 1.1;
    private static final double EUR_TO_USD = 1.1;

    @Override
    public double convertir(double monto, String monedaOrigen, String monedaDestino) {
        // Si son la misma moneda, no se necesita conversión
        if (monedaOrigen.equalsIgnoreCase(monedaDestino)) {
            return monto;
        }

        // Crear clave para identificar la conversión
        String clave = monedaOrigen.toUpperCase() + "_TO_" + monedaDestino.toUpperCase();

        // Realizar la conversión según la clave
        return switch (clave) {
            case "CLP_TO_USD" ->
                monto * CLP_TO_USD;
            case "USD_TO_CLP" ->
                monto * USD_TO_CLP;
            case "CLP_TO_EUR" ->
                monto * CLP_TO_EUR;
            case "EUR_TO_CLP" ->
                monto * EUR_TO_CLP;
            case "USD_TO_EUR" ->
                monto * USD_TO_EUR;
            case "EUR_TO_USD" ->
                monto * EUR_TO_USD;
            default -> {
                // Si la conversión no está soportada, retornar el monto original
                System.out.println("Advertencia: Conversión no soportada - "
                        + monedaOrigen + " -> " + monedaDestino);
                yield monto;
            }
        };
    }
}
