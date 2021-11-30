package taskManager.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    static Connection conn;
    public static Connection connect() throws Exception {
        if (conn != null) { return conn; }
        try {
            //System.out.println("start connecting......");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    System.getenv("jdbcTag") + System.getenv("rdsMySqlDatabaseUrl") + ":" + System.getenv("rdsMySqlDatabasePort") + "/" + System.getenv("schemaName") + System.getenv("multiQueries"),
                    System.getenv("dbUsername"),
                    System.getenv("dbPassword"));
            //System.out.println("Database has been connected successfully.");
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failed in database connection");
        }
    }

}
