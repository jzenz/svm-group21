package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.Date;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.GetGoing;

/**
 * Created by sschrimpf on 19.04.2016.
 */
public class GetGoingUITest extends ActivityInstrumentationTestCase2<GetGoing> {

    private Solo han;

    public GetGoingUITest() {
        super(GetGoing.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testForTitle() {
        han.getText("GetGoing");
    }

    public void testTextLabels()
    {
        han.getText("Name:");
        han.getText("Ort:");
        han.getText("Datum:");
    }

    public void testDisplayingOfEvent()
    {
        Event testevent = super.getActivity().getEvent();
        han.getText(testevent.getName());
        han.getText(testevent.getLocation());
    }


}


