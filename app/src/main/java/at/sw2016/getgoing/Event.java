package at.sw2016.getgoing;

import java.util.Date;

/**
 * Created by Michael on 10.04.2016.
 */
public class Event {
    private String name;
    private String location;
    private Date date;

    public Event(String name, String loc, Date date) {
        this.name = name;
        this.location = loc;
        this.date = date;
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
}
