package konectia.cryptonectia;

import konectia.cryptonectia.modelo.dao.CursoDAO;
import konectia.cryptonectia.modelo.entidad.Curso;

public class PruebaInsertaCurso {

    public static void main(String[] args) {

        CursoDAO cursoDAO = new CursoDAO();

        // Se crea sin id porque MySQL lo asignará automáticamente.
        Curso nuevoCurso = new Curso(
                "Criptografía aplicada",
                30,
                true
        );

        // insertar() devuelve cuántas filas ha insertado.
        int filasInsertadas = cursoDAO.insertar(nuevoCurso);

        if (filasInsertadas == 1) {
            System.out.println("Curso insertado correctamente");
        } else {
            System.out.println("No se insertó el curso");
        }

        // Comprueba que el nuevo curso está ahora en la base de datos.
        for (Curso curso : cursoDAO.listarTodos()) {
            System.out.println(
                    curso.getId() + " | " +
                            curso.getNombre() + " | " +
                            curso.getHoras() + " horas | activo: " +
                            curso.isActivo()
            );
        }
    }
}