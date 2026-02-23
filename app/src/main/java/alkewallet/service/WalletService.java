package alkewallet.service;

import java.math.BigDecimal;
import java.util.List;

import alkewallet.UsuarioRepository;
import alkewallet.convertidor.ConvertidorMoneda;
import alkewallet.convertidor.ConvertidorMonedaSimple;
import alkewallet.model.Moneda;
import alkewallet.model.Transaccion;
import alkewallet.model.Usuario;

public class WalletService {

    private final UsuarioRepository usuarioRepo;
    private final ConvertidorMoneda convertidor;

    public WalletService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
        this.convertidor = new ConvertidorMonedaSimple();
    }

    public Usuario login(int userId) {
        return usuarioRepo.obtenerUsuario(userId);
    }

    public BigDecimal consultarSaldo(int userId) {
        return usuarioRepo.obtenerSaldo(userId);
    }

    public void depositar(int userId, BigDecimal monto) {
        usuarioRepo.depositar(userId, monto);
    }

    public void retirar(int userId, BigDecimal monto) {
        usuarioRepo.retirar(userId, monto);
    }

    public void transferir(int senderId, int receiverId, BigDecimal monto) {
        usuarioRepo.transferir(senderId, receiverId, monto);
    }

    public BigDecimal convertirSaldo(int userId, Moneda destino) {

        Usuario usuario = usuarioRepo.obtenerUsuario(userId);

        return convertidor.convertir(
                usuario.getSaldo(),
                Moneda.CLP,
                destino
        );
    }

    public List<Transaccion> obtenerHistorial(int userId) {
        return usuarioRepo.obtenerHistorial(userId);
    }
}