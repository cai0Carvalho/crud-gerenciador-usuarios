package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionUtil {
    private static final Logger logger = Logger.getLogger(ConnectionUtil.class.getName());
    private static final String URL = "jdbc:postgresql://localhost:5432/gerenciador_usuarios";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Bolinha23@";

    public static Connection getConnection(){

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            return connection;
        } catch(SQLException e){
            logger.log(Level.SEVERE, "Erro ao conectar ao banco de dados", e);
        }
        return null;
    }
}
