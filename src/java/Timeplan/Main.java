package Timeplan;

import Database.Query;
import java.io.File;
import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
import java.sql.BatchUpdateException;
import java.sql.DatabaseMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Main {

    public static void main(String[] args) throws Exception {
        Reader rdr = new Reader();
        rdr.readExcel("C:/Users/Sondre/Dropbox/IT og informasjonssystemer/IS-302 Praksisplass/Emne_data_TestUttrekk.csv");
        Query query = new Query();
        rdr.insertExcelDatabase(query);
        query.close();
    }
}
