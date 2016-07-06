package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.EventOverviewActivity;
import at.sw2016.getgoing.Model;
import at.sw2016.getgoing.R;

/**
 * Created by sschrimpf on 19.04.2016.
 */
public class EventOverviewActivityUITest extends ActivityInstrumentationTestCase2<EventOverviewActivity> {

    private Solo han;

    public EventOverviewActivityUITest() {
        super(EventOverviewActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testForTitle() {
        han.getText("Get Going!");
    }

    public void testLoginButton() {
        if (Model.getInstance().isLoged_in()) {
            han.clickOnMenuItem("Logout");
            han.sleep(1500);
        }
        han.clickOnMenuItem("Login");
        han.getText("Sign in");

    }

    public void testLogOutButton() {
        if (!Model.getInstance().isLoged_in()) {
            han.clickOnMenuItem("Login");
            han.sleep(500);
            han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.username), "Test1");
            han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.password), "123456");
            han.clickOnButton("Login");
            han.sleep(1500);
        }
        han.clickOnMenuItem("Logout");
    }


}


