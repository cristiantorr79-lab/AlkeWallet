package alkewallet.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaccion {

    private final LocalDateTime fecha;
    private final String emisor;
    private final String receptor;
    private final BigDecimal importe;

    public Transaccion(LocalDateTime fecha,
                       String emisor,
                       String receptor,
                       BigDecimal importe) {
        this.fecha = fecha;
        this.emisor = emisor;
        this.receptor = receptor;
        this.importe = importe;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getEmisor() {
        return emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public boolean esEmisor(String nombreUsuario) {
        return emisor.equals(nombreUsuario);
    }
}