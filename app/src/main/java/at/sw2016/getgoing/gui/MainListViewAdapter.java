package at.sw2016.getgoing.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.R;

/**
 * Created by sschrimpf on 19.05.2016.
 */
public class MainListViewAdapter  extends ArrayAdapter<Event> {
    public MainListViewAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Event event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_item, parent, false);
        }
        // Lookup view for data population
        TextView eventName = (TextView) convertView.findViewById(R.id.eventName);
        TextView eventLocation = (TextView) convertView.findViewById(R.id.eventLocation);
        // Populate the data into the template view using the data object
        eventName.setText(event.getName());
        eventLocation.setText(event.getLocation());
        // Return the completed view to render on screen
        return convertView;
    }
}
