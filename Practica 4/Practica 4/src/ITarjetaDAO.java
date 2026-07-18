import java.util.List;

public interface ITarjetaDAO {
    boolean crear(Tarjeta tarjeta);
    List<Tarjeta> leerPorUsuario(int usuarioId);
    boolean actualizarSaldo(int id, double nuevoSaldo);
    boolean eliminar(int id);
}