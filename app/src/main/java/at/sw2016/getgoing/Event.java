package at.sw2016.getgoing;

import java.util.Date;

/**
 * Created by Michael on 10.04.2016.
 */
public class Event implements Comparable<Event>{
    private String name;
    private String location;
    private Date date;
    private String description;
    private EventType type;


    public Event(String name, String loc, Date date) {
        this.name = name;
        this.location = loc;
        this.date = date;
        this.type = EventType.DEFAULT;
    }

    public Event(String name, String loc, Date date, String desc) {
        this.name = name;
        this.location = loc;
        this.date = date;
        this.description = desc;
        this.type = EventType.DEFAULT;
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

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
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
        if( ( (date.getTime() - otherEvt.getDate().getTime()) / 1000) != 0) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Event another) {
        return this.date.compareTo((another).getDate());
    }

    public String getLocationDateString()
    {
        return date.getDay() + "." + date.getMonth() + "." + date.getYear() + " - " +
                getLocation().toString();
    }
}
