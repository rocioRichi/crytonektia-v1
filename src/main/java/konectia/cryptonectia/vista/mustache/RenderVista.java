package konectia.cryptonectia.vista.mustache;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileReader;
import java.io.IOException;

public class RenderVista {

    public static void renderizar(
            HttpServletResponse response,
            String rutaPlantilla,
            Object datos
    ) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (FileReader archivo = new FileReader(rutaPlantilla)) {

            MustacheFactory fabrica = new DefaultMustacheFactory();

            Mustache plantilla = fabrica.compile(
                    archivo,
                    rutaPlantilla
            );

            plantilla.execute(
                    response.getWriter(),
                    datos
            );
        }
    }
}