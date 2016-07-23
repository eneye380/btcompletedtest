/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fairbilling;

/**
 * Class UserSession modelling a single user session in the log file
 * @author eneye380
 */
public class UserSession {
    private String user; //user name
    private long duration; //session duration
    private long start; //session start time
    private long end; //session end time
   
    /**
     * 
     * @param user //user name
     * @param start //session start time
     * @param end //session end time
     */
    public UserSession(String user, long start, long end) {
        this.user = user;
        this.start = start;
        this.end = end;
        this.duration = this.end - this.start;
        
    }    
 
/**
 * 
 * @return start time
 */
    public long getStart() {
        return start;
    }

/**
 * 
 * @param start //session start time
 */
    public void setStart(long start) {
        this.start = start;
    }

    /**
     * 
     * @return end time
     */
    public long getEnd() {
        return end;
    }

    /**
     * 
     * @param end //session end time
     */
    public void setEnd(long end) {
        this.end = end;
    }

    /**
     * 
     * @return user name
     */
    public String getUser() {
        return user;
    }
/**
 * 
 * @param user user name
 */
    public void setUser(String user) {
        this.user = user;
    }

/**
 * 
 * @return session duration
 */
    public long getDuration() {
        return duration;
    }
/**
 * 
 * @param duration session duration
 */
    public void setDuration(long duration) {
        this.duration = duration;
    }
    
    
}
