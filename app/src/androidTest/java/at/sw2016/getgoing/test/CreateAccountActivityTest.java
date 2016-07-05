package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import at.sw2016.getgoing.R;
import at.sw2016.getgoing.login.CreateAccountActivity;
import at.sw2016.getgoing.login.LoginActivity;

/**
 * Created by peisn_000 on 16.06.2016.
 */
public class CreateAccountActivityTest extends ActivityInstrumentationTestCase2<CreateAccountActivity> {

    private Solo han;


    public CreateAccountActivityTest()
    {
        super(CreateAccountActivity.class);
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

    public void testFields()
    {
        han.getText("Username");
        han.getText("Password");
        han.getText("Confirm Password");
        han.getText("Create");
    }

    public void testNoInput()
    {
        han.clickOnButton("Create");
        assertTrue(han.waitForText("Please insert username and password"));
    }

    public void testNotMatchingPW()
    {
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.username), "USER1");
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.pw1), "123456");
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.pw2), "123");
        han.clickOnButton("Create");
        assertTrue(han.waitForText("Password does not match the confirm password."));
    }
/*
    public void testCreateUser()
    {
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.username), "Test1");
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.pw1), "123456");
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.pw2), "123456");
        han.clickOnButton("Create");
        assertTrue(han.waitForText("User Test1 created"));
    }
    */

    public void testUserExists()
    {
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.username), "Test1");
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.pw1), "123456");
        han.enterText((EditText) han.getCurrentActivity().findViewById(R.id.pw2), "123456");
        han.clickOnButton("Create");
        assertTrue(han.waitForText("Username already exists"));
    }



}
