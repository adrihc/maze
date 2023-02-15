package com.liceu.maze.utils;



import java.sql.Connection;
import java.sql.DriverManager;



public class Session{
    static Connection connection;
    public static Connection getConnection(){
        if (connection != null){
            return connection;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://mysql/maze",
                    "root",
                    "root"
            );
            return connection;
        }catch (Exception e ){
            throw new RuntimeException(e);
        }
    }
}
