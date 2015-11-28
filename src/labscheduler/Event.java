/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labscheduler;

/**
 *
 * @author dpryor
 */
public class Event {
    private String requestorName;
    private String requestorEmail;
    private String eventTitle;
    private Integer numOfParticipants;
    private String specialSoftwareRequests;
    private String date;
    private String startTime;
    private String endTime;
        
    public Event(){
        this.requestorName = "";
        this.requestorEmail = "";
        this.eventTitle = "";
        this.numOfParticipants = 0;
        this.specialSoftwareRequests = "";
        this.date = "";
        this.startTime = "";
        this.endTime = "";
    }
    
    public void setRequestorName(String s){ this.requestorName = s; };
    public void setRequestorEmail(String s){ this.requestorEmail = s; };
    public void setEventTitle(String s){ this.eventTitle = s; };
    public void setNumOfParticipants(Integer i){ this.numOfParticipants = i; };
    public void setSpecialSoftwareRequests(String s){ this.specialSoftwareRequests = s; };
    public void setDate(String s){ this.date = s; };
    public void setStartTime(String s){ this.startTime = s; };
    public void setEndTime(String s){ this.endTime = s; };
    
    public String getRequestorName(){ return this.requestorName; };
    public String getRequestorEmail(){ return this.requestorEmail; };
    public String getEventTitle(){ return(this.eventTitle); };
    public Integer getNumOfParticipants(){ return this.numOfParticipants; };
    public String getSpecialSoftwareRequests(){ return this.specialSoftwareRequests; };
    public String getDate(){ return this.date; };
    public String getStartTime(){ return this.startTime; };
    public String getEndTime(){ return this.endTime; };
    
    public void parseEvent(String event){
        String[] fields = event.split(",");
        if(fields.length == 8 ){
            this.setRequestorName(fields[0]);
            this.setRequestorEmail(fields[1]);
            this.setNumOfParticipants(new Integer(fields[2]));
            this.setSpecialSoftwareRequests(fields[3]);
            this.setEventTitle(fields[4]);
            this.setDate(fields[5]);
            this.setStartTime(fields[6]);
            this.setEndTime(fields[7]);
        }
    }
    
    @Override
    public String toString(){
        //returns CSV format of event
        return(this.getRequestorName() + "," + this.getRequestorEmail() + "," + 
                this.getNumOfParticipants().toString() + "," + this.getSpecialSoftwareRequests() + 
                "," + this.getEventTitle() + "," + this.getDate() + "," + this.getStartTime() 
                + "," + this.getEndTime());
    }
}
