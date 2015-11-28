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
    public ArrayList<Event> allEvents;
    
    public EventCollection(){
        allEvents = new ArrayList<Event>();
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
                allEvents.add(tmpEvent);
            }
            reader.close();
            fileReader.close();
        }
        catch(Exception e){
            System.out.println("Failure to read events file or failure to parse data. " + e.getMessage());
        }
    }
    public void writeFile(){
        File myFile;
        FileOutputStream is;
        OutputStreamWriter osw;    
        Writer w;
        
        try{
            myFile = new File("events.csv");
            is = new FileOutputStream(myFile);
            osw = new OutputStreamWriter(is);    
            w = new BufferedWriter(osw);
            w.write("RequestorName,RequestorEmail,NumberOfParticipants,SpecialSoftwareRequests,EventTitle,EventDate,StartTime,EndTime\n");
            for(Event event : this.allEvents){
                w.write(event.toString() + "\n");
            }
            w.close();
            osw.close();
            is.close();
        }
        catch(Exception e){
            System.out.println("Failure to write events file: " + e.getMessage());
        }
    }        
}
