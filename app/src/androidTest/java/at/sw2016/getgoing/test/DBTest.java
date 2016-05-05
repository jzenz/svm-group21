package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.ArrayList;
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

    public DBTest() {
        super(GetGoing.class);
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
        GetGoing gg = (GetGoing)han.getCurrentActivity();
        long row = gg.getDBHelper().insertEvent(testEvent);
        assertTrue(row != -1);

        ArrayList<Event> storedEvents = new ArrayList<>(gg.getDBHelper().getAllEvents());
        assertTrue(storedEvents.contains(testEvent));
    }
}