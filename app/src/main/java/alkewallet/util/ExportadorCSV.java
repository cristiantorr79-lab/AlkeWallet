package alkewallet.util;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import alkewallet.model.Transaccion;

public class ExportadorCSV {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void exportar(String nombreArchivo,
                                List<Transaccion> historial,
                                String nombreUsuario,
                                BigDecimal saldoActual) {

        try (FileWriter writer = new FileWriter(nombreArchivo)) {

            writer.write("Fecha,Tipo,Monto,Saldo,Contraparte\n");

            for (Transaccion t : historial) {

                boolean esDebito = t.esEmisor(nombreUsuario);

                String tipo = esDebito ? "DEBITO" : "CREDITO";
                String contraparte = esDebito
                        ? t.getReceptor()
                        : t.getEmisor();

                BigDecimal montoMovimiento = esDebito
                        ? t.getImporte().negate()
                        : t.getImporte();

                writer.write(
                        formatter.format(t.getFecha()) + "," +
                        tipo + "," +
                        montoMovimiento + "," +
                        saldoActual + "," +
                        contraparte + "\n"
                );

                saldoActual = saldoActual.subtract(montoMovimiento);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error exportando archivo", e);
        }
    }
}
