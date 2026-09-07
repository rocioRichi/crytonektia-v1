// Indica dónde está esta clase dentro del proyecto.
package konectia.cryptonectia.modelo.dao;

// Permite crear objetos Curso a partir de las filas de MySQL.
import konectia.cryptonectia.modelo.entidad.Curso;

// Clases JDBC: conexión, consulta preparada, resultado y errores SQL.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Clases para crear y devolver una lista de cursos.
import java.util.ArrayList;
import java.util.List;

// Clase encargada de consultar la tabla curso.
public class CursoDAO {

    // Método que devolverá una lista de cursos activos.
    public List<Curso> listarActivos() {

        // Consulta SQL: solo selecciona las filas cuyo activo es TRUE.
        String sql = """
                SELECT id, nombre, horas, activo
                FROM curso
                WHERE activo = TRUE
                """;

        // Lista vacía donde iremos guardando los cursos encontrados.
        List<Curso> cursos = new ArrayList<>();

        // Abre los tres recursos necesarios.
        // Java los cerrará automáticamente al salir de este bloque.
        try (
                // Pide a ConexionDB una sesión abierta con MySQL.
                Connection conexion = ConexionDB.conectar();

                // Entrega el SELECT a MySQL como consulta preparada.
                PreparedStatement sentencia = conexion.prepareStatement(sql);

                // Ejecuta el SELECT y recibe las filas encontradas.
                ResultSet resultado = sentencia.executeQuery()
        ) {

            // Mientras queden filas por leer en el resultado...
            while (resultado.next()) {

                // Toma los cuatro valores de la fila actual
                // y crea un objeto Curso en Java.
                Curso curso = new Curso(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getInt("horas"),
                        resultado.getBoolean("activo")
                );

                // Añade ese objeto a la lista.
                cursos.add(curso);
            }

            // Si MySQL da un error, interrumpe el método e informa del motivo.
        } catch (SQLException e) {
            throw new RuntimeException(
                    "No se pudieron listar los cursos activos", e
            );
        }

        // Devuelve la lista: tendrá dos cursos ahora,
        // porque el tercero de prueba tiene activo = FALSE.
        return cursos;
    }
    public List<Curso> listarTodos() {

        // No contiene WHERE porque no queremos filtrar filas.
        String sql = """
            SELECT id, nombre, horas, activo
            FROM curso
            """;

        List<Curso> cursos = new ArrayList<>();

        try (
                Connection conexion = ConexionDB.conectar();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                ResultSet resultado = sentencia.executeQuery()
        ) {
            while (resultado.next()) {

                Curso curso = new Curso(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getInt("horas"),
                        resultado.getBoolean("activo")
                );

                cursos.add(curso);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "No se pudieron listar todos los cursos", e
            );
        }

        return cursos;
    }

    public int insertar(Curso curso) {

        // No incluimos id porque MySQL lo genera con AUTO_INCREMENT.
        // Los interrogantes reservan el lugar de los valores.
        String sql = """
            INSERT INTO curso (nombre, horas, activo)
            VALUES (?, ?, ?)
            """;

        // Solo abrimos Connection y PreparedStatement.
        // INSERT no devuelve filas, por eso no necesita ResultSet.
        try (
                Connection conexion = ConexionDB.conectar();
                PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {
            // El primer interrogante recibe el nombre.
            sentencia.setString(1, curso.getNombre());

            // El segundo recibe las horas.
            sentencia.setInt(2, curso.getHoras());

            // El tercero recibe true o false.
            sentencia.setBoolean(3, curso.isActivo());

            // Ejecuta el INSERT y devuelve el número de filas insertadas.
            return sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "No se pudo insertar el curso", e
            );
        }
    }
}