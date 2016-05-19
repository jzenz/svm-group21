package at.sw2016.getgoing;

import java.util.ArrayList;

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

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
