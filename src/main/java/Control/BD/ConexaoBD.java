package Control.BD;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/gymflow";
    private static final String USER = "postgres";
    private static final String SENHA = "gymflow";
    private static final String DRIVER = "org.postgresql.Driver";

    public static Connection conectar() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, SENHA);
    }
}
