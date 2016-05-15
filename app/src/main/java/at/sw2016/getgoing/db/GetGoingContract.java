package at.sw2016.getgoing.db;

import android.provider.BaseColumns;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Michael on 05.05.2016.
 */
public final class GetGoingContract {
    public GetGoingContract(){}

    public static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat(EventEntry.DB_DATE_FORMAT, Locale.GERMAN);

    public static abstract class EventEntry implements BaseColumns{
        public static final String TABLE_NAME = "events";
        //public static final String COLUMN_NAME_ENTRY_ID = "eventid";
        public static final String COLUMN_NAME_EVENT_NAME = "name";
        public static final String COLUMN_NAME_EVENT_LOC = "location";
        public static final String COLUMN_NAME_EVENT_DATE = "date";

        public static final String DB_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    }
}
