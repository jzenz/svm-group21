package at.sw2016.getgoing.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.db.GetGoingContract.*;

/**
 * Created by Michael on 05.05.2016.
 */
public class GetGoingDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "GetGoing.db";

    public GetGoingDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + EventEntry.TABLE_NAME +
                        " (" + EventEntry._ID + "AUTOINCREMENT INTEGER PRIMARY KEY, " +
                        EventEntry.COLUMN_NAME_EVENT_NAME + " TEXT," +
                        EventEntry.COLUMN_NAME_EVENT_LOC + " TEXT," +
                        EventEntry.COLUMN_NAME_EVENT_DATE + " TEXT," +
                        EventEntry.COLUMN_NAME_EVENT_DESCRIPTION + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public long insertEvent(Event evt){
        long rowId;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(EventEntry.COLUMN_NAME_EVENT_NAME, evt.getName());
            values.put(EventEntry.COLUMN_NAME_EVENT_DATE,
                       GetGoingContract.DB_DATE_FORMAT.format(evt.getDate()));
            values.put(EventEntry.COLUMN_NAME_EVENT_LOC, evt.getLocation());
            values.put(EventEntry.COLUMN_NAME_EVENT_DESCRIPTION, evt.getDescription());

            rowId = db.insert(EventEntry.TABLE_NAME, EventEntry.COLUMN_NAME_EVENT_NAME, values);
            return rowId;
        } catch (Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    public void deleteEvent(Event evt){
        try{
            SQLiteDatabase db = getWritableDatabase();
            String eventName = evt.getName();
            String eventLoc = evt.getLocation();
            String evtDateString = GetGoingContract.DB_DATE_FORMAT.format(evt.getDate());

            String tableName = EventEntry.TABLE_NAME;
            String whereClause = EventEntry.COLUMN_NAME_EVENT_NAME + "=?" + " and " +
                                    EventEntry.COLUMN_NAME_EVENT_LOC + "=?" + " and " +
                                    EventEntry.COLUMN_NAME_EVENT_DATE + "=?";
            String[] whereArgs = new String[]{ String.valueOf(eventName),
                                               String.valueOf(eventLoc),
                                               String.valueOf(evtDateString)};
            db.delete(tableName, whereClause, whereArgs);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public Event getEvent(int id)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();


            Cursor c = db.rawQuery("SELECT * FROM " + EventEntry.TABLE_NAME + " WHERE " +
                    EventEntry._ID + " = " + id, null);
            String evtName = c.getString(1);
            String evtLoc = c.getString(2);
            String dateString = c.getString(3);
            Date evtDate = GetGoingContract.DB_DATE_FORMAT.parse(dateString);
            String evtDesc = c.getString(4);
            int evtId = c.getInt(0);

            Event evt = new Event(evtName, evtLoc, evtDate, evtDesc, evtId);
            c.close();
            return evt;
            //return null;
        }
        catch (Exception e)
        {
            return null;
        }

    }

    public List<Event> getAllEvents(){
        ArrayList<Event> events = new ArrayList<>();
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + EventEntry.TABLE_NAME, null);

            if(c.moveToFirst()) {
                do {
                    String evtName = c.getString(1);
                    String evtLoc = c.getString(2);
                    String dateString = c.getString(3);
                    Date evtDate = GetGoingContract.DB_DATE_FORMAT.parse(dateString);
                    String evtDesc = c.getString(4);
                    int evtId = c.getInt(0);

                    Event evt = new Event(evtName, evtLoc, evtDate, evtDesc, evtId);


                    events.add(evt);
                } while (c.moveToNext());
            }
            c.close();
            return events;
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
