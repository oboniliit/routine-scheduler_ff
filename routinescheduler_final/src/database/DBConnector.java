/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Farhin
 */
public final class DBConnector {
    
    private static final String  USER_NAME = "root";
    private static final String  USER_PASSWORD = "";
    private static final String  CONNECTION_STRING = "jdbc:mysql://localhost:3306/routinescheduler";
    public Connection conn;
    public static DBConnector handler = null;
    
    private DBConnector(){
        
        createNewConnection();
    }
    
    public static DBConnector getInstance(){
        if(handler==null){
            handler = new DBConnector();
        }
        return handler;
    }
    
    void createNewConnection(){
        try{
            conn = (Connection) DriverManager.getConnection(CONNECTION_STRING, USER_NAME,USER_PASSWORD);
            System.err.println("Connected to DB.");
        }catch(SQLException EX){
            System.err.println(EX);
        }
    }
    
    
}
