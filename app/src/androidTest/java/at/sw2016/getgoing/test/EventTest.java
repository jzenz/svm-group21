package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.Date;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.GetGoing;

/**
 * Created by Johannes on 10.04.2016.
 */
public class EventTest extends ActivityInstrumentationTestCase2<GetGoing> {
    private Solo han;

    private String evtName = "testEvtName";
    private String evtLoc = "testEvtLoc";
    private Date evtDate = new Date();
    Event testEvt;


    public EventTest() {
        super(GetGoing.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());
        testEvt = new Event(evtName, evtLoc, evtDate);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCtor() {
        assertEquals(testEvt.getName(), evtName);
        assertEquals(testEvt.getLocation(), evtLoc);
        assertEquals(testEvt.getDate(), evtDate);
    }

    public void testSetName() {
        String newEvtName = "some_new_name";
        testEvt.setName(newEvtName);
        assertEquals("Testing Event.setName()", testEvt.getName(), newEvtName);
    }

    public void testSetLocation() {
        String newEvtLoc = "some_new_location";
        testEvt.setLocation(newEvtLoc);
        assertEquals("Testing Event.setLocation()", testEvt.getLocation(), newEvtLoc);
    }

    public void testSetDate() {
        Date newDate = new Date();
        testEvt.setDate(newDate);
        assertEquals("Testing Event.setDate()", testEvt.getDate(), newDate);
    }
}