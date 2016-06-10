package at.sw2016.getgoing.uitest;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.GetGoing;
import at.sw2016.getgoing.glogin.GetGoingLoginActivity;

public class LoginUITest extends ActivityInstrumentationTestCase2<GetGoingLoginActivity> {
    private Solo han;
    private String userName = "getgoingsw2016";
    private String password = "qwertzuiop1234";

    public LoginUITest() {
        super(GetGoingLoginActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testLogin(){
        han.clickOnButton("Sign in");
    }

}


