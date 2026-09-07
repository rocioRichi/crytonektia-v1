package konectia.cryptonectia;

import konectia.cryptonectia.modelo.dao.CursoDAO;
import konectia.cryptonectia.modelo.entidad.Curso;

import java.util.List;

public class PruebaCursoDAO {

    public static void main(String[] args) {

        CursoDAO cursoDAO = new CursoDAO();

        // Comprueba listarTodos().
        List<Curso> todos = cursoDAO.listarTodos();

        System.out.println("TODOS: " + todos.size());

        for (Curso curso : todos) {
            System.out.println(
                    curso.getId() + " | " +
                            curso.getNombre() + " | activo: " +
                            curso.isActivo()
            );
        }

        // Comprueba listarActivos().
        List<Curso> activos = cursoDAO.listarActivos();

        System.out.println("ACTIVOS: " + activos.size());

        for (Curso curso : activos) {
            System.out.println(
                    curso.getId() + " | " +
                            curso.getNombre() + " | activo: " +
                            curso.isActivo()
            );
        }
    }
}