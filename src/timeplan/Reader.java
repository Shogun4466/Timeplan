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

/**
 *
 * @author Sondre
 */
public class Reader {

    public Reader() throws Exception {
        
    }

    void reader() throws FileNotFoundException {
        File f = new File ("C:/Users/Sondre/Dropbox/IT og informasjonssystemer/Praksisplass/Studieprogram_data_testUttrekk.csv");
        try (Scanner scn = new Scanner(f)) {
            scn.useDelimiter(",");
            while(scn.hasNext())    {
                System.out.print(scn.next()+"        ");
            }
        }
    }
}
