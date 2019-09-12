/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeplan;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Sondre
 */
public class Reader {

    ArrayList table;
    ArrayList firstRow;

    public Reader(String filSted) throws FileNotFoundException {
        File f = new File(filSted);
        try (Scanner scn = new Scanner(f)) {
            table = new ArrayList();
            firstRow = new ArrayList();
            firstRow.add(scn.nextLine());
            scn.useDelimiter(",");
            while (scn.hasNext()) {
                table.add(scn.next());
            }
        }
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
