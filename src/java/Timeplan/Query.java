/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Timeplan;
import Timeplan.*;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sondre
 */
 public class Query {
     private ConnectToDatabase ctd;
     private Connection con;
     private ResultSet rs;
     private PreparedStatement stmnt;

     //Setter opp objektet med connection klar for statemtents
     public Query(){
         ctd = new ConnectToDatabase();
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
             stmnt.setBlob(1,is);
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
