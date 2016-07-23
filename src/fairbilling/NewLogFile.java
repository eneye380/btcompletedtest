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
 * Class NewLogFile modelling the newly formatted log entries
 * @author eneye380
 */
public class NewLogFile {
    
    private final Map<Integer, ArrayList> datastore;//a map object containing the log entries
    private ArrayList<String> record;//list of entry data HH:MM:SS, username, status
    
    /**
     * 
     */
    public NewLogFile() {
        datastore = new HashMap<>();       
    }
    
    /**
     * creates a formatted log entry
     * @param r Record object
     */    
    private void createRecord(Record r){
        this.record = new ArrayList<>();
        this.record.add(0,r.getName());//adds username
        this.record.add(1,Record.getTime(r.getTimestamp()).getTime()+"");//add converted timestamp in millisecond
        this.record.add(2,r.getStatus());//adds status of user in log
        
    }
    
    /**
     * stores each formatted log entry using the Map object reference datastore
     * @param index serial number
     */
    private void fillDatastore(int index){
        if(!this.datastore.containsKey(index)){
            this.datastore.put(index, this.record);
        }
    }
    
    /**
     * retrieves Record object
     * @param record holding user log entry
     * @return 
     */
    public Map fetchDatastore(ArrayList<Record> record){
        for(Record r:record){
            createRecord(r);
            int index = Integer.parseInt(r.getSn());//gets created log entry serial number
            fillDatastore(index);
        }
        return this.datastore;
    }
    
}
