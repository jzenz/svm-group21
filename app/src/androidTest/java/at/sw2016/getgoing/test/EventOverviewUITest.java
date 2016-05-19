package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ArrayAdapter;

import com.robotium.solo.Solo;

import java.util.ArrayList;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.EventOverview;
import at.sw2016.getgoing.GetGoing;

/**
 * Created by sschrimpf on 19.04.2016.
 */
public class EventOverviewUITest extends ActivityInstrumentationTestCase2<EventOverview> {

    private Solo han;

    public EventOverviewUITest() {
        super(EventOverview.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testForTitle() {
        han.getText("Get Going!");
    }


    public void testDisplayingOfEvents()
    {
        ArrayList<Event> events = super.getActivity().getEvents();
        for (Event e : events) {
            han.getText(e.getName());
            han.getText(e.getLocation());
        }
    }


}


