package at.sw2016.getgoing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.Date;

import at.sw2016.getgoing.db.GetGoingDbHelper;

public class CreateEvent extends Activity {

    protected GetGoingDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        final EditText event_name = (EditText) findViewById(R.id.create_name);
        final EditText event_description = (EditText) findViewById(R.id.create_description);
        final EditText event_location = (EditText) findViewById(R.id.create_location);

        Spinner spinner = (Spinner) findViewById(R.id.create_event_type_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_event_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        /*spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }

            }
        });*/

        dbHelper = new GetGoingDbHelper(this);

        Button save_button = (Button) findViewById(R.id.create_save);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Default Event";
                Date date = new Date();
                String location = "Default Street 24";
                String description = "No Description";


                if(event_name.getText().toString().equalsIgnoreCase("")) {
                    event_name.setHint("please enter eventname");//it gives user to hint
                    event_name.setError("please enter eventname");//it gives user to info message //use any one //
                    return;
                }
                else if(event_location.getText().toString().equalsIgnoreCase("")) {
                    event_location.setHint("please enter location");//it gives user to hint
                    event_location.setError("please enter location");//it gives user to info message //use any one //
                    return;
                }
                else if(event_description.getText().toString().equalsIgnoreCase("")) {
                    event_description.setHint("please enter description");//it gives user to hint
                    event_description.setError("please enter description");//it gives user to info message //use any one //
                    return;
                }



                name = event_name.getText().toString();
                location = event_location.getText().toString();
                description = event_description.getText().toString();

                Event event = new Event(name, location, new Date(2016,9,4));
                event.setDescription(description);

                dbHelper.insertEvent(event);

                Intent create = new Intent(CreateEvent.this, EventList.class);
                startActivity(create);
            }
        });

    }


}
