package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;

import java.util.ArrayList;

import at.sw2016.getgoing.CreateEventActivity;
import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.EventOverview;
import at.sw2016.getgoing.R;

/**
 * Created by sschrimpf on 19.04.2016.
 */
public class CreateEventUITest extends ActivityInstrumentationTestCase2<CreateEventActivity> {

    private Solo han;

    public CreateEventUITest() {
        super(CreateEventActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }


    public void testCreatingOfEvents()
    {
        EditText nameField = (EditText) han.getCurrentActivity().findViewById(R.id.nameField);
        EditText locationField = (EditText) han.getCurrentActivity().findViewById(R.id.locationField);

        han.enterText(nameField,"napoleon");
        han.enterText(locationField,"waterloo");

        han.clickOnView(han.getView(R.id.action_done));
        han.sleep(50);

        han.getText("Get Going!");
        han.getText("napoleon");
        han.getText("waterloo");

    }



}


