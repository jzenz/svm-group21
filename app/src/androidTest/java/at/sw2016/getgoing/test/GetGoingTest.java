package at.sw2016.getgoing.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import at.sw2016.getgoing.GetGoing;

/**
 * Created by Johannes on 10.04.2016.
 */
public class GetGoingTest extends ActivityInstrumentationTestCase2<GetGoing> {

    private Solo han;

    public GetGoingTest() {
        super(GetGoing.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        han = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testYolo() {
        assertEquals(5, 5);
    }
}