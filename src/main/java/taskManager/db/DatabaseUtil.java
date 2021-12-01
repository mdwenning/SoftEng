package taskManager.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    static Connection conn;
    protected static Connection connect() throws Exception {
        if (conn != null) { return conn; }

        String dbUsername = System.getenv("dbUsername");
        if (dbUsername == null) {
            System.err.println("Environment variable dbUsername is not set!");
        }
        String dbPassword = System.getenv("dbPassword");
        if (dbPassword == null) {
            System.err.println("Environment variable dbPassword is not set!");
        }
        String rdsMySqlDatabaseUrl = System.getenv("rdsMySqlDatabaseUrl");
        if (rdsMySqlDatabaseUrl == null) {
            System.err.println("Environment variable rdsMySqlDatabaseUrl is not set!");
        }

        try {
            //System.out.println("start connecting......");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    System.getenv("jdbcTag") + rdsMySqlDatabaseUrl + ":" + System.getenv("rdsMySqlDatabasePort") + "/" + System.getenv("dbName") + System.getenv("multiQueries"),
                    dbUsername,
                    dbPassword);
            //System.out.println("Database has been connected successfully.");
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failed in database connection");
        }
    }

}
