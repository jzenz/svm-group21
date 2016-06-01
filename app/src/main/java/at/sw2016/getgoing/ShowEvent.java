package at.sw2016.getgoing;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import at.sw2016.getgoing.db.GetGoingDbHelper;

public class ShowEvent extends Activity {

    protected GetGoingDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        ImageView view = (ImageView) this.findViewById(R.id.reteventlist);

        view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent list = new Intent(ShowEvent.this, EventList.class);
                startActivity(list);
            }
            });




        Bundle extras = getIntent().getExtras();
        TextView name = (TextView) this.findViewById(R.id.show_eventname);
        if(extras.getString("name")!= null)
            name.setText(extras.getString("name"));

        dbHelper = new GetGoingDbHelper(this);

        Event event = null;
        event = dbHelper.getEvent(extras.getInt("id"));

        if(event == null)
           return;

        TextView view_event_description = (TextView) this.findViewById(R.id.show_description);
        view_event_description.setText(event.getDescription());

        TextView view_event_name = (TextView) this.findViewById(R.id.show_eventname);
        view_event_name.setText(event.getName());

        TextView view_event_date = (TextView) this.findViewById(R.id.show_date);
        view_event_date.setText(event.getDateString());

        TextView view_event_location = (TextView) this.findViewById(R.id.show_location);
        view_event_location.setText(event.getLocation());




    }


}
