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
import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
/**
 *
 * @author Sondre
 */
public class Reader {
    
    //List firstRow;
    ArrayList<ArrayList<String>> listeUtifraKoll;
    ArrayList listOfCells;
    List<String> csvFRad;
    int antKoll;
    int antRader;
    int antElementer;
    int j;
    int i;
    int x;

    public Reader(){
    }
    
    void readExcel (String filSted) throws FileNotFoundException    {
        if (filSted.contains(".csv")) {
            File f = new File(filSted);
            try (Scanner scn = new Scanner(f)) {
                i = 0;
                //tables=new ArrayList[i];
                String fRad = scn.nextLine();   //Gir første rad
                System.out.println(fRad);       //Dobbeltsjekker i output
                //Separerer utifra komma, setter første rad inn i en egen arraylist
                String[] csvFCeller = fRad.split(",");
                csvFRad = Arrays.asList(csvFCeller);
                /*for (i=0; i < antKoll; i++) {
                    Object element = csvFRad.get(i);
                    String elementString = element.toString();
                    firstRow.add(elementString);
                }*/
                //firstRow = new ArrayList(csvFRad);
                antKoll = csvFRad.size();
                System.out.println("antall kollonner: "+antKoll);
                //String alleRader = "";          //Initsierer utenfor while-løkken
                ArrayList alleRaderAL = new ArrayList();
                while (scn.hasNext())   {   
                    alleRaderAL.add(scn.next());
                    //alleRader = alleRader.concat(scn.nextLine());
                    //String rad = scn.nextLine();
                    //String str[] = rad.split(",");
                    //List<String> tables[i] = Arrays.asList(str);
                    //tables[i]=new ArrayList<String>();
                    antRader++;
                }
                //alleRader inneholder nå hele .csv listen som en String.
                String a = alleRaderAL.toString();  //DENNE SKAPER PROBLEMET PÅ START OG SLUTT
                String alleRader = a.replaceAll("]$","");   //FUNGERER FOR SISTE, MEN IKKE FØRSTE. REEEEE.
                System.out.println("Reeee: "+ alleRader);  //Dobbelsjekk
                System.out.println("antall rader: "+ antRader); //Trippelsjekk
                //Separerer utifra komma, setter alle cellene i tabellen inn i arraylist
                String[] csvCeller = alleRader.split(",");
                List<String> csvCells = Arrays.asList(csvCeller);
                listOfCells = new ArrayList(csvCells);
                
                //Sjekker om alt ser ut som det skal
                System.out.println("Listen: " + listOfCells);
                System.out.println("Ant elementer: " + listOfCells.size());
                antElementer = listOfCells.size();
                //String [] excelString = csv.split(",");
                
                //Lager antall kollonner mengde med arraylister inn i en overliggende arraylist.
                listeUtifraKoll = new ArrayList<>(antKoll);
                System.out.println("Vi har laget: " + antKoll + " antall arraylists i listeUtifraKoll");
                i=0;
                //Lager antKoll mengder arraylists i listeUtifraKoll
                for (i=0; i < antKoll; i++)   {
                    listeUtifraKoll.add(new ArrayList());
                }
                System.out.println("Dobbelsjekk at tallet over stemmer med dette: "+listeUtifraKoll.size());
                System.out.println("Hva er på plass 12?" +listOfCells.get(12));
                System.out.println("Hei, vi begynner herved innsetting. Vennligst vent.");
                x = 0;
                /*Itererer gjennom original liste og legger inn i arraylist(for j) inni en arraylist (for i).
                For å visualisere; betyr dette at første for løkken tar for seg hele excel arket.
                Andre løkken tar for seg hver kolonne. Etter hver gang andre løkke er ferdig med en kolonne, øker x med 1 for å gå videre til neste kollonne.
                While løkken er for å forsikre om at for løkken ikke itererer gjennom flere celler enn de som finnes i excel arket.*/
                while (x < antKoll) {
                    for (i=0; i < antKoll; i++)    {
                        for (j=x; j < antElementer; j=j+antKoll)    {
                            Object element = listOfCells.get(j);
                            String elementString = element.toString();
                            listeUtifraKoll.get(x).add(elementString);
                        }
                        x++;
                    }
                }
                System.out.println("Innsetting ferdig. Takk for at du ventet. ");
                /*listeUtifraKoll.get(0) betyr å hente kollonne 0 (a). 2 vil tilsvare C osv.
                .get(0).get(i) betyr å hente i fra kollonne 0 fra plass i i listen; i dette tilfelle iterere videre 
                i listen frem til kondisjonen i midten av løkken er møtt.*/
                for (i=0; i<5; i++)    {   //erstatt i<5 med antRader for hele listen
                    System.out.println(listeUtifraKoll.get(0).get(i));
                    System.out.println(listeUtifraKoll.get(2).get(i));
                    System.out.println(listeUtifraKoll.get(9).get(i));
                }
                }
        }
        else    {
            System.out.println("Whoops. Feil filtype. Prøv igjen. Eventuelt spør utvikler pent om å legge til støtte for denne filtypen.");
        }
    }
    
    void readDatabase(Query query) {
         //Videre til å sette inn i databasen.
         /*
                while(listOfCells.contains(i))  {
                    query.update
                }*/
    }
    
    void insertExcelDatabase(Query query) throws SQLException  {
        try {
        //x=0;
        int y=0;
        String objekter;
        String table;
        String emnekode;
        String emnekombniv2;
        String emnekombniv3;
        String emnekombniv4;
        String emnekomb;
        String emnekombinasjon;
        String emnevalgsstatkode;
        String semester;
        String Studieprogramkode;
        String terminnrDefault;
        String terminnrRS;
        String table2;
        String table3;
        String table4;
        String table5;
        System.out.println("Antall rader: "+antRader+" og antall kolonner: "+antKoll+" og antall elementer: "+antElementer);
        //while (x < antElementer) {
            for (i=0; i < antKoll; i++)    {
                System.out.println("Begynner på rad: "+i);
                for (j=0; j < antRader; j++)    {
                    //HVIS DET ER FØRSTE RAD, SÅ MÅ VI LEGGE TING TIL PÅ EN LITT DUM MÅTE FORELØBIG:
                    if (i==0)   {
                    emnekode = listeUtifraKoll.get(i).get(j);
                    emnekombniv2 = listeUtifraKoll.get(i+1).get(j)+j;
                    emnekombniv3 = listeUtifraKoll.get(i+2).get(j)+j;
                    emnekombniv4 = listeUtifraKoll.get(i+3).get(j)+j;
                    emnekomb = "emnekomb"+j;
                    table = csvFRad.get(i);
                    System.out.println(csvFRad.get(i));
                    table2 = "Emne";
                    table3 = csvFRad.get(i+1);
                    table4 = csvFRad.get(i+2);
                    table5 = csvFRad.get(i+3);
                    query.addBatchEmnekode(emnekode, emnekomb, emnekombniv2, emnekombniv3, emnekombniv4, "emnekode m/versjon", table2, table3, table4, table5);
                    y=y+6;
                    //query.executeBatch();
                    System.out.println("Sucess nr: " + y);
                    }   
                    //HVIS DET IKKE ER FØRSTE RADEN:
                    else    {
                    objekter = listeUtifraKoll.get(i).get(j);
                    table = csvFRad.get(i);
                    emnekomb = "emnekomb"+j;
                    query.addBatch(objekter, emnekomb, table);
                    query.executeBatch();
                    y++;
                    System.out.println("Sucess nr: " + y);
                    }
                }
                query.executeBatch();
                System.out.println("Execution complete kolonne: "+i);
                if (i==0)   {
                    i=6;
                }
                //x++;
           // }
        }
        System.out.println("Fiks feri");
        } catch (BatchUpdateException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void executeBatch(Query query) {
        query.executeBatch();
    }

    /*void printTabell() {
        Iterator it = table.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }*/
}
