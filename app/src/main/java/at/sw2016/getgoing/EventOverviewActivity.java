package at.sw2016.getgoing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import at.sw2016.getgoing.gui.MainListViewAdapter;

public class EventOverviewActivity extends AppCompatActivity {
    private static Context context = MainApplication.getAppContext();

    public Context getContext(){
        return context;
    }

    private ArrayList<Event> events = new ArrayList<>();
    private ListView mainlistview;
    private FloatingActionButton fab;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(Model.getInstance().isLoged_in())
        {

        }
        else
        {
            getMenuInflater().inflate(R.menu.menu_event_overview, menu);
        }

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventOverviewActivity.context = getApplicationContext();
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
                intent.putExtra("EVENT", selected_event);
                startActivity(intent);
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
