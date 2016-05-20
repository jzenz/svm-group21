package at.sw2016.getgoing;

import android.content.Intent;
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
import android.widget.Toast;

import at.sw2016.getgoing.db.GetGoingDbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditEventActivity extends AppCompatActivity implements View.OnClickListener {
    protected GetGoingDbHelper dbHelper;
    private EditText nameField;
    private EditText dateField;
    private EditText locationField;
    private Toolbar toolbar;

    private Event event;

    private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public Event getEvent() {
        return event;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_going);



        Event e;
        if(getIntent().getSerializableExtra("EVENT") == null) {
            event = new Event("Warning", "No event handed over!", new Date(12000));
        }
        else {
            e = (Event) getIntent().getSerializableExtra("EVENT");
            event = Model.getInstance().getEvent(e.getName(),e.getLocation());
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        this.dateField.setText(df.format(e.getDate()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_event, menu);
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
                Date d = new Date();
                try {
                    d = df.parse(dateField.getText().toString());
                    Log.i("INFO", df.format(d));
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "Invalid Date!", Toast.LENGTH_LONG).show();
                    return true;
                }
                event.setName(eventname);
                event.setLocation(eventlocation);
                event.setDate(d);

                //TODO: hand event back to Model.

                Intent intent = new Intent(getBaseContext(), EventOverviewActivity.class);
                startActivity(intent);
            }

            return false;
        }

        return super.onOptionsItemSelected(item);
    }


}
