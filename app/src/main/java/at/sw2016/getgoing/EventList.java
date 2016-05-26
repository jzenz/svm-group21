package at.sw2016.getgoing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.Activity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class EventList extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        ListView list_view;

        final ArrayList<Event> event_list = new ArrayList<Event>();

        event_list.add(new Event("Best Event", "Mustergasse 3", new Date(2016,9,4)));
        event_list.add(new Event("Event 1", "Musterplatz 3", new Date(2016,9,10)));
        event_list.add(new Event("Dieter", "Musterweg 3", new Date(2016,8,6)));
        event_list.add(new Event("Event 3", "Mustergasse 6", new Date(2016,9,7)));

        Collections.sort(event_list);


        CustomImgStringList list_adapter = new CustomImgStringList(EventList.this, event_list);

        list_view=(ListView)findViewById(R.id.listEvent1);
        list_view.setAdapter(list_adapter);


        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent info = new Intent(EventList.this, ShowEvent.class);
                info.putExtra("name", event_list.get(position).getName());
                startActivity(info);
            }
        });
    }
}
