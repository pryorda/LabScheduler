/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labscheduler;

import java.util.*;
import java.io.*;
import java.io.LineNumberReader;

/**
 *
 * @author dpryor
 */
public class EventCollection {
    public ArrayList<Event> eventCollection;
    
    public EventCollection(){
        eventCollection = new ArrayList<Event>();
    }
    
    public void readFile(){
        File myFile;
        FileReader fileReader;
        LineNumberReader reader;
        String line = "";
        Event tmpEvent;
        
        try{
            myFile = new File("events.csv");
            fileReader = new FileReader(myFile);
            reader = new LineNumberReader(fileReader);
            Integer skipLineNum = 1;
            while ((line = reader.readLine()) != null) {
                if(reader.getLineNumber() == skipLineNum) {
                    continue; 
                }
                tmpEvent = new Event();
                tmpEvent.parseEvent(line);
                eventCollection.add(tmpEvent);
            }
        }
        catch(Exception e){
            System.out.println("Failure to read events file or failure to parse data. " + e.getMessage());
        }
    }    
}
