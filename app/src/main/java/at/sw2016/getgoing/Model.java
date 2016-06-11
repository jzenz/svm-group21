package at.sw2016.getgoing;

import java.util.ArrayList;

import at.sw2016.getgoing.db.GetGoingDbHelper;

/**
 * Created by sschrimpf on 19.05.2016.
 */
public class Model {
    private static GetGoingDbHelper dbHelper = new GetGoingDbHelper(MainApplication.getAppContext());
    private static Model ourInstance = new Model();

    public static Model getInstance() {
        return ourInstance;
    }

    private ArrayList<Event> events;

    private Model() {
        events = new ArrayList<>(dbHelper.getAllEvents());
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

    public boolean addEvent(Event e) {
        long row = dbHelper.insertEvent(e);

        if(row != -1){
            e.setId(row);
        }
        events.add(e);
        return row != -1;
    }

    public void deleteEvent(Event e) {
        dbHelper.deleteEvent(e);
        if(events.remove(e) == false){
            // TODO: SOMETHING?
            int i = 5;
            i += 4;
        }
    }
    public void updateEvent(Event e) {
        dbHelper.updateEvent(e);

    }

}
