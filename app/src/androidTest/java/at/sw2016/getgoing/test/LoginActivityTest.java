package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import at.sw2016.getgoing.login.LoginActivity;
import at.sw2016.getgoing.R;

/**
 * Created by peisner on 12.06.2016.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Solo han;

    public LoginActivityTest() {
        super(LoginActivity.class);
    }


    @Override
    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }


    public void testButton()
    {
        han.getEditText("Username");
        han.getEditText("Password");
        han.getButton("Login");
        han.getButton("Create Account");
    }

     public void testEmptyFields()
    {
        han.clickOnButton("Login");
        assertTrue(han.waitForText("Please insert a username and a password"));
    }

    public void testLoginWorked()
    {
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.username), "Test1");
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.password), "123456");
        han.clickOnButton("Login");
        assertTrue(han.waitForText("Login Successful"));
    }

    public void testLoginFailed()
    {
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.username), "saddfsdf");
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.password), "123456");
        han.clickOnButton("Login");
        assertTrue(han.waitForText("Username or Password wrong"));
    }


}
