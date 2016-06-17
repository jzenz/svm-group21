package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import at.sw2016.getgoing.CreateEventActivity;
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
        han.sleep(1000);
        EditText nameField = (EditText) han.getCurrentActivity().findViewById(R.id.nameField);
        EditText locationField = (EditText) han.getCurrentActivity().findViewById(R.id.locationField);
        EditText dateField = (EditText) han.getCurrentActivity().findViewById(R.id.dateField);
        EditText timeField = (EditText) han.getCurrentActivity().findViewById(R.id.timeField);

        han.enterText(nameField,"napoleon");
        han.enterText(locationField,"waterloo");
        han.enterText(dateField,"20.5.2010");
        han.enterText(timeField,"18:00");

        han.clickOnView(han.getView(R.id.action_done));
        han.sleep(50);

        han.getText("Get Going!");
        han.getText("napoleon");
        han.getText("waterloo");
        han.getText("20.05.2010 18:00");

    }
/*
    public void testEditingOfEvent(){

        testCreatingOfEvents();
        han.clickOnText("napoleon");
        han.sleep(5000);

        EditText nameField = (EditText) han.getCurrentActivity().findViewById(R.id.nameField);
        EditText locationField = (EditText) han.getCurrentActivity().findViewById(R.id.locationField);
        EditText dateField = (EditText) han.getCurrentActivity().findViewById(R.id.dateField);
        EditText timeField = (EditText) han.getCurrentActivity().findViewById(R.id.timeField);

        han.clearEditText(nameField);
        han.clearEditText(locationField);
        han.clearEditText(dateField);
        han.clearEditText(timeField);
        han.enterText(nameField,"Ceasar");
        han.enterText(locationField,"Brutus");
        han.enterText(dateField,"01.01.0010");
        han.enterText(timeField,"10:00");

        han.sleep(5000);
        han.clickOnView(han.getView(R.id.action_done));
        han.sleep(10);

        han.getText("Get Going!");
        han.getText("Ceasar");
        han.getText("Brutus");
        han.getText("01.01.0010 10:00");

    }
*/


}


