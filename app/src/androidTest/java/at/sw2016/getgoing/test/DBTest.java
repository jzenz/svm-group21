package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.List;
import java.util.Date;

import at.sw2016.getgoing.EditEventActivity;
import at.sw2016.getgoing.Event;

/**
 * Created by Michael on 05.05.2016.
 */
public class DBTest extends ActivityInstrumentationTestCase2<EditEventActivity> {
    private Solo han;
    private Event testEvent;
    private String evtName = "testEvtName";
    private String evtLoc = "testEvtLoc";
    private Date evtDate = new Date();

    public DBTest() {
        super(EditEventActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());
        testEvent = new Event(evtName, evtLoc, evtDate);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testReadingWriting() {
        EditEventActivity gg = (EditEventActivity)han.getCurrentActivity();

        long row = gg.getDBHelper().insertEvent(testEvent);
        assertTrue(row != -1);
        List<Event> storedEvents = gg.getDBHelper().getAllEvents();
        assertTrue("Database contains stored event", storedEvents.contains(testEvent));

        gg.getDBHelper().deleteEvent(testEvent);
        storedEvents = gg.getDBHelper().getAllEvents();
        assertFalse("Event was successfully deleted", storedEvents.contains(testEvent));

    }
}