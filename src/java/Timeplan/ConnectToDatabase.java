/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Timeplan;

import java.sql.*;

/**
 *
 * @author Sondre
 */
public class ConnectToDatabase {
    private Connection myConnection;
    /**
     * Creates a new instance of MyDBConnection
     */
    public ConnectToDatabase() {
    }

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/timeplan?autoReconnect=true&useSSL=false", "root", "root");
            System.out.println("Connection established");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Failed to get connection");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return myConnection;
    }

    public void close(ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
    }

    public void close(java.sql.Statement stmt) {

        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
            }
        }
    }

    public void destroy() {
        if (myConnection != null) {
            try {
                myConnection.close();
            } catch (Exception e) {
            }
        }
    }
}
