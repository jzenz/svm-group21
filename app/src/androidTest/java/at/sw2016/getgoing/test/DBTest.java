package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.List;
import java.util.Date;

import at.sw2016.getgoing.EditEventActivity;
import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.EventOverviewActivity;
import at.sw2016.getgoing.Model;

/**
 * Created by Michael on 05.05.2016.
 */
public class DBTest extends ActivityInstrumentationTestCase2<EventOverviewActivity> {
    private Solo han;
    private Event testEvent;
    private final String evtName = "testEvtName";
    private final String evtLoc = "testEvtLoc";
    private final Date evtDate = new Date();
    private final String evtDesc = "testEvtDesc";

    public DBTest() {
        super(EventOverviewActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());
        testEvent = new Event(evtName, evtLoc, evtDate, evtDesc);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testReadingWriting() {
        boolean inserted =  Model.getInstance().addEvent(testEvent);
        assertTrue("Event was added to the database", inserted);
        List<Event> storedEvents = Model.getInstance().getEvents();
        assertTrue("Database contains stored event", storedEvents.contains(testEvent));

        Model.getInstance().deleteEvent(testEvent);
        storedEvents = Model.getInstance().getEvents();
        assertFalse("Event was successfully deleted", storedEvents.contains(testEvent));
    }

    public void testReadingWriting_withDescription() {
        boolean inserted =  Model.getInstance().addEvent(testEvent);
        assertTrue("Event was added to the database", inserted);
        List<Event> storedEvents = Model.getInstance().getEvents();
        assertTrue("Database contains stored event", storedEvents.contains(testEvent));

        int idx = storedEvents.indexOf(testEvent);
        Event toCompare = storedEvents.get(idx);
        assertEquals("Event name matches", testEvent.getName(), toCompare.getName());
        assertEquals("Event location matches", testEvent.getLocation(), toCompare.getLocation());
        assertEquals("Event description matches", testEvent.getDescription(), toCompare.getDescription());

        Model.getInstance().deleteEvent(testEvent);
        storedEvents = Model.getInstance().getEvents();
        assertFalse("Event was successfully deleted", storedEvents.contains(testEvent));
    }
}