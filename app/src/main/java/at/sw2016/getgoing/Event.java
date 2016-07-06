package at.sw2016.getgoing;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Michael on 10.04.2016.
 */
public class Event implements Serializable {
    private int id;
    private String name;
    private String location;
    private Date date;
    private String description;


    public Event(int id,String name, String loc, Date date, String desc) {
        this.id = id;
        this.name = name;
        this.location = loc;
        this.date = date;
        this.description = desc;
    }

    public Event(String evtName, String evtLoc, Date evtDate) {
        this.id = 0;
        this.name = evtName;
        this.location = evtLoc;
        this.date = evtDate;
        this.description = " ";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int newID){
        this.id = newID;
    }

    @Override
    public boolean equals(Object other){
        if(!other.getClass().equals(this.getClass())) {
            return false;
        }
        Event otherEvt = (Event)other;
        if(!otherEvt.getName().equals(this.getName())) {
            return false;
        }
        if(!otherEvt.getLocation().equals(this.getLocation())) {
            return false;
        }
        // compare up until seconds for now
        if( ( (this.getDate().getTime() - otherEvt.getDate().getTime()) / 1000) != 0) {
            return false;
        }
        return true;
    }
}
