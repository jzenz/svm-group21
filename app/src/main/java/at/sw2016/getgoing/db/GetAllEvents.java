package at.sw2016.getgoing.db;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.EventOverviewActivity;

/**
 * Created by peisn_000 on 17.06.2016.
 */
public class GetAllEvents implements AsyncResponse {

    JSONTask asyncTask = new JSONTask();
   private ArrayList<Event> events = new ArrayList<>();
    boolean finished = false;
    public GetAllEvents() {


        asyncTask.delegate = this;


    }

    public void start()
    {

            asyncTask.executeOnExecutor(asyncTask.THREAD_POOL_EXECUTOR);
        try {
            asyncTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void processFinish(JSONArray output) {
        Log.i("DB" , "JSON: " + output.toString());
        for (int i = 0; i < output.length(); i++) {
            JSONObject row = null;
            try {
                row = output.getJSONObject(i);

                int id = row.getInt("id");
                String name = row.getString("name");
                String location = row.getString("location");
                String desc = row.getString("desc");
                Date evtDate = GetGoingContract.DB_DATE_FORMAT.parse(row.getString("date"));

                Event ev = new Event(name, location, evtDate, desc);
                events.add(ev);

                Log.i("DB", name);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        finished = true;
        Log.i("DB" , "FINISHED: " +  events.size());

    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
