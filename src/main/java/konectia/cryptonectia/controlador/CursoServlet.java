package konectia.cryptonectia.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import konectia.cryptonectia.modelo.dao.CursoDAO;
import konectia.cryptonectia.modelo.entidad.Curso;
import konectia.cryptonectia.vista.mustache.RenderVista;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cursos")
public class CursoServlet extends HttpServlet {

    private CursoDAO cursoDAO;

    @Override
    public void init() {
        cursoDAO = new CursoDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        List<Curso> cursos = cursoDAO.listarActivos();

        Map<String, Object> datos = new HashMap<>();
        datos.put("cursos", cursos);

        String rutaPlantilla =
                getServletContext().getRealPath("/cursos.html");

        RenderVista.renderizar(
                response,
                rutaPlantilla,
                datos
        );
    }
}