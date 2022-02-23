import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainClass {
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;

    public static void dataBaseConnect() throws Exception {
        connection = DataBaseConnection.getDataBaseConnection();
    }

    /*
    Question 1:
     Add a method which takes (pid: String, pname: String, price: Int) as input parameter .
    and inserts into the product table. Add another methode for cart table
     */
    public static void recordInsertProductsTable(int pid, String name, int price) throws Exception {
        preparedStatement = connection.prepareStatement("INSERT INTO products (pid,pname, price) values (?, ?,?)");
        preparedStatement.setInt(1, pid);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, price);
        preparedStatement.executeUpdate();
        System.out.println("Record Successfully Inserted into Products table  ");
    }

    public static void recordInsertedCartDataTable(int pid, int qty) throws Exception {
        preparedStatement = connection.prepareStatement("INSERT INTO cartdata (pid,qty) values (?, ?)");
        preparedStatement.setInt(1, pid);
        preparedStatement.setInt(2, qty);
        preparedStatement.executeUpdate();
        System.out.println("Record Successfully Inserted into CartData table ");
    }
    /*
    Question 2:
     Add a method which queries the product table as per given pid.
     It should return Optional.empty if no records are found.
     */

    public static void printSelectDataUsingId(int pid) throws Exception {
        String query = "SELECT * FROM products WHERE products.pid =" + pid;
        preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            System.out.println(rs.getString(2) + " " + rs.getInt(3));
        } else {
            System.out.println("Empty");
        }

    }
    /*
    Question 3:
     Write a method to find the average price of all the products
     */

    public static void averagePrice() throws Exception {
        String query = "SELECT pid ,AVG(price) AS 'Avg Price' FROM products GROUP BY pid";
        preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            System.out.println("Product id " + rs.getInt(1) + " with no of  quantity " + rs.getInt(2));
        }
    }

    public static void main(String[] args) throws Exception {
        dataBaseConnect();
        recordInsertProductsTable(22, "NewProduct_Sachin", 44);
        recordInsertedCartDataTable(10, 4);
        printSelectDataUsingId(3);
        averagePrice();
        preparedStatement.close();
        connection.close();
    }


}
