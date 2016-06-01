package at.sw2016.getgoing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import at.sw2016.getgoing.gui.MainListViewAdapter;

public class EventOverviewActivity extends AppCompatActivity {

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
        if(events.isEmpty()) {
            Event testevent = new Event("Testevent", "Nowhere", new Date(116, 10, 20, 17, 00));
            events.add(testevent);
        }

        MainListViewAdapter arrayAdapter = new MainListViewAdapter(this , events);

        arrayAdapter.notifyDataSetChanged();
        mainlistview.setAdapter(arrayAdapter);


        mainlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Event selected_event = events.get(position);
                Intent intent = new Intent(getBaseContext(), EditEventActivity.class);
                intent.putExtra("EVENT",events.get(position));
                startActivity(intent);
            }
        });

    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
