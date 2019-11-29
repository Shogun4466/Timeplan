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

    public Reader() {
    }

    void readExcel(String filSted) throws FileNotFoundException {
        if (filSted.contains(".csv")) {
            File f = new File(filSted);
            try (Scanner scn = new Scanner(f)) {
                i = 0;  //For senere bruk.
                String fRad = scn.nextLine();   //Gir første rad
                //Separerer utifra komma fra fRad, setter første rad inn i en egen arraylist (csvFCeller).
                String[] csvFCeller = fRad.split(",");
                csvFRad = Arrays.asList(csvFCeller);
                antKoll = csvFRad.size();
                //Lager ny arraylist og setter resterende verdier inn i en lang arraylist (alleRaderAL) samtidig som vi teller antall celler.
                ArrayList alleRaderAL = new ArrayList();
                while (scn.hasNext()) {
                    alleRaderAL.add(scn.next());
                    antRader++;
                }
                //alleRader inneholder nå hele .csv listen som en String.
                //Neste to linjene fjerner ] fra array konvertering.
                String a = alleRaderAL.toString();
                String alleRader = a.replaceAll("]$", "");
                //Separerer utifra komma, setter alle cellene i tabellen inn i arraylist
                String[] csvCeller = alleRader.split(",");
                List<String> csvCells = Arrays.asList(csvCeller);
                listOfCells = new ArrayList(csvCells);

                //Setter en variabel for totalt antall med celler (med data inni, altså ekskludert kolonnenavn)
                antElementer = listOfCells.size();

                //Lager antall kollonner mengde med arraylister inn i en overliggende arraylist ("nested" arraylist).
                listeUtifraKoll = new ArrayList<>(antKoll);
                i = 0;
                //Lager antKoll mengder arraylists i den overliggende arraylisten
                for (i = 0; i < antKoll; i++) {
                    listeUtifraKoll.add(new ArrayList());
                }

                System.out.println("Vi begynner herved innsetting i listene. Vennligst vent.");
                x = 0;
                /*Itererer gjennom original liste og legger inn i arraylist(for j) inni en arraylist (for i).
                For å visualisere; betyr dette at første for løkken tar for seg hele excel arket.
                Andre løkken tar for seg hver kolonne. Etter hver gang andre løkke er ferdig med en kolonne, øker x med 1 for å gå videre til neste kollonne.
                While løkken er for å forsikre om at for løkken ikke itererer gjennom flere celler enn de som finnes i excel arket.*/
                while (x < antKoll) {
                    for (i = 0; i < antKoll; i++) {
                        for (j = x; j < antElementer; j = j + antKoll) {
                            Object element = listOfCells.get(j);
                            String elementString = element.toString();
                            listeUtifraKoll.get(x).add(elementString);
                        }
                        x++;
                    }
                }
                System.out.println("Innsetting ferdig. Takk for at du ventet. ");
                //listeUtifraKoll.get(0) betyr å hente kollonne 0 (Koll A i Excel). 2 vil tilsvare C, osv.
                //.get(0).get(i) betyr å hente i fra kollonne 0 (A i excel) fra plass i i listen; i dette tilfelle iterere videre 
                //i listen frem til kondisjonen i midten av løkken er møtt.
                for (i = 0; i < 5; i++) {   //erstatt i<5 med antRader for hele listen
                    System.out.println(listeUtifraKoll.get(1).get(i));
                    System.out.println(listeUtifraKoll.get(2).get(i));
                    System.out.println(listeUtifraKoll.get(11).get(i));
                }
            }
        } else {
            System.out.println("Whoops. Feil filtype. Prøv igjen. Eventuelt spør utvikler pent om å legge til støtte for denne filtypen.");
        }
    }

    void insertExcelDatabase(Query query) throws SQLException {
        try {
            int y = 0;
            String objekter;
            String table;
            String emnekode;
            String emnekombniv2;
            String emnekombniv3;
            String emnekombniv4;
            String emnekomb;
            String table2;
            String table3;
            String table4;
            String table5;
            System.out.println("Antall rader: " + antRader + " og antall kolonner: " + antKoll + " og antall elementer: " + antElementer + ".\nVennligst vent for innsetning i databasen...");
            //while (x < antElementer) {
            for (i = 0; i < antKoll; i++) {
                for (j = 0; j < antRader; j++) {
                    //HVIS FØRSTE RAD, legges til kolonnene f.v til og med emnekombnivå 4.
                    if (i == 0) {
                        emnekode = listeUtifraKoll.get(i).get(j);
                        emnekombniv2 = listeUtifraKoll.get(i + 1).get(j);
                        emnekombniv3 = listeUtifraKoll.get(i + 2).get(j);
                        emnekombniv4 = listeUtifraKoll.get(i + 3).get(j);
                        emnekomb = "emnekomb" + j;
                        table = csvFRad.get(i);
                        table2 = "Emne";
                        table3 = csvFRad.get(i + 1);
                        table4 = csvFRad.get(i + 2);
                        table5 = csvFRad.get(i + 3);
                        query.addBatchEmnekode(emnekode, emnekomb, emnekombniv2, emnekombniv3, emnekombniv4, "emnekode m/versjon", table2, table3, table4, table5);
                        y = y + 6;
                        //query.executeBatch();
                    } //HVIS DET IKKE ER FØRSTE legges det til emnekomb ID, for å ha en relasjon mellom de forskjellige.
                    else {
                        objekter = listeUtifraKoll.get(i).get(j);
                        table = csvFRad.get(i);
                        emnekomb = "emnekomb" + j;
                        query.addBatch(objekter, emnekomb, table);
                        y++;
                    }
                }
                if (i == 0) {
                    i = 6;
                }
                if (i == 9) {
                    i++;
                }
                System.out.println("T.o.m de vesentlige kolonnene frem til: " + i + " og radene frem til: " + j + " er nå lagt til.");
            }
            System.out.println("Innsettning er herved komplett. Ha en fin dag videre.");
        } catch (BatchUpdateException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void executeBatch(Query query) {
        query.executeBatch();
    }
}
