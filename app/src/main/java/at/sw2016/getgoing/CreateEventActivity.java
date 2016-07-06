package at.sw2016.getgoing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateEventActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText dateField;
    private EditText timeField;
    private EditText locationField;
    private Toolbar toolbar;
    private ArrayList<Event> events;

    private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private EditText viewById;

    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_create_event);

                    events = Model.getInstance().getEvents();

                    nameField = (EditText) findViewById(R.id.nameField);
                    locationField = (EditText) findViewById(R.id.locationField);
                    dateField = (EditText) findViewById(R.id.dateField);
                    timeField = (EditText) findViewById(R.id.timeField);


                    dateField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            if(actionId == EditorInfo.IME_ACTION_DONE)
                            {
                                Date myDate;
                                try {

                        myDate = df.parse(dateField.getText().toString() + " " + timeField.getText().toString());
                        Log.i("INFO", df.format(myDate));

                    } catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "Invalid Date!", Toast.LENGTH_LONG).show();
                        return true;
                    }
                }
                return false;
            }
        });



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
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
            String eventName = nameField.getText().toString();
            String eventLocation = locationField.getText().toString();


            if(!eventName.isEmpty() && !eventLocation.isEmpty()) {
                Date d = new Date();

                try {
                    d = df.parse(dateField.getText().toString() + " " + timeField.getText().toString());
                    Log.i("INFO", df.format(d));
                } catch (ParseException e) {
                    Toast.makeText(getBaseContext(), "Invalid Date!", Toast.LENGTH_LONG).show();
                    return true;
                }
              /*  Event e = new Event(eventName,eventLocation,d);
                if (Model.getInstance().getEvent(eventName,eventLocation) == null) {
                    Model.getInstance().addEvent(e);
                }
                else {
                    Toast.makeText(getBaseContext(), "Event already exists!", Toast.LENGTH_LONG).show();
                    return true;
                }*/

                Toast.makeText(getBaseContext(), "Event created!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), EventOverviewActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getBaseContext(), "Some information is missing!", Toast.LENGTH_LONG).show();
            }

            return false;
        }

        return super.onOptionsItemSelected(item);
    }
}
