package alkewallet;

import java.text.DecimalFormat;
import java.util.Scanner;

import alkewallet.convertidor.ConvertidorMoneda;
import alkewallet.convertidor.ConvertidorMonedaSimple;
import alkewallet.model.Cuenta;
import alkewallet.model.Moneda;
import alkewallet.model.Wallet;

public class WalletApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static Wallet wallet = null;
    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    private static boolean validarCuentaCreada() {
        if (wallet == null) {
            System.out.println("Primero debe crear una cuenta.");
            return false;
        }
        return true;
    }

    private static Moneda seleccionarMoneda() {
        Moneda monedaSeleccionada = null;

        do {
            System.out.println("Seleccione moneda:");
            System.out.println("1: CLP");
            System.out.println("2: USD");
            System.out.println("3: EUR");
            System.out.print("Opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();
                continue;
            }

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    monedaSeleccionada = Moneda.CLP;
                    break;
                case 2:
                    monedaSeleccionada = Moneda.USD;
                    break;
                case 3:
                    monedaSeleccionada = Moneda.EUR;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (monedaSeleccionada == null);

        return monedaSeleccionada;
    }

    public static void main(String[] args) {

        int opcion = -1;

        do {
            System.out.println("\nMENÚ PRINCIPAL:");
            System.out.println("1: Crear Cuenta");
            System.out.println("2: Ver Saldo");
            System.out.println("3: Depositar");
            System.out.println("4: Retirar");
            System.out.println("5: Convertir saldo");
            System.out.println("0: Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                opcion = -1;
            }

            switch (opcion) {

                case 1:
                    if (wallet != null) {
                        System.out.println("Ya existe una cuenta creada.");
                        break;
                    }

                    String titular;
                    do {
                        System.out.print("Ingrese nombre del titular: ");
                        titular = scanner.nextLine().trim();

                        if (titular.isEmpty()) {
                            System.out.println("El nombre no puede estar vacío.");
                        } else if (!titular.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                            System.out.println("El nombre solo debe contener letras.");
                            titular = "";
                        }

                    } while (titular.isEmpty());

                    Moneda monedaSeleccionada = seleccionarMoneda();

                    System.out.print("Ingrese saldo inicial ("
                            + monedaSeleccionada.getSimbolo() + "): ");

                    if (!scanner.hasNextDouble()) {
                        System.out.println("Por favor, ingrese un número válido.");
                        scanner.nextLine();
                        break;
                    }

                    double saldoInicial = scanner.nextDouble();
                    scanner.nextLine();

                    Cuenta cuenta = new Cuenta(titular, saldoInicial, monedaSeleccionada);
                    ConvertidorMoneda convertidor = new ConvertidorMonedaSimple();
                    wallet = new Wallet(cuenta, convertidor);

                    System.out.println("Cuenta creada exitosamente.");
                    break;

                case 2:
                    if (!validarCuentaCreada()) {
                        break;
                    }
                    System.out.println("Saldo actual: "
                            + df.format(wallet.getSaldo()) + " "
                            + wallet.getMoneda().getCodigo()
                            + " (" + wallet.getMoneda().getSimbolo() + ")");
                    break;

                case 3:
                    if (!validarCuentaCreada()) {
                        break;
                    }
                    System.out.print("Ingrese monto a depositar: ");

                    if (!scanner.hasNextDouble()) {
                        System.out.println("Por favor, ingrese un número válido.");
                        scanner.nextLine(); // Limpiar entrada no válida
                        break;
                    }
                    double deposito = scanner.nextDouble();
                    scanner.nextLine();
                    wallet.depositar(deposito);
                    System.out.println("Nuevo saldo: "
                            + df.format(wallet.getSaldo()) + " "
                            + wallet.getMoneda().getCodigo()
                            + " (" + wallet.getMoneda().getSimbolo() + ")");
                    break;

                case 4:
                    if (!validarCuentaCreada()) {
                        break;
                    }
                    System.out.print("Ingrese monto a retirar: ");

                    if (!scanner.hasNextDouble()) {
                        System.out.println("Por favor, ingrese un número válido.");
                        scanner.nextLine(); // Limpiar entrada no válida
                        break;
                    }
                    double retiro = scanner.nextDouble();
                    scanner.nextLine();
                    if (wallet.retirar(retiro)) {
                        System.out.println("Retiro exitoso. Nuevo saldo: "
                                + df.format(wallet.getSaldo()) + " "
                                + wallet.getMoneda().getCodigo()
                                + " (" + wallet.getMoneda().getSimbolo() + ")");
                    } else {
                        System.out.println("Fondos insuficientes.");
                    }
                    break;

                case 5:

                    if (!validarCuentaCreada()) {
                        break;
                    }

                    System.out.println("Seleccione moneda destino:");
                    Moneda monedaDestino = seleccionarMoneda();

                    double convertido = wallet.convertirSaldo(monedaDestino.getCodigo());
                    System.out.println("Saldo convertido: "
                            + df.format(convertido) + " "
                            + monedaDestino.getCodigo()
                            + " (" + monedaDestino.getSimbolo() + ")");
                    break;
                case 0:
                    System.out.println("Gracias por usar AlkeWallet.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
