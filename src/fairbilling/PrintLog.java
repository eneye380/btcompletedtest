/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fairbilling;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Class PrintLog that writes to log file
 * @author eneye380
 */
public class PrintLog {

    Map<String, ArrayList> data = new HashMap<>(); //a map object containing the new formatted log entries
    private final ArrayList<String> users; //an arraylist of unique users

    /**
     * 
     * @param data //a map object containing the new formatted log entries
     * @param users //an array list of unique users
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     */
    public PrintLog(Map data, ArrayList<String> users) throws FileNotFoundException, UnsupportedEncodingException {
        this.data = data;
        this.users = users;

        String logOutPut;
        
        try (PrintWriter writer = new PrintWriter("fairbillingsolution.txt", "UTF-8")) {
            for (String u : this.users) {
                logOutPut = "";
                logOutPut += u;
                logOutPut += " ";
                logOutPut += this.data.get(u).get(0);
                logOutPut += " ";
                logOutPut += this.data.get(u).get(1);
                logOutPut += "\n";
                writer.println(logOutPut);
            }
        }
    }

}
