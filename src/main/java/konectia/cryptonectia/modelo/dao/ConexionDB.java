// Esta clase pertenece al paquete de acceso a datos.
package konectia.cryptonectia.modelo.dao;

// Connection representa una conexión abierta con la base de datos.
import java.sql.Connection;

// DriverManager es la clase Java que pide al conector MySQL abrir esa conexión.
import java.sql.DriverManager;

// SQLException representa un problema al conectar o al trabajar con SQL.
import java.sql.SQLException;

public class ConexionDB {

    // URL: protocolo JDBC + tipo de servidor + ordenador + puerto + base de datos.
    private static final String URL =
            "jdbc:mysql://localhost:3306/crytonektia_db";
    // Usuario con el que DBeaver entra en MariaDB.
    private static final String USER = "root";

    // Contraseña de ese usuario.
    // Si DBeaver entra con root sin contraseña, se deja vacía.
    private static final String PASSWORD = "";

    // Método que cualquier DAO llamará cuando necesite una conexión.
    public static Connection conectar() throws SQLException {

        // DriverManager usa la URL, usuario y contraseña.
        // Si todo es correcto, devuelve una Connection abierta.
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}