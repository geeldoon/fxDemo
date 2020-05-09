package home.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    //database connection
   private static final String driver = "com.mysql.jdbc.Driver";
    private static final String dbUrl ="jdbc:mysql://localhost/school_demo";
    private static final String dbUser ="root";
    private static final String dbPassword = "123456";
    private static Connection connection;

    public static Connection connect(){
        try {
            //Regisgter the driver
            Class.forName(driver);

            //establish the connection
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  connection;
    }

}
