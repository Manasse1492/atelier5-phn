import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private String userName="root";
    private String userPass="";
    private  String URL="jdbc:mysql://localhost:3306/bibliotheque_db";
    public Connection connect() throws SQLException {
    Connection connection= DriverManager.getConnection(URL,userName,userPass);
    System.out.println("connection reussi");
    return  connection;
    }

}
