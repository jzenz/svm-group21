package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.EventOverviewActivity;

/**
 * Created by sschrimpf on 19.04.2016.
 */
public class EventOverviewActivityUITest extends ActivityInstrumentationTestCase2<EventOverviewActivity> {

    private Solo han;

    public EventOverviewActivityUITest() {
        super(EventOverviewActivity.class);
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
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            han.getText(df.format(e.getDate()));

        }
    }

    public void testLoginButton()
    {
        han.clickOnMenuItem("Login");
        han.getText("Sign in");
    }


}


