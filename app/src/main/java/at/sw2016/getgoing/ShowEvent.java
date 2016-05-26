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

        Event event = dbHelper.getEvent(extras.getInt("id"));


        /*if(extras.getInt("id")) {
            event = dbHelper.getEvent(extras.getInt("id"));
        }*/
        if(event == null)
           return;;



       TextView description = (TextView) this.findViewById(R.id.show_description);
        description.setText(""+extras.getInt("id"));




    }


}
