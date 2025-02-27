package com.thoughtspott.app;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;


@IgnoreExtraProperties
public class Session {
    private String course;
    private Student creator;
    private ArrayList<Student> participants = new ArrayList<>();
    private int population;
    private LatLng location;
    private String timeStart;
    private String description;
    // empty constructor required for Firebase
    public Session(){}
    // constructor
    public Session(String c, Student crtr, LatLng loc, String time, String desc){
        course = c;
        creator = crtr;
        participants.add(creator);
        population = 1;
        location = loc;
        timeStart = time;
        description = desc;
    }

    public void setCourse(String c){
        this.course = c;
    }
    public String getCourse(){
        return this.course;
    }
    public void setCreator(Student cr){
        this.creator = cr;
    }
    public Student getCreator(){
        return this.creator;
    }
    public void setParticipants(ArrayList<Student> p){
        this.participants = p;
    }
    public ArrayList<Student> getParticipants(){
        return this.participants;
    }
    public void setPopulation(int pop) {
        this.population = pop;
    }
    public int getPopulation() {
        return this.population;
    }
    public void setDescription(String d) {
        this.description = d;
    }
    public String getDescription() {
        return this.description;
    }
    public void setLocation(LatLng lcn) {
        this.location = lcn;
    }
    public LatLng getLocation() {
        return this.location;
    }
    public void setTimeStart(String tS) {
        this.timeStart = tS;
    }
    public String getTimeStart() {
        return this.timeStart;
    }

    public String sessionToFormattedString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getCourse())
                .append(" - Created by ")
                .append(this.creator.getNameFirst())
                .append(" ")
                .append(this.creator.getNameLast())
                .append('\n');
        stringBuilder.append("Members: ")
                .append(this.getPopulation())
                .append('\n');
        stringBuilder.append(this.getDescription())
                .append('\n');
        stringBuilder.append("Start time: ")
                .append(this.getTimeStart());
        return stringBuilder.toString();
    }

    public void addMember(Student member){
        participants.add(member);
        population++;
        //update database here!
    }

    // true if removed successfully
    public boolean removeMember(Student member){
        for(int i = 0; i < population; i++){
            if(participants.get(i) == member){
                participants.remove(i);
                population--;
                // update database here!
                return true;
            }
        }
        return false;
    }
    public void removeSession(){
        // update database here!
    }



}
