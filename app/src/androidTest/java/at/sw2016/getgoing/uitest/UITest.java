package at.sw2016.getgoing.uitest;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.Date;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.GetGoing;

/**
 * Created by sschrimpf on 19.04.2016.
 */
public class UITest extends ActivityInstrumentationTestCase2<GetGoing> {

    private Solo han;

    public UITest() {
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

    public void testDisplayingOfEvents()
    {
        Event testevent = super.getActivity().getEvents().get(0);
        han.getText(testevent.getName());
        han.getText(testevent.getLocation());
    }

    public void testNextEventButton() throws InterruptedException {
        han.getText("Next Event >");

        han.clickOnButton("Next Event >");

        han.sleep(10);

        Event testevent = super.getActivity().getEvents().get(1);
        han.getText(testevent.getName());
        han.getText(testevent.getLocation());

    }

}


