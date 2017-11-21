package Model;

import java.sql.*;

public class ConnectionDatabase {

    private static final String URL = "jdbc:postgresql://localhost:5432/banco1";
    private static final String USER = "postgres";
    private static final String PWD = "123";

    public static Connection getConnection() {
        System.out.println("Conectando ao Banco de Dados");
        try {
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}






