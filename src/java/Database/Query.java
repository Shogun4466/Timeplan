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

    //Legger inn "objekter" /m objektkombinasjonen inn i "table"
    public void addBatch(String objekter, String objektKomb, String table) throws SQLException {
        con.setAutoCommit(false);
        PreparedStatement stmnt = null;
        stmnt = con.prepareStatement("INSERT IGNORE INTO `" + table + "` VALUES ('" + objekter + "', '" + objektKomb + "');");
        stmnt.addBatch();
        stmnt.executeBatch();
        con.setAutoCommit(true);
    }

    //Samme som den over, men for kolonne 0-6 (A-G i excel). 
    //Benyttet Batch innsetning, men MySQL bug forårsaker at batches kan kun commites en av gangen, hvilket
    //fjerner hensikten, men er like effektivt som om det hadde vært vanlig string innsetning.
    public void addBatchEmnekode(String emnekode, String emnekomb, String emnekombniv2, String emnekombniv3, String emnekombniv4, String table, String table2, String table3, String table4, String table5) throws SQLException {
        con.setAutoCommit(false);
        PreparedStatement stmnt = null;
        stmnt = con.prepareStatement("INSERT IGNORE INTO `" + table + "` VALUES ('" + emnekomb + "', '" + emnekode + "');");
        stmnt.addBatch();
        stmnt.executeBatch();
        stmnt = con.prepareStatement("INSERT IGNORE INTO `" + table3 + "` VALUES ('" + emnekombniv2 + "', '" + emnekomb + "');");
        stmnt.addBatch();
        stmnt.executeBatch();
        stmnt = con.prepareStatement("INSERT IGNORE INTO `" + table4 + "` VALUES ('" + emnekombniv3 + "', '" + emnekombniv2 + "');");
        stmnt.addBatch();
        stmnt.executeBatch();
        stmnt = con.prepareStatement("INSERT IGNORE INTO `" + table5 + "` VALUES ('" + emnekombniv4 + "', '" + emnekombniv3 + "');");
        stmnt.addBatch();
        stmnt.executeBatch();
        con.setAutoCommit(true);
    }

    public void executeBatch() {
        try {
            stmnt.execute();
            //int[] updates = stmnt.executeBatch();     //Skulle vært inkludert med batch innsetning, for å commite hele listen av batches.
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
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
            if (stmnt != null) {
                stmnt.close();
            }
            con.close();
            ctd.destroy();
        } catch (SQLException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
