package at.sw2016.getgoing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

public class GetGoing extends AppCompatActivity {

    private EditText nameField;
    private EditText dateField;
    private EditText locationField;

    private ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_going);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        events = new ArrayList<>();

        nameField = (EditText) findViewById(R.id.nameField);
        locationField = (EditText) findViewById(R.id.locationField);
        dateField = (EditText) findViewById(R.id.dateField);

        Event testevent = new Event("Boaty MCBoatface", "England", new Date(12000));
        events.add(testevent);
        displayEvent(testevent);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayEvent(Event e){
        this.nameField.setText(e.getName());
        this.locationField.setText(e.getLocation());
        this.dateField.setText(e.getDate().toString());
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
