package konectia.cryptonectia;

import konectia.cryptonectia.modelo.dao.ConexionDB;

import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {
        try (Connection conexion = ConexionDB.conectar()) {

            System.out.println("Conexión realizada correctamente");

        } catch (Exception e) {

            System.out.println("No se pudo conectar");
            e.printStackTrace();

        }
    }
}