package alkewallet.model;

import java.math.BigDecimal;

public class Usuario {

    private int id;
    private String nombre;
    private BigDecimal saldo;

    public Usuario(int id, String nombre, BigDecimal saldo) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}