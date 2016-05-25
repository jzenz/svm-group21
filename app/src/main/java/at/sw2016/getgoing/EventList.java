package at.sw2016.getgoing;

import android.os.Bundle;
import android.widget.ListView;
import android.app.Activity;

import java.util.ArrayList;
import java.util.Date;

public class EventList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        ListView list_view;

        ArrayList<Event> event_list = new ArrayList<Event>();

        event_list.add(new Event("Best Event", "Mustergasse 3", new Date(2016,9,4)));
        event_list.add(new Event("Event 1", "Musterplatz 3", new Date(2016,9,10)));
        event_list.add(new Event("Dieter", "Musterweg 3", new Date(2016,8,6)));
        event_list.add(new Event("Event 3", "Mustergasse 6", new Date(2016,9,7)));



        Integer[] imageId = {
                R.drawable.grill,
                R.drawable.cocktail,
                R.drawable.grill,
                R.drawable.cocktail,
        };

        CustomImgStringList list_adapter = new CustomImgStringList(EventList.this, imageId, event_list);

        list_view=(ListView)findViewById(R.id.listEvent1);
        list_view.setAdapter(list_adapter);
        /*list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {}
        });*/
    }
}
