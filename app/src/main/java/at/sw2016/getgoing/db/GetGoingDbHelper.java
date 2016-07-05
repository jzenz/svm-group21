package at.sw2016.getgoing.db;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import at.sw2016.getgoing.Event;
import at.sw2016.getgoing.MainApplication;
import at.sw2016.getgoing.db.GetGoingContract.*;

/**
 * Created by Michael on 05.05.2016.
 */
public class GetGoingDbHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "GetGoing.db";


    private Spinner spinner;
    private ArrayList<String> students;
    private JSONArray result;
    private TextView textViewName;
    private TextView textViewCourse;
    private TextView textViewSession;

    private ArrayList<Event> events = new ArrayList<>();

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










}
