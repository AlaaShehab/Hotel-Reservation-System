package Model;

import java.sql.*;

public class SQLConnection {
    private static Connection connection = null;
    private static String databaseName=  "hotel reservation system";
    private static String databaseURL=  "jdbc:mysql://127.0.0.1:3306/"+databaseName;
    private static String userName = "root";
    private static String password = "password";
    private static SQLConnection connect;

    private SQLConnection() {
        DBConnect();
    }

    public static SQLConnection getInstance(){
        if (connect == null){
            connect = new SQLConnection();
        }
        return connect;
    }

    public Connection getConnection(){
        return connection;
    }
    public void DBConnect(){
        try {
            connection = DriverManager.getConnection(
                    databaseURL,userName,password);
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public ResultSet getData(String query)throws SQLException {
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        return rs;
    }

}