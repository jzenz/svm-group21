package at.sw2016.getgoing.test;

import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

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
        EventSerializer eventSerializer = new EventSerializer();
        EventDeserializer eventDeserializer = new EventDeserializer();
        byte[] serEvent = eventSerializer.serializeEvent(testEvt);
        Event res = eventDeserializer.deserializeEvent(new ByteArrayInputStream(serEvent));

        boolean areEqual = res.equals(testEvt);

        assertEquals(true, areEqual);
    }

}