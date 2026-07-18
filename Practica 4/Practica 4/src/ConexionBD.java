import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:sqlserver://DESKTOP-6OFAL93:1433;databaseName=SistemaTarjetas;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "java";
    private static final String PASSWORD = "12345";

    public static Connection getConexion() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return conexion;
    }
}