package alkewallet;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import alkewallet.exception.MontoInvalidoException;
import alkewallet.exception.SaldoInsuficienteException;
import alkewallet.exception.UsuarioNoExisteException;
import alkewallet.model.Moneda;
import alkewallet.model.Transaccion;
import alkewallet.model.Usuario;
import alkewallet.service.WalletService;

public class WalletApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final WalletService walletService =
            new WalletService(new UsuarioRepository());

    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    public static void main(String[] args) {

        System.out.println("=== ALKE WALLET PROFESIONAL ===");

        Usuario usuario = null;

        // LOGIN
        while (usuario == null) {

            try {
                System.out.print("Ingrese su user_id: ");
                int userId = Integer.parseInt(scanner.nextLine());

                usuario = walletService.login(userId);

                System.out.println("Usuario cargado correctamente.");
                System.out.println("Bienvenido, " + usuario.getNombre() + ".");
                System.out.println("Saldo actual: "
                        + Moneda.CLP.getSimbolo() + " "
                        + df.format(usuario.getSaldo()));

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            } catch (UsuarioNoExisteException e) {
                System.out.println(e.getMessage());
            }
        }

        int opcion = -1;

        while (opcion != 0) {

            System.out.println("\nMENÚ PRINCIPAL:");
            System.out.println("1: Ver Saldo");
            System.out.println("2: Depositar");
            System.out.println("3: Retirar");
            System.out.println("4: Transferir");
            System.out.println("5: Convertir saldo");
            System.out.println("6: Ver historial");
            System.out.println("7: Exportar historial a CSV ");
            System.out.println("0: Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1 -> {
                        BigDecimal saldo =
                                walletService.consultarSaldo(usuario.getId());

                        System.out.println("Saldo actual: "
                                + Moneda.CLP.getSimbolo() + " "
                                + df.format(saldo));
                    }

                    case 2 -> {
                        System.out.print("Monto a depositar: ");
                        BigDecimal monto = new BigDecimal(scanner.nextLine());

                        walletService.depositar(usuario.getId(), monto);

                        System.out.println("Depósito realizado correctamente.");
                    }

                    case 3 -> {
                        System.out.print("Monto a retirar: ");
                        BigDecimal monto = new BigDecimal(scanner.nextLine());

                        walletService.retirar(usuario.getId(), monto);

                        System.out.println("Retiro realizado correctamente.");
                    }

                    case 4 -> {
                        System.out.print("ID del destinatario: ");
                        int destino = Integer.parseInt(scanner.nextLine());

                        System.out.print("Monto a transferir: ");
                        BigDecimal monto = new BigDecimal(scanner.nextLine());

                        walletService.transferir(usuario.getId(), destino, monto);

                        Usuario receptor = walletService.login(destino);

                        System.out.println("Transferencia realizada a "
                                + receptor.getNombre() + " correctamente.");
                    }

                    case 5 -> {
                        System.out.println("Seleccione moneda destino:");
                        System.out.println("1: USD");
                        System.out.println("2: EUR");
                        System.out.print("Opción: ");

                        int opMoneda = Integer.parseInt(scanner.nextLine());

                        Moneda destino;

                        if (opMoneda == 1) {
                            destino = Moneda.USD;
                        } else if (opMoneda == 2) {
                            destino = Moneda.EUR;
                        } else {
                            System.out.println("Opción inválida.");
                            break;
                        }

                        BigDecimal convertido =
                                walletService.convertirSaldo(usuario.getId(), destino);

                        System.out.println("Saldo convertido: "
                                + destino.getSimbolo() + " "
                                + df.format(convertido));
                    }

                 case 6 -> {

    List<Transaccion> historial =
            walletService.obtenerHistorial(usuario.getId());

    if (historial.isEmpty()) {
        System.out.println("No hay transacciones.");
        break;
    }

    BigDecimal saldoActual =
            walletService.consultarSaldo(usuario.getId());

    System.out.println("\nFecha               Tipo      Monto         Saldo        Contraparte");
    System.out.println("--------------------------------------------------------------------------");

    for (Transaccion t : historial) {

        boolean esDebito = t.esEmisor(usuario.getNombre());

        String tipo = esDebito ? "DEBITO" : "CREDITO";
        String contraparte = esDebito ? t.getReceptor() : t.getEmisor();

        BigDecimal montoMovimiento = esDebito
                ? t.getImporte().negate()
                : t.getImporte();

        System.out.printf("%-18s %-8s %10s %12s %12s%n",
                t.getFecha().toString().replace("T", " "),
                tipo,
                Moneda.CLP.getSimbolo() + " " + df.format(montoMovimiento),
                Moneda.CLP.getSimbolo() + " " + df.format(saldoActual),
                contraparte);

        // Retrocedemos saldo para siguiente iteración
        saldoActual = saldoActual.subtract(montoMovimiento);
    }
}
case 7 -> {

    List<Transaccion> historial =
            walletService.obtenerHistorial(usuario.getId());

    if (historial.isEmpty()) {
        System.out.println("No hay transacciones para exportar.");
        break;
    }

    BigDecimal saldoActual =
            walletService.consultarSaldo(usuario.getId());

    String nombreArchivo = "historial_"
            + usuario.getId() + "_"
            + java.time.LocalDateTime.now()
                .toString()
                .replace(":", "-")
                .replace("T", "_")
            + ".csv";

    alkewallet.util.ExportadorCSV.exportar(
            nombreArchivo,
            historial,
            usuario.getNombre(),
            saldoActual
    );

    System.out.println("Archivo generado: " + nombreArchivo);
}
                    case 0 -> System.out.println("Gracias por usar AlkeWallet.");

                    default -> System.out.println("Opción inválida.");
                }

            } catch (MontoInvalidoException |
                     SaldoInsuficienteException |
                     UsuarioNoExisteException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Entrada inválida.");
            }
        }

        scanner.close();
    }
}