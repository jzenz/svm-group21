package at.sw2016.getgoing;

import android.app.Application;
import android.content.Context;

/**
 * Created by Michael on 10.06.2016.
 */
public class MainApplication extends Application{
    private static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        MainApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MainApplication.context;
    }

}
