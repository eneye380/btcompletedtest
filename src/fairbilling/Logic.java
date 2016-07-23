/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fairbilling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Class logic that performs log entry formatting
 * @author eneye380
 */
public class Logic {

    private final Map<Integer, ArrayList> recordMap;//a map object containing the log entries identified using serial numbers
    private Map<String,ArrayList> recordDatastore;//a map object containing the log entries identified using usernames formatted using start and end times
    private final ArrayList<String> usersInSession;//holds unique users in log file
    private int numberOfUsers;//holds number of unique users in a log file
    private static long earliestTime;//holds the earliest time in a log file
    private static long latestTime;//holds the latest time in a log file
    private int size;//holds the  number of valid log entries

    /**
     *
     * @param map
     */
    public Logic(Map map) {
        recordMap = map;
        
        usersInSession = new ArrayList<>();
        retrieveNames();
        retrieveEarliestAndLatestTimes();
        processUserSessions();
    }

    /**
     *retrieves sets the unique users in log file and set the number of valid log entry
     */
    private void retrieveNames() {
        int length = recordMap.size();
        //System.out.println("NUMBER OF VALID LOG ENTRIES: "+length);
        setSize(length);
        for (int i = 1; i <= length; i++) {
            String name = (String) recordMap.get(i).get(0);
            if (!usersInSession.contains(name)) {
                usersInSession.add(name);
            }
        }
        calculateNumberOfUsers();
    }

    /**
     *retrieves and sets the earliest and latest times in the log file
     */
    private void retrieveEarliestAndLatestTimes() {
        String earliest = (String) recordMap.get(1).get(1);
        long earliestL = Long.parseLong(earliest);
        String latest = (String) recordMap.get(getSize()).get(1);
        long latestL = Long.parseLong(latest);
        setEarliestTime(earliestL);
        setLatestTime(latestL);
    }

    /**
     *computes and sets the number of unique users in the log
     */
    private void calculateNumberOfUsers() {
        int count = usersInSession.size();
        setNumberOfUsers(count);
    }
    
    /**
     * stores the formatted log entries in the Map object reference recordDatastore
     */
    private void processUserSessions() {
        int userCount = this.getNumberOfUsers();
        int recordCount = this.getSize();
        recordDatastore = new HashMap<>();
        
        for (int i = 0; i < userCount; i++) {
            //local variable
            Map<String, ArrayList> entry = new HashMap<>();//temporary map object holding a users formatted log entry
            ArrayList<Long> startA = new ArrayList<>();//temporary arraylist holding start times
            ArrayList<Long> endA = new ArrayList<>();//temporary arraylist holding start times
            ArrayList<Map> datastore = new ArrayList<>();//temporary arraylist holding map object holding a users start and end times
            for (int j = 1; j <= recordCount; j++) {
                String name = (String) recordMap.get(j).get(0);
                String time = (String) recordMap.get(j).get(1);
                long timeL = Long.parseLong(time);
                String status = (String) recordMap.get(j).get(2);
                if(name.equalsIgnoreCase(usersInSession.get(i))){
                    if(status.equalsIgnoreCase("start")){
                        startA.add(timeL);
                    }
                    if(status.equalsIgnoreCase("end")){
                        endA.add(timeL);
                    }
                }
            }
            entry.put("start",startA);
            entry.put("end",endA);
            datastore.add(0,entry);
            recordDatastore.put(usersInSession.get(i), datastore);
        }
    }

    /**
     *
     * @return Map recordDatastore
     */
    
    public Map<String, ArrayList> getRecordDatastore() {
        return recordDatastore;
    }
/**
 * 
 * @return numberOfUsers
 */
    public int getNumberOfUsers() {
        return numberOfUsers;
    }
/**
 * 
 * @return usersInSession
 */
    public ArrayList<String> getUsersInSession() {
        return usersInSession;
    }

    /**
     *
     * @param numberOfUsers holds number of unique users in a log
     */
    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    /**
     *
     * @return earliestTime
     */
    public static long getEarliestTime() {
        return earliestTime;
    }

    /**
     *
     * @param earliestTime holds the earliest time in a log file
     */
    public static void setEarliestTime(long earliestTime) {
        //System.out.println("EARLIEST: "+earliestTime);
        Logic.earliestTime = earliestTime;
    }

    /**
     *
     * @return latestTime
     */
    public static long getLatestTime() {
        return latestTime;
    }

    /**
     *
     * @param latestTime holds the latest time in a log file
     */
    public static void setLatestTime(long latestTime) {
        //System.out.println("LATEST: "+latestTime);
        Logic.latestTime = latestTime;
    }

    /**
     *
     * @return size of valid log entries
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param size holds the  number of valid log entries
     */
    public void setSize(int size) {
        this.size = size;
    }

}
