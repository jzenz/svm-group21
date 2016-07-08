package at.sw2016.getgoing;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import at.sw2016.getgoing.db.GetGoingContract;
import at.sw2016.getgoing.db.GetGoingDbHelper;

public class CreateEvent extends Activity {

    protected GetGoingDbHelper dbHelper;
    private Calendar calendar = Calendar.getInstance();;
    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH);
    private int day = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // find all textfields
        final EditText event_name = (EditText) findViewById(R.id.create_name);
        final EditText event_description = (EditText) findViewById(R.id.create_description);
        final EditText event_location = (EditText) findViewById(R.id.create_location);
        final TextView event_date = (TextView) findViewById(R.id.create_showdate);

        // init date view
        setDateOnView();

        final Spinner spinner = (Spinner) findViewById(R.id.create_event_type_spinner);
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



        // enter a new date ... show dialog
        event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);

            }
        });

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


                // check date and time
                String[] selected_date = null;
                Date new_date = null;
                Resources resources = getResources();
                try {
                    String date_formated = String.format(resources.getString(R.string.date_format), day,month,year);
                    new_date = GetGoingContract.DB_DATE_FORMAT.parse(date_formated + " 00:00:00");
                }
                catch (Exception e)
                {
                    event_date.setError("please enter correct date");
                    event_date.setHint(String.format(resources.getString(R.string.date_format), calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR)));
                    Log.d("CreateEvent", "onClick: date = " + event_date.getText());
                    return;
                }

                Event event = new Event(name, location, new_date);
                event.setDescription(description);

                // check event type
                String event_type = "";
                try
                {
                    event_type = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                    event.setType(EventType.valueOf(event_type.toUpperCase()));
                }
                catch (Exception e)
                {
                    Log.d("CreateEvent", e.toString());
                    return;
                }

                dbHelper.insertEvent(event);

                Intent create = new Intent(CreateEvent.this, EventList.class);
                startActivity(create);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, date_listener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            arg3 = arg0.getDayOfMonth();
            arg2 = arg0.getMonth()+1; // Jan = 0
            arg1 = arg0.getYear();
            year = arg1;
            month = arg2;
            day = arg3;
            setDateOnView();

        }
    };

    private void setDateOnView()
    {
        TextView event_date = (TextView) findViewById(R.id.create_showdate);
        Resources resources = getResources();
        String date_string = String.format(resources.getString(R.string.date_format), day,month,year);
        event_date.setText(date_string);
    }
}
