package com.track.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB {
    private DB(){
        //since the constructor is private so object cant be made
    }
    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        // step 1 load a driver class
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/expense_tracker","root","#avighyat1234");
        if(connection != null){
            System.out.println("connection created");
        }
        return connection;

    }
}
