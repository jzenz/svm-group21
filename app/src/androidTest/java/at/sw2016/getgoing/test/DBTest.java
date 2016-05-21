package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.List;
import java.util.Date;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.GetGoing;

/**
 * Created by Michael on 05.05.2016.
 */
public class DBTest extends ActivityInstrumentationTestCase2<GetGoing> {
    private Solo han;
    private Event testEvent;
    private String evtName = "testEvtName";
    private String evtLoc = "testEvtLoc";
    private Date evtDate = new Date();
    private String evtDesc = "testEvtDesc";

    public DBTest() {
        super(GetGoing.class);
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
        GetGoing gg = (GetGoing)han.getCurrentActivity();

        long row = gg.getDBHelper().insertEvent(testEvent);
        assertTrue(row != -1);
        List<Event> storedEvents = gg.getDBHelper().getAllEvents();
        assertTrue("Database contains stored event", storedEvents.contains(testEvent));

        gg.getDBHelper().deleteEvent(testEvent);
        storedEvents = gg.getDBHelper().getAllEvents();
        assertFalse("Event was successfully deleted", storedEvents.contains(testEvent));
    }

    public void testReadingWriting_withDescription() {
        GetGoing gg = (GetGoing)han.getCurrentActivity();

        long row = gg.getDBHelper().insertEvent(testEvent);
        assertTrue(row != -1);
        List<Event> storedEvents = gg.getDBHelper().getAllEvents();
        assertTrue("Database contains stored event", storedEvents.contains(testEvent));

        int idx = storedEvents.indexOf(testEvent);
        Event toCompare = storedEvents.get(idx);
        assertEquals("Event name matches", testEvent.getName(), toCompare.getName());
        assertEquals("Event location matches", testEvent.getLocation(), toCompare.getLocation());
        assertEquals("Event description matches", testEvent.getDescription(), toCompare.getDescription());

        gg.getDBHelper().deleteEvent(testEvent);
        storedEvents = gg.getDBHelper().getAllEvents();
        assertFalse("Event was successfully deleted", storedEvents.contains(testEvent));
    }
}