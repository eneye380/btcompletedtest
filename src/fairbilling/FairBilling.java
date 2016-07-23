/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fairbilling;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class FairBilling is the main method and performs key computation 
 * @author eneye380
 */
public class FairBilling {

    private static Map<Integer, ArrayList> recordMap; //a map object containing the log entries
    private static ArrayList<UserSession> session; //an arraylist holding UserSession object
    private static ArrayList<String> users; //an arraylist of unique users
    private static String path = "";//a string variable holding the log file path

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //System.out.println(args[0]);
        path += args[0];
        //path += "C:\\Users\\eneye380\\Documents\\NetBeansProjects\\FairBilling\\log.txt";
        
        OldLogFile oldlogfile = new OldLogFile(path);
        ArrayList<Record> record = oldlogfile.fetchCollectionOfRecords();

        NewLogFile newlogfile = new NewLogFile();
        recordMap = newlogfile.fetchDatastore(record);

        Logic logic = new Logic(recordMap);
        users = logic.getUsersInSession();
        
        //local variables
        Map<String, ArrayList> datastore = logic.getRecordDatastore();//map object holding a formatted log entry
        Map<String, ArrayList> entry;//map object holding each user respective start and end times
        ArrayList<Long> startA;//arraylist holding start times
        ArrayList<Long> endA;//arraylist holding end times

        session = new ArrayList<>();
        //iterate through unique users in the log
        for (int i = 0; i < logic.getNumberOfUsers(); i++) {
            entry = (HashMap) datastore.get(users.get(i)).get(0);//temporary map object holding a users formatted log entry passed to the method process
            startA = entry.get("start");//temporary arraylist holding start times passed to the method process
            endA = entry.get("end");//temporary arraylist holding start times passed to the method process
            process(users.get(i), startA, endA);
        }
        
    }

    /**
     *The method processes the log 
     * entry to find user sessions and store 
     * in the UserSession reference
     * @param user username
     * @param s list of start times in millisecond
     * @param e list of end times in millisecond
     */
    private static void process(String user, ArrayList s, ArrayList e) {
        int i = 0;
        long start;
        long end;
        UserSession us;

        while (s.size() > 0 || e.size() > 0) {

            if (s.isEmpty()) {
                start = Logic.getEarliestTime();
                end = (long) e.get(i);
                us = new UserSession(user, start, end);
                session.add(us);
                e.remove(i);
                continue;
            }

            if (e.isEmpty()) {
                start = (long) s.get(i);
                end = Logic.getLatestTime();
                us = new UserSession(user, start, end);
                session.add(us);
                s.remove(i);
                continue;
            }

            start = (long) s.get(i);
            end = (long) e.get(i);
            if (start < end) {
                us = new UserSession(user, start, end);
                session.add(us);
                s.remove(i);
                e.remove(i);
                continue;
            }
            if (start > end) {
                start = Logic.getEarliestTime();
                us = new UserSession(user, start, end);
                session.add(us);
                e.remove(i);
            }
        }

        int sum;
        int cc;
        Map<String, ArrayList> data = new HashMap<>();
        ArrayList<String> item;

        for (String u : users) {
            sum = 0;
            cc = 0;
            item = new ArrayList<>();
            for (UserSession uu : session) {

                if (uu.getUser().equalsIgnoreCase(u)) {
                    sum += uu.getDuration();
                    cc++;
                }

            }
            item.add(cc + "");
            item.add(sum / 1000 + "");//convert the time from milliseconds to seconds then adds to item
            data.put(u, item);
        }
        try {
            outPut(data, users);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(FairBilling.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param m
     * @param a
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private static void outPut(Map m, ArrayList a) throws FileNotFoundException, UnsupportedEncodingException {
        PrintLog printLog = new PrintLog(m, a);
    }

}
