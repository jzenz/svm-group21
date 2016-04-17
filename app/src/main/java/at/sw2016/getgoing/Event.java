package at.sw2016.getgoing;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Michael on 10.04.2016.
 */
public class Event implements Serializable{
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

    @Override
    public String toString() {
        return new StringBuffer(" Name: " )
                .append(this.name)
                .append(" Location: ")
                .append(this.location)
                .append(" Date: ")
                .append(this.date)
                .toString();
    }

    @Override
    public boolean equals(Object other)
    {
        if(!(other instanceof Event)) {
            return false;
        }
        Event that = (Event)other;
        return this.date.equals(that.date)
                && this.location.equals(that.location)
                && this.date.equals(that.date);
    }

    @Override
    public int hashCode()
    {
        int hashCode = 1;
        hashCode = hashCode * 37 + this.name.hashCode();
        hashCode = hashCode * 37 + this.location.hashCode();
        hashCode = hashCode * 37 + this.date.hashCode();

        return hashCode;
    }
}
