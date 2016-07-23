/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fairbilling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class OldLogFile reads log file and checks for data consistencies
 * @author eneye380
 */
public class OldLogFile {

    private final String PATH;//path to log file
    private ArrayList<Record> collectionOfRecords;//list of Record object

    /**
     *
     * @param path
     */
    public OldLogFile(String path) {
        this.PATH = path;
    }

    /**
     *Creates a Record object representing the original log entries
     */
    private void populateCollectionOfRecords() {

        File file = new File(this.PATH); // creates File object file
        int count = 0;
        int index = 0;
        collectionOfRecords = new ArrayList<>(); 

        try {
            Scanner test = new Scanner(file);
            while (test.hasNext()) {
                String row = test.nextLine();
                String field[] = row.split(" ");

                if (field.length == 3) {//check to ensure correct log format is read
                    String[] t = field[0].split(":");
                    if (t.length == 3) {
                        String hour = t[0];
                        String minute = t[1];
                        String second = t[2];
                        try {
                            int hh = Integer.parseInt(hour);
                            int mm = Integer.parseInt(minute);
                            int ss = Integer.parseInt(second);
                            if(!(hh >= 0 && hh <=23)){
                                System.out.println("invalid hh detected");
                                continue;
                            }
                            if(!(mm >= 0 && mm <=59)){
                                System.out.println("invalid mm detected");
                                continue;
                            }
                            if(!(ss >= 0 && ss <=59)){
                                System.out.println("invalid ss detected");
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("invalid number detected"); 
                            continue;
                        }
                        
                        if (!(field[2].equalsIgnoreCase("end") || field[2].equalsIgnoreCase("start"))) {
                            continue;
                        }
                        ++index;
                        //Record object created
                        Record record = new Record(index + "", field[0], field[1], field[2]);
                        collectionOfRecords.add(count++, record);
                    }
                }
            }
        } catch (IOException e) {
            System.out.print("File does not exist");
        }

    }

    /**
     *
     * @return returns a list Record object
     */
    public ArrayList<Record> fetchCollectionOfRecords() {
        this.populateCollectionOfRecords();
        return collectionOfRecords;
    }

}
