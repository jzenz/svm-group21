package at.sw2016.getgoing;

import android.os.Bundle;
import android.app.Activity;
import at.sw2016.getgoing.GetGoing;

public class EventOverview extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_overview);


    }

    public GetGoing getGetgoing() {
        return getgoing;
    }

    public void setGetgoing(GetGoing getgoing) {
        this.getgoing = getgoing;
    }

}
