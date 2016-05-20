package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.Date;

import at.sw2016.getgoing.EditEventActivity;
import at.sw2016.getgoing.Event;

/**
 * Created by Johannes on 10.04.2016.
 */
public class EventTest extends ActivityInstrumentationTestCase2<EditEventActivity> {
    private Solo han;

    private String evtName = "testEvtName";
    private String evtLoc = "testEvtLoc";
    private Date evtDate = new Date();

    public EventTest() {
        super(EditEventActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCtor() {
        Event testEvt = new Event(evtName, evtLoc, evtDate);
        assertEquals(testEvt.getName(), evtName);
        assertEquals(testEvt.getLocation(), evtLoc);
        assertEquals(testEvt.getDate(), evtDate);
    }

    public void testSetName() {
        String newEvtName = "some_new_name";
        Event testEvt = new Event(evtName, evtLoc, evtDate);
        testEvt.setName(newEvtName);
        assertEquals("Testing Event.setName()", testEvt.getName(), newEvtName);
    }

    public void testSetLocation() {
        String newEvtLoc = "some_new_location";
        Event testEvt = new Event(evtName, evtLoc, evtDate);
        testEvt.setLocation(newEvtLoc);
        assertEquals("Testing Event.setLocation()", testEvt.getLocation(), newEvtLoc);
    }

    public void testSetDate() {
        Date newDate = new Date();
        Event testEvt = new Event(evtName, evtLoc, evtDate);
        testEvt.setDate(newDate);
        assertEquals("Testing Event.setDate()", testEvt.getDate(), newDate);
    }
}