package at.sw2016.getgoing;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sschrimpf on 19.05.2016.
 */
public class Model {
    private static Model ourInstance = new Model();

    public static Model getInstance() {
        return ourInstance;
    }

    private ArrayList<Event> events = new ArrayList<>();

    private Model() {
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public Event getEvent(String name, String location)
    {
        for(Event e : events){
            if(e.getName().equals(name) && e.getLocation().equals(location))
            {
                return e;
            }
        }
        return null;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
