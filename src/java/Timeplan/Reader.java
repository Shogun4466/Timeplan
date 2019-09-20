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

    ArrayList table;
    List firstRow;
    int antKoll;
    int antRader;

    public Reader(String filSted) throws FileNotFoundException {
        if (filSted.contains(".csv")) {
            File f = new File(filSted);
            try (Scanner scn = new Scanner(f)) {
                table = new ArrayList();
                String rad = scn.nextLine();
                String str[] = rad.split(",");
                List<String> firstRow = new ArrayList<String>();
                firstRow = Arrays.asList(str);
                //firstRow.add(al);
                Iterator it = firstRow.iterator();
                antKoll = 0;
                while (it.hasNext()) {
                    antKoll++;
                    it.next();
                }
                while (scn.hasNext()) {
                    table.add(scn.next());
                    antRader++;
                }
                System.out.println("Antall rader: " +antRader);
                System.out.println("Ant kollonner: "+antKoll);
            }
        }
        else    {
            System.out.println("Whoops. Feil filtype. Prøv igjen. Eventuelt spør utvikler pent om å legge til støtte for denne filtypen.");
        }
    }
    
    void insertExcelDatabase(int antRader)  {
        int i = 0;
        ArrayList Studieprogram = new ArrayList();
        
        Query query = new Query();
        query.update("insert into "+firstRow.get(0)+" value ("+table.get(0)+")");
    }

    void printRow1() {
        Iterator it = firstRow.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }

    void printTabell() {
        Iterator it = table.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }
}
