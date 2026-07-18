import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAOImpl implements IUsuarioDAO {

    @Override
    public boolean crear(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nombre, apellido, edad, email, password, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setInt(3, usuario.getEdad());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getPassword());
            ps.setBoolean(6, usuario.isActivo());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Usuario iniciarSesion(String email, String password) {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND password = ? AND activo = 1";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                // Setear demás atributos...
                return u;
            }
        } catch (SQLException e) {
            System.err.println("Error en login: " + e.getMessage());
        }
        return null;
    }


    @Override public List<Usuario> leerTodos() { return null; }
    @Override public boolean actualizar(Usuario usuario) { return false; }
    @Override public boolean eliminar(int id) { return false; }
}