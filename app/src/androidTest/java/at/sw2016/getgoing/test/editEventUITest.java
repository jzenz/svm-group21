package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import java.text.SimpleDateFormat;

import at.sw2016.getgoing.EditEventActivity;
import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.R;

/**
 * Created by sschrimpf on 19.04.2016.
 */
public class editEventUITest extends ActivityInstrumentationTestCase2<EditEventActivity> {

    private Solo han;

    public editEventUITest() {
        super(EditEventActivity.class);
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
        han.getText("Zeit:");
    }

    public void testDisplayingOfEvent()
    {
        Event testevent = super.getActivity().getEvent();
        han.getText(testevent.getName());
        han.getText(testevent.getLocation());
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        han.getText(df.format(testevent.getDate()));
        SimpleDateFormat df_time = new SimpleDateFormat("HH:mm");
        han.getText(df_time.format(testevent.getDate()));
    }


}


