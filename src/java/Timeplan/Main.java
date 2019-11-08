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
        /*query.update("INSERT INTO `Emnekode m/versjon` VALUES ('objekt2');"
                + "INSERT INTO `Emnekomb nivå 2` VALUES ('emnekombkomb', 'emnekombniv2');");
        */
        rdr.insertExcelDatabase(query);
        rdr.executeBatch(query);
        ResultSet rs = query.query("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='timeplan';");
        rs.next();
        System.out.println(rs.getString(1));
        query.close();
        //rdr.executeBatch(query);
        //System.out.println("REEEE: "+rs.getString(1));
        /*rdr.printRow1();
        rdr.printTabell();*/
        /*Query query = new Query();
        ResultSet rs = null;
        rs = query.query("select * from Studieprogram");    //test
        rs.next();
        System.out.println(rs.getString(1));    //test
        query.close();*/
    }
}

    /*try {

            File f = new File("C:/Users/Sondre/Dropbox/IT og informasjonssystemer/Praksisplass/Emne_data_TestUttrekk.csv");
            
            Workbook wb=Workbook.getWorkbook(f);
            Sheet s=wb.getSheet(0);
            //Iterator<Row> iterator = datatypeSheet.iterator();
            int row=s.getRows();
            int col=s.getColumns();
            for (int i = 0; i < row; i++)   {
                for (int j = 0; j<col; j++) {
                    Cell c=s.getCell(j, i);
                    System.out.print(c.getContents());
                }
                System.out.println("");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }

                }
                System.out.println();
     */