package at.sw2016.getgoing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import at.sw2016.getgoing.db.GetGoingDbHelper;
import java.util.ArrayList;
import java.util.Date;

public class GetGoing extends AppCompatActivity implements View.OnClickListener {
    protected GetGoingDbHelper dbHelper;
    private EditText nameField;
    private EditText dateField;
    private EditText locationField;
    private Button nextEventButton;
    private int currenteventposition;
    private String userId;
    private String userName;

    private ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getIntent().getStringExtra("USER_ID");
        userName = getIntent().getStringExtra("USER_NAME");
        setContentView(R.layout.activity_get_going);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new GetGoingDbHelper(this);

        events = new ArrayList<>();

        currenteventposition = 0;

        nameField = (EditText) findViewById(R.id.nameField);
        locationField = (EditText) findViewById(R.id.locationField);
        dateField = (EditText) findViewById(R.id.dateField);

        nextEventButton = (Button) findViewById(R.id.nextEventButton);
        nextEventButton.setOnClickListener(this);


        Event testevent = new Event("Boaty MCBoatface", "England", new Date(12000));
        events.add(testevent);
        displayEvent(testevent);
        currenteventposition = 0;

        Event testevent2 = new Event("What Iceberg?", "North Pole", new Date(1200000));
        events.add(testevent2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_going, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        switch (clickedButton.getId()) {
            case R.id.nextEventButton:
                displayNextEvent();
                break;
            default:

        }
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

    public GetGoingDbHelper getDBHelper() {
        return dbHelper;
    }

    public void displayEvent(Event e) {
        this.nameField.setText(e.getName());
        this.locationField.setText(e.getLocation());
        this.dateField.setText(e.getDate().toString());
    }

    public void displayNextEvent() {
        Event e;

        if (currenteventposition + 1 < events.size()) {
            e = events.get(currenteventposition + 1);
            System.out.println(e.getName());
            currenteventposition++;
        } else {
            e = events.get(0);
            currenteventposition = 0;
        }
        displayEvent(e);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
