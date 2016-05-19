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

    private Event event;

    public Event getEvent() {
        return event;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_going);

        event = (Event) getIntent().getSerializableExtra("EVENT");

        //TODO: REMOVE THIS!
        if(event == null) {
            event = new Event("Boaty MCBoatface", "England", new Date(12000));

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new GetGoingDbHelper(this);


        nameField = (EditText) findViewById(R.id.nameField);
        locationField = (EditText) findViewById(R.id.locationField);
        dateField = (EditText) findViewById(R.id.dateField);



        displayEvent(event);

    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

    }


    public GetGoingDbHelper getDBHelper() {
        return dbHelper;
    }

    public void displayEvent(Event e) {
        this.nameField.setText(e.getName());
        this.locationField.setText(e.getLocation());
        this.dateField.setText(e.getDate().toString());
    }


}
