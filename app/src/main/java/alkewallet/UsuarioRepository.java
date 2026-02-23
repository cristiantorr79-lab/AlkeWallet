package alkewallet;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import alkewallet.exception.MontoInvalidoException;
import alkewallet.exception.SaldoInsuficienteException;
import alkewallet.exception.UsuarioNoExisteException;
import alkewallet.model.Transaccion;
import alkewallet.model.Usuario;

public class UsuarioRepository {

    private static final int SISTEMA_ID = 6;

    // =========================================
    // OBTENER USUARIO
    // =========================================
    public Usuario obtenerUsuario(int userId) {

        String sql = "SELECT user_id, nombre, saldo FROM usuario WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("user_id"),
                        rs.getString("nombre"),
                        rs.getBigDecimal("saldo")
                );
            } else {
                throw new UsuarioNoExisteException("Usuario no existe.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo usuario", e);
        }
    }

    public BigDecimal obtenerSaldo(int userId) {
        return obtenerUsuario(userId).getSaldo();
    }

    // =========================================
    // VALIDAR MONTO
    // =========================================
    private void validarMonto(BigDecimal monto) {

        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontoInvalidoException("El monto debe ser mayor que cero.");
        }
    }

    // =========================================
    // DEPOSITAR
    // =========================================
    public void depositar(int userId, BigDecimal monto) {

        validarMonto(monto);

        String updateSaldo = "UPDATE usuario SET saldo = saldo + ? WHERE user_id = ?";
        String insertTransaccion =
                "INSERT INTO transaccion (sender_user_id, receiver_user_id, importe) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {

            conn.setAutoCommit(false);

            try {

                int filas;

                try (PreparedStatement pstmt = conn.prepareStatement(updateSaldo)) {
                    pstmt.setBigDecimal(1, monto);
                    pstmt.setInt(2, userId);
                    filas = pstmt.executeUpdate();
                }

                if (filas == 0) {
                    conn.rollback();
                    throw new UsuarioNoExisteException("Usuario no existe.");
                }

                try (PreparedStatement pstmt = conn.prepareStatement(insertTransaccion)) {
                    pstmt.setInt(1, SISTEMA_ID);
                    pstmt.setInt(2, userId);
                    pstmt.setBigDecimal(3, monto);
                    pstmt.executeUpdate();
                }

                conn.commit();

            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en depósito", e);
        }
    }

    // =========================================
    // RETIRAR
    // =========================================
    public void retirar(int userId, BigDecimal monto) {

        validarMonto(monto);

        String updateSaldo =
                "UPDATE usuario SET saldo = saldo - ? WHERE user_id = ? AND saldo >= ?";

        String insertTransaccion =
                "INSERT INTO transaccion (sender_user_id, receiver_user_id, importe) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {

            conn.setAutoCommit(false);

            try {

                int filas;

                try (PreparedStatement pstmt = conn.prepareStatement(updateSaldo)) {
                    pstmt.setBigDecimal(1, monto);
                    pstmt.setInt(2, userId);
                    pstmt.setBigDecimal(3, monto);
                    filas = pstmt.executeUpdate();
                }

                if (filas == 0) {
                    conn.rollback();
                    throw new SaldoInsuficienteException("Fondos insuficientes.");
                }

                try (PreparedStatement pstmt = conn.prepareStatement(insertTransaccion)) {
                    pstmt.setInt(1, userId);
                    pstmt.setInt(2, SISTEMA_ID);
                    pstmt.setBigDecimal(3, monto);
                    pstmt.executeUpdate();
                }

                conn.commit();

            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en retiro", e);
        }
    }

    // =========================================
    // TRANSFERIR
    // =========================================
    public void transferir(int senderId, int receiverId, BigDecimal monto) {

        validarMonto(monto);

        if (senderId == receiverId) {
            throw new MontoInvalidoException("No puede transferirse a sí mismo.");
        }

        String descontar =
                "UPDATE usuario SET saldo = saldo - ? WHERE user_id = ? AND saldo >= ?";

        String acreditar =
                "UPDATE usuario SET saldo = saldo + ? WHERE user_id = ?";

        String insertTransaccion =
                "INSERT INTO transaccion (sender_user_id, receiver_user_id, importe) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {

            conn.setAutoCommit(false);

            try {

                int filas;

                // Descontar al emisor
                try (PreparedStatement pstmt = conn.prepareStatement(descontar)) {
                    pstmt.setBigDecimal(1, monto);
                    pstmt.setInt(2, senderId);
                    pstmt.setBigDecimal(3, monto);
                    filas = pstmt.executeUpdate();
                }

                if (filas == 0) {
                    conn.rollback();
                    throw new SaldoInsuficienteException("Fondos insuficientes.");
                }

                // Acreditar al receptor
                try (PreparedStatement pstmt = conn.prepareStatement(acreditar)) {
                    pstmt.setBigDecimal(1, monto);
                    pstmt.setInt(2, receiverId);
                    pstmt.executeUpdate();
                }

                // Registrar transacción
                try (PreparedStatement pstmt = conn.prepareStatement(insertTransaccion)) {
                    pstmt.setInt(1, senderId);
                    pstmt.setInt(2, receiverId);
                    pstmt.setBigDecimal(3, monto);
                    pstmt.executeUpdate();
                }

                conn.commit();

            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en transferencia", e);
        }
    }

    // =========================================
    // HISTORIAL
    // =========================================
    public List<Transaccion> obtenerHistorial(int userId) {

        String sql = """
            SELECT t.transaction_date,
                   u1.nombre AS emisor,
                   u2.nombre AS receptor,
                   t.importe
            FROM transaccion t
            JOIN usuario u1 ON t.sender_user_id = u1.user_id
            JOIN usuario u2 ON t.receiver_user_id = u2.user_id
            WHERE t.sender_user_id = ? OR t.receiver_user_id = ?
            ORDER BY t.transaction_date DESC
            """;

        List<Transaccion> historial = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, userId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Timestamp ts = rs.getTimestamp("transaction_date");
                LocalDateTime fecha = ts.toLocalDateTime();

                historial.add(new Transaccion(
                        fecha,
                        rs.getString("emisor"),
                        rs.getString("receptor"),
                        rs.getBigDecimal("importe")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo historial", e);
        }

        return historial;
    }
}