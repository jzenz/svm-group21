package at.sw2016.getgoing;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import at.sw2016.getgoing.gui.MainListViewAdapter;
import at.sw2016.getgoing.login.LoginActivity;

public class EventOverviewActivity extends AppCompatActivity {
    private static Context context = MainApplication.getAppContext();

    public Context getContext(){
        return context;
    }

    private ListView mainlistview;
    private FloatingActionButton fab;
    private Model m = Model.getInstance();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(Model.getInstance().isLoged_in())
        {

        }
        else
        {
            getMenuInflater().inflate(R.menu.menu_event_overview, menu);
        }

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventOverviewActivity.context = getApplicationContext();
        setContentView(R.layout.activity_event_overview);


        loadData();


        mainlistview = (ListView) findViewById(R.id.mainListView);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreateEventActivity.class);
                startActivity(intent);
            }
        });



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
        return true;
    }


    public void rebuildEventList(){

        final ArrayList<Event> events = m.getEvents();
        //TODO: REMOVE THIS!
        if(events.isEmpty()) {
            Event testevent = new Event("Testevent", "Nowhere", new Date(116, 10, 20, 17, 00));
            events.add(testevent);
        }

        MainListViewAdapter arrayAdapter = new MainListViewAdapter(this , events);

        arrayAdapter.notifyDataSetChanged();
        mainlistview.setAdapter(arrayAdapter);

        mainlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Event selected_event = events.get(position);
                Intent intent = new Intent(getBaseContext(), EditEventActivity.class);
                intent.putExtra("EVENT", selected_event);
                startActivity(intent);
            }
        });

    }

    public void loadData()
    {
        RequestQueue mRequestQueue;

// Instantiate the cache

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();

            String url = "http://sw2016gr21.esy.es/getAllEvents.php";

        JsonArrayRequest jsonObjReq1 = new
                    JsonArrayRequest(url,
                new com.android.volley.Response.Listener<JSONArray>() {

                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("TAG", response.toString());

                        try {
                            m.getEvents().clear();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
                            Log.d("JsonArray",response.toString());
                            for(int i=0;i<response.length();i++){
                                JSONObject jresponse = response.getJSONObject(i);
                                Event e = new Event(jresponse.getString("name"),jresponse.getString("location"),df.parse(jresponse.getString("date")),jresponse.getString("desc"));
                                m.addEvent(e);
                            }
                            rebuildEventList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        //pDialog.dismiss();

                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                //pDialog.dismiss();

            }
        });
        mRequestQueue.add(jsonObjReq1);
    }
}
