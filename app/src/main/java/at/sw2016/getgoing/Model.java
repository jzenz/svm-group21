package at.sw2016.getgoing;

import java.util.ArrayList;
import java.util.List;

import at.sw2016.getgoing.db.GetGoingDbHelper;

/**
 * Created by sschrimpf on 19.05.2016.
 */
public class Model {
    private static GetGoingDbHelper dbHelper = new GetGoingDbHelper(MainApplication.getAppContext());
    private static Model ourInstance = new Model();
    private String username;
    private boolean loged_in;

    public static Model getInstance() {
        return ourInstance;
    }

    private ArrayList<Event> events;

    private Model() {
        events = new ArrayList<>(dbHelper.getAllEvents());
        loged_in = false;
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

    public boolean getUser(String username)
    {

        if (!dbHelper.checkUsername(username))
        {
            loged_in = true;
            return true;
        }
        else
        {
            return false;
        }

    }

    public boolean createUser(String username, String pw)
    {
        long status = dbHelper.insertUser(username, pw);
        if (status > -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkUserPW(String username, String pw)
    {

        return dbHelper.checkUserPW(username, pw);
    }

    public void setUser(String username)
    {
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
