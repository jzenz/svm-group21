package at.sw2016.getgoing;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.R;

/**
 * Created by Lukas on 23.05.2016.
 */
public class CustomImgStringList extends ArrayAdapter<String> {
    private final Activity context;
    private final Integer[] imageId;
    private final List<Event> events;

    public CustomImgStringList(Activity context,
                      Integer[] imageId, List<Event> events) {
        super(context, R.layout.single_list_item, new String[events.size()]);
        this.context = context;
        this.imageId = imageId;
        this.events = events;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.single_list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        //txtTitle.setText(web[position]);

        Event event = events.get(position);

        SpannableStringBuilder size_string = new SpannableStringBuilder();
        SpannableString stxt=  new SpannableString(event.getName());
        stxt.setSpan(new RelativeSizeSpan(2f), 0,event.getName().length(), 0);

        SpannableString dtxt=  new SpannableString(event.getLocationDateString());

        size_string.append(stxt);
        size_string.append("\n");
        size_string.append(dtxt);

        txtTitle.setText(size_string);

        ImageView shopIcon = (ImageView) rowView.findViewById(R.id.shoppingicon);

        imageView.setImageResource(event.getType().getPath());
        shopIcon.setImageResource(R.mipmap.ic_shopping);

        return rowView;
    }
}
