package at.sw2016.getgoing.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
                        " (" + EventEntry._ID + " INTEGER PRIMARY KEY, " +
                        EventEntry.COLUMN_NAME_EVENT_NAME + " TEXT," +
                        EventEntry.COLUMN_NAME_EVENT_LOC + " TEXT," +
                        EventEntry.COLUMN_NAME_EVENT_DATE + " TEXT," +
                        EventEntry.COLUMN_NAME_EVENT_DESCRIPTION + " TEXT)";
        db.execSQL(sql);

        String user = "CREATE TABLE " + UserEntry.TABLE_NAME +
                " (" + UserEntry._ID + " INTEGER PRIMARY KEY, " +
                UserEntry.COLUMN_NAME_USER_NAME + " TEXT," +
                UserEntry.COLUMN_NAME_USER_PASSWORD + " TEXT)";
        db.execSQL(user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME;
        db.execSQL(sql);
        String sql1 = "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;
        db.execSQL(sql1);
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

    public void updateEvent(Event evt){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues newValues = new ContentValues();
            newValues.put(EventEntry.COLUMN_NAME_EVENT_NAME, evt.getName());
            newValues.put(EventEntry.COLUMN_NAME_EVENT_LOC, evt.getLocation());
            newValues.put(EventEntry.COLUMN_NAME_EVENT_DATE,  GetGoingContract.DB_DATE_FORMAT.format(evt.getDate()));
            newValues.put(EventEntry.COLUMN_NAME_EVENT_DESCRIPTION, evt.getDescription());

            db.update(EventEntry.TABLE_NAME, newValues, "_id=" + evt.getId(),null);


        }
        catch (Exception ex){

            ex.printStackTrace();
        }
    }

    public List<Event> getAllEvents(){

        ArrayList<Event> events = new ArrayList<>();
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + EventEntry.TABLE_NAME, null);

            if(c.moveToFirst()) {
                do {
                    long evtId = c.getLong(0);
                    String evtName = c.getString(1);
                    Log.i("DB", "NAME: " + evtName);
                    String evtLoc = c.getString(2);
                    String dateString = c.getString(3);
                    Date evtDate = GetGoingContract.DB_DATE_FORMAT.parse(dateString);
                    String evtDesc = c.getString(4);
                    Event evt = new Event(evtName, evtLoc, evtDate, evtDesc);
                    evt.setId(evtId);
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

    public long insertUser(String username,  String password){
        long rowId;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(UserEntry.COLUMN_NAME_USER_NAME, username);
            values.put(UserEntry.COLUMN_NAME_USER_PASSWORD,password);


            rowId = db.insert(UserEntry.TABLE_NAME, UserEntry.COLUMN_NAME_USER_NAME, values);
            return rowId;
        } catch (Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }


    public boolean checkUsername(String username){


        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + UserEntry.TABLE_NAME + " WHERE " + UserEntry.COLUMN_NAME_USER_NAME + "='" + username + "'", null);
            int counter = 0;
            if(c.moveToFirst()) {
                do {
                    counter++;
                } while (c.moveToNext());
            }
            c.close();
            if (counter > 0)
            {

                return false;
            }
            else
            {
                return true;
            }

        } catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
