package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.EventDeserializer;
import at.sw2016.getgoing.EventSerializer;
import at.sw2016.getgoing.GetGoing;

/**
 * Created by Johannes on 10.04.2016.
 */
public class EventSerializerDeserializerTest extends ActivityInstrumentationTestCase2<GetGoing> {
    private Solo han;

    private String evtName = "testEvtName";
    private String evtLoc = "testEvtLoc";
    private Date evtDate = new Date();
    Event testEvt;


    public EventSerializerDeserializerTest() {
        super(GetGoing.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());
        testEvt = new Event(evtName, evtLoc, evtDate);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testReadWriteEvent()
    {
        ArrayList<Event> events = new ArrayList<>();
        events.add(testEvt);
        EventSerializer eventSerializer = new EventSerializer();
        EventDeserializer eventDeserializer = new EventDeserializer();
        byte[] serEvent = eventSerializer.serializeEvents(events);
        List<Event> res = eventDeserializer.deserializeEvents(new ByteArrayInputStream(serEvent));

        assertEquals(events, res);
    }

}