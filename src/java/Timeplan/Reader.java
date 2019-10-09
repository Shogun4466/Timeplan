/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Timeplan;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import Database.Query;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author Sondre
 */
public class Reader {

    List table0;
    List table1;
    List table2;
    List table3;
    List table4;
    List table8;
    List table9;
    List table10;
    List table11;
    List table12;
    List table13;
    
    List firstRow;
    int antKoll;
    int antRader;
    int tot;

    public Reader(){
    }
    
    void readExcel (String filSted) throws FileNotFoundException    {
        if (filSted.contains(".csv")) {
            File f = new File(filSted);
            try (Scanner scn = new Scanner(f)) {
                int i = 0;
                ArrayList<String> tables[]=new ArrayList[i];
                while (scn.hasNextLine())   {
                    String rad = scn.nextLine();
                    String str[] = rad.split(",");
                    tables[i]=new ArrayList<String>();
                    firstRow = Arrays.asList(str);
                    i++;
                }
                /*int i = 0;
                while (scn.hasNextLine()) {
                    String rad = scn.nextLine();
                    String str[] = rad.split(",");
                    List<String> firstRow = new ArrayList<String>();
                    firstRow = Arrays.asList(str);
                    i++;
                }*/
                Iterator it = firstRow.iterator();
                antKoll = 0;
                while (it.hasNext()) {
                    antKoll++;
                    it.next();
                }
                i = 0;
                while (i<antKoll)   {
                    
                }
                while (scn.hasNext()) {
                    if (i!=5 && i<antKoll)  {
                        tables[i].add(scn.next());
                        i++;
                    }
                    System.out.println(antRader);
                    if (i == 5 && i<antKoll)  {
                        //table5.add(scn.next());
                        System.out.println(scn.next());
                        i=i+3;
                    }
                    if (i==antKoll)   {
                        antRader++;
                        i=0;
                    }
                }
                System.out.println("Antall rader: " +antRader);
                System.out.println("Ant kollonner: "+antKoll);
            }
        }
        else    {
            System.out.println("Whoops. Feil filtype. Prøv igjen. Eventuelt spør utvikler pent om å legge til støtte for denne filtypen.");
        }
    }
    
    void readDatabase() {
        
    }
    
    /*void insertExcelDatabase(int antRader)  {
        int tot = 0;
        int t = 0;
        
        Query query = new Query();
        
        
        if (i == 0 && i<antKoll) {  //koll 1
            
        }
        if (i == 0 && i<antKoll) {
            
        }
        if (i == 0 && i<antKoll) {
            
        }
        if (i == 0 && i<antKoll) {
            
        }
        if (i == 0 && i<antKoll) {
            
        }
        if (i == 0 && i<antKoll) {
            
        }
        if (i == 0 && i<antKoll) {
            
        }
        if (i == 0 && i<antKoll) {
            
        }
        if (i == 0 && i<antKoll) {
            
        }
        if (i == 0 && i<antKoll) {
            
        }
        if (antKoll == i && tot < antRader)   {   //Ny rad
            i=0;
        }
        
    }
*/
    void printRow1() {
        Iterator it = firstRow.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }

    /*void printTabell() {
        Iterator it = table.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }*/
}
