package at.sw2016.getgoing;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Michael on 10.04.2016.
 */
public class Event implements Serializable {
    private long id;
    private String name;
    private String location;
    private Date date;
    private String description;


    public Event(String name, String loc, Date date){
        this.id = -1;
        this.name = name;
        this.location = loc;
        this.date = date;
    }

    public Event(String name, String loc, Date date, String desc) {
        this.id = -1;
        this.name = name;
        this.location = loc;
        this.date = date;
        this.description = desc;
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

    public long getId(){
        return this.id;
    }

    public void setId(long newID){
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
