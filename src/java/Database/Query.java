/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Timeplan.*;
import Timeplan.Reader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sondre
 */
public class Query {

    private Database ctd;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement stmnt;

    //Setter opp objektet med connection klar for statemtents
    public Query() {
        ctd = new Database();
        ctd.init();
        con = ctd.getConnection();
    }

    //For insert, update og delete etc.
    public void update(String Query) {
        try {
            stmnt = con.prepareStatement(Query);
            stmnt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Insert "objekter" inn i "table"
    public void addBatch(String objekter, String table) throws SQLException {
        //try {
            con.setAutoCommit(false);
            PreparedStatement stmnt = null;
            //stmnt = con.prepareStatement("INSERT INTO `"+table+"` VALUES ('"+objekter+"');");
            System.out.println("Table: "+table);
            System.out.println("objekter: "+objekter);
            //stmnt.setString(1, objekter);
            stmnt = con.prepareStatement("INSERT INTO `"+table+"` VALUES ('"+objekter+"');");
            stmnt.executeUpdate();
            //this.stmnt.clearBatch();
        //} catch (BatchUpdateException ex) {
          //  Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }
    
    public void addBatchEmnekode(String objekt1, String objekt2, String table) throws SQLException {
        con.setAutoCommit(false);
        PreparedStatement stmnt = null;
        /*System.out.println("Table: "+table);
        System.out.println("objekt1: "+objekt1);
        System.out.println("Objekt2: "+objekt2);*/
        stmnt = con.prepareStatement("INSERT INTO `"+table+"` VALUES ('"+objekt1+"', '"+objekt2+"');");
        stmnt.executeUpdate();
    }
    
    public void executeBatch() {
        try {
            con.commit();
            int[] updates = stmnt.executeBatch();
            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
        }
    }

    //for select. returnerer resultset
    public ResultSet query(String Query) {
        try {
            stmnt = con.prepareStatement(Query);
            rs = stmnt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    //Legger en fil inn i ett statement gjennom inputstream og legger inn i database

    public void insertFile(String query, InputStream is) {
        try {
            stmnt = con.prepareStatement(query);
            stmnt.setBlob(1, is);
            stmnt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            stmnt.close();
            con.close();
            ctd.destroy();
        } catch (SQLException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
