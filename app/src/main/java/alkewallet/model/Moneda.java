package alkewallet.model;

public enum Moneda {

    CLP("CLP", "$"),
    USD("USD", "US$"),
    EUR("EUR", "€");

    private final String codigo;
    private final String simbolo;

    Moneda(String codigo, String simbolo) {

        this.codigo = codigo;
        this.simbolo = simbolo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}
