package at.sw2016.getgoing.login;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import at.sw2016.getgoing.EventOverviewActivity;
import at.sw2016.getgoing.Model;
import at.sw2016.getgoing.R;

/**
 * Created by peisn_000 on 16.06.2016.
 */
public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final Button createButton = (Button) findViewById(R.id.create_button);
        if (createButton != null) {
            createButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    createAccount(v);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {

    }

    public void createAccount(View v) {
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.pw1)).getText().toString();
        String password_confirm = ((EditText) findViewById(R.id.pw2)).getText().toString();

        if (!username.isEmpty() && !password.isEmpty() && !password_confirm.isEmpty()) {
            if (password.equals(password_confirm)) {
                if (!username.contains(" ") && !password.contains(" ")) {
                    insertNewUserIntoDB(username, password);
                } else {
                    Toast.makeText(getBaseContext(), "Username and Password may not contain spaces!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getBaseContext(), "Password does not match the confirm password.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "Please insert username and password", Toast.LENGTH_LONG).show();
        }
    }

    public boolean insertNewUserIntoDB(final String username, final String password) {
        String url = "http://sw2016gr21.esy.es/createUser.php?name=" + username + "&password=" + password;


        JsonArrayRequest request = new
                JsonArrayRequest(url,
                new com.android.volley.Response.Listener<JSONArray>() {

                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("TAG", response.toString());
                        Log.d("JsonArray", response.toString());
                        String status = response.toString();
                        if (status.equals("[\"exists\"]")) {

                            Toast.makeText(getBaseContext(), "Username already exists", Toast.LENGTH_LONG).show();
                        } else if (status.equals("[\"created\"]")) {
                            creationsuccessfull(username, password);
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.getMessage());
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        });
        lunchJSONRequest(request);

        return true;
    }

    public void creationsuccessfull(String username, String password) {
        Toast.makeText(getBaseContext(), "User " + username + " created", Toast.LENGTH_LONG).show();
        Model.getInstance().setUser(username, password);
        Intent intent = new Intent(getBaseContext(), EventOverviewActivity.class);
        startActivity(intent);
    }

    public void lunchJSONRequest(JsonArrayRequest request) {
        RequestQueue mRequestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        mRequestQueue.add(request);
    }

}
