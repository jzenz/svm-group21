package at.sw2016.getgoing;

import java.util.ArrayList;

/**
 * Created by sschrimpf on 19.05.2016.
 */
public class Model {
    private static Model ourInstance = new Model();
    private String username;
    private boolean loged_in;
    private String password;

    public static Model getInstance() {
        return ourInstance;
    }

    private ArrayList<Event> events;

    private Model() {
        events = new ArrayList<>();
        loged_in = false;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public void addEvent(Event e) {
        events.add(e);
    }

    public void deleteEvent(Event e) {
        events.remove(e);

    }

    public void setUser(String username, String password)
    {
        this.password = password;
        this.username = username;
        this.loged_in = true;
    }


    public boolean isLoged_in() {
        return loged_in;
    }

    public void setLoged_in(boolean loged_in) {
        this.loged_in = loged_in;
    }
}
