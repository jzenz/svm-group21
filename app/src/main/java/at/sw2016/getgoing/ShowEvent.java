package at.sw2016.getgoing;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowEvent extends Activity {

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



    }

    public void go_back()
    {


    }

}
