package utils;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    public static Connection connectDB(String url){ //url = "jdbc:mySQL://localhost:3306/cnw";
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            String username = "root";
            String password = "";
            conn =  DriverManager.getConnection(url, username, password);

        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return conn;
    }

    public static void disconnectDB(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
