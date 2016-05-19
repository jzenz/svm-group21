package at.sw2016.getgoing;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import at.sw2016.getgoing.GetGoing;
import at.sw2016.getgoing.gui.MainListViewAdapter;

public class EventOverview extends AppCompatActivity {

    private ArrayList<Event> events = new ArrayList<>();
    private ListView mainlistview;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_overview);

        events = Model.getInstance().getEvents();

        mainlistview = (ListView) findViewById(R.id.mainListView);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreateEventActivity.class);
                startActivity(intent);
            }
        });


        //TODO: REMOVE THIS!
        if(events.get(0) != null) {
            Event testevent = new Event("Boaty MCBoatface", "England", new Date(12000));
            events.add(testevent);
        }
//
//        Event testevent2 = new Event("What Iceberg?", "North Pole", new Date(1200000));
//        events.add(testevent2);
//
//        Event testevent3 = new Event("Titanic", "Below water", new Date(120000000));
//        events.add(testevent3);
//
//        Event testevent4 = new Event("Napoleons Defeat", "Waterloo", new Date(120000000));
//        events.add(testevent4);

        MainListViewAdapter arrayAdapter = new MainListViewAdapter(this , events);

        arrayAdapter.notifyDataSetChanged();
        mainlistview.setAdapter(arrayAdapter);


        mainlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Event selected_event = events.get(position);
                Intent intent = new Intent(getBaseContext(), GetGoing.class);
                intent.putExtra("EVENT",events.get(position));
                startActivity(intent);
            }
        });

    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
