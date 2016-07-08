package at.sw2016.getgoing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.app.Activity;
import android.widget.SearchView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import at.sw2016.getgoing.db.GetGoingDbHelper;

public class EventList extends Activity {


    protected GetGoingDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new GetGoingDbHelper(this);

        setContentView(R.layout.activity_event_list);

        final ListView list_view;

        final List<Event> event_list_db = dbHelper.getAllEvents();

        Collections.sort(event_list_db);


        CustomImgStringList list_adapter = new CustomImgStringList(EventList.this, event_list_db);

        list_view=(ListView)findViewById(R.id.listEvent1);
        list_view.setAdapter(list_adapter);


        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent info = new Intent(EventList.this, ShowEvent.class);
                info.putExtra("name", event_list_db.get(position).getName());
                info.putExtra("id", event_list_db.get(position).getId());
                startActivity(info);
            }
        });

        ImageButton add_button = (ImageButton) findViewById(R.id.list_add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent create = new Intent(EventList.this, CreateEvent.class);
                startActivity(create);
            }
        });

        SearchView search = (SearchView) findViewById(R.id.searchEvent);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query){
                searchAndUpdateQuery(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText)
            {
                searchAndUpdateQuery(newText);
                return true;
            }

            public void searchAndUpdateQuery(String text)
            {
                List<Event> updated_event_list = new ArrayList<Event>();
                for(Event x : event_list_db)
                {

                    if(x.getName().toUpperCase().matches(".*" + text.toUpperCase() +".*"))
                        updated_event_list.add(x);
                }

                CustomImgStringList list_adapter = new CustomImgStringList(EventList.this, updated_event_list);
                list_view.setAdapter(list_adapter);
            }

        });


    }
}
