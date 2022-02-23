import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataBaseConnection {
    final static String className = "com.mysql.cj.jdbc.Driver";
    final static String url = "jdbc:mysql://localhost:3306/Shopping";
    final static String uname = "root";
    final static String pass= "Nitin@123";
    public static Connection getDataBaseConnection() throws Exception{
        PreparedStatement stmt = null;
        Class.forName(className);
        Connection connect = DriverManager.getConnection(url,uname,pass);
        return connect;
    }
}
