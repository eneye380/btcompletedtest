/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fairbilling;

import java.sql.Time;





/**
 * Class Record modelling the original log file single entry
 * @author eneye380
 */
public class Record {
    
    private String timestamp;//timestamp HH:MM:SS
    private String name;// user name
    private String status;//user status - start or end
    private static Time time;//Time object
    private String sn;//serial number

    /**
     * 
     * @param sn //serial number
     * @param timestamp //timestamp HH:MM:SS
     * @param name // user name
     * @param status //user status - start or end
     */
    public Record(String sn, String timestamp, String name, String status) {
        this.sn = sn;
        this.timestamp = timestamp;
        this.name = name;
        this.status = status;
    }
    
    /**
     * 
     */
    public Record() {
    }
    
    /**
     * converts the timestamp to a Time object
     * @param tStamp timestamp HH:MM:SS
     */
    private static void createTime(String tStamp) {
        String[] t = tStamp.split(":");
        String hour = t[0];
        String minute = t[1];
        String second = t[2];
        int hh = Integer.parseInt(hour);
        int mm = Integer.parseInt(minute);
        int ss = Integer.parseInt(second);
        time = new Time(hh, mm, ss);
    }
    /**
     * creates a Time object by invoking the createTime method
     * @param tStamp timestamp
     * @return a Time object
     */
    public static Time getTime(String tStamp) {
        Record.createTime(tStamp);
        return time;
    }
    /**
     * 
     * @return timestamp as a string
     */
    public String getTimestamp() {
        return timestamp;
    }
    /**
     * 
     * @param timestamp timestamp HH:MM:SS
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
/**
 * 
 * @return username
 */
    public String getName() {
        return name;
    }
/**
 * 
 * @param name username
 */
    public void setName(String name) {
        this.name = name;
    }
/**
 * 
 * @return status - start or end
 */
    public String getStatus() {
        return status;
    }
/**
 * 
 * @param status status - start or end
 */
    public void setStatus(String status) {
        this.status = status;
    }
/**
 * 
 * @return serial number
 */
    public String getSn() {
        return sn;
    }

        
    
}
