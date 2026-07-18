import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TarjetaDAOImpl implements ITarjetaDAO {

    @Override
    public boolean crear(Tarjeta tarjeta) {
        String sql = "INSERT INTO Tarjeta (usuario_id, clave, numero, fecha_exp, saldo, tipo, limite_credito, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, tarjeta.getUsuarioId());
            ps.setString(2, tarjeta.getClave());
            ps.setString(3, tarjeta.getNumero());
            ps.setDate(4, java.sql.Date.valueOf(tarjeta.getFechaExp()));
            ps.setDouble(5, tarjeta.getSaldo()); // Por defecto 0
            ps.setString(6, tarjeta.getTipo());


            if (tarjeta.getTipo().equalsIgnoreCase("Crédito")) {
                ps.setDouble(7, tarjeta.getLimiteCredito());
            } else {
                ps.setNull(7, java.sql.Types.DECIMAL); // Si es débito, es nulo
            }

            ps.setBoolean(8, tarjeta.isActivo());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear tarjeta: " + e.getMessage());
            return false;
        }
    }


    @Override public List<Tarjeta> leerPorUsuario(int usuarioId) { return null; }
    @Override public boolean actualizarSaldo(int id, double nuevoSaldo) { return false; }
    @Override public boolean eliminar(int id) { return false; }
}