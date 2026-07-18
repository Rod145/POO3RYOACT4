import java.util.List;

public interface IUsuarioDAO {
    boolean crear(Usuario usuario);
    Usuario iniciarSesion(String email, String password); // Método para el login
    List<Usuario> leerTodos();
    boolean actualizar(Usuario usuario);
    boolean eliminar(int id);
}