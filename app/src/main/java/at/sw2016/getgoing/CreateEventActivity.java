package at.sw2016.getgoing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import at.sw2016.getgoing.gui.MainListViewAdapter;

public class CreateEventActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText dateField;
    private EditText locationField;
    private Toolbar toolbar;
    private ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        events = Model.getInstance().getEvents();

        nameField = (EditText) findViewById(R.id.nameField);
        locationField = (EditText) findViewById(R.id.locationField);
        dateField = (EditText) findViewById(R.id.dateField);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_going, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            String eventname = nameField.getText().toString();
            String eventlocation = locationField.getText().toString();

            if(!eventname.isEmpty() && !eventlocation.isEmpty()) {
                Event e = new Event(eventname,eventlocation,new Date(0));
                events.add(e);

                Intent intent = new Intent(getBaseContext(), EventOverview.class);
                startActivity(intent);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
