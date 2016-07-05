package at.sw2016.getgoing.login;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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
 * Created by peisner on 12.06.2016.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               checkLogin(v);
            }
        });

        final Button create = (Button) findViewById(R.id.create_account_button);
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreateAccountActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    public void checkLogin(View v)
    {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
        {

                checkLoginWorked(username.getText().toString(), password.getText().toString());

        }
        else
        {
            Toast.makeText(getBaseContext(), "Please insert username and password", Toast.LENGTH_LONG).show();
        }

    }


    public void checkLoginWorked (final String username, final String password) {


        RequestQueue mRequestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();

        String url = "http://sw2016gr21.esy.es/login.php?name="+username+"&password=" + password;


        JsonArrayRequest jsonObjReq1 = new
                JsonArrayRequest(url,
                new com.android.volley.Response.Listener<JSONArray>() {

                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("TAG", response.toString());


                        Log.d("JsonArray", response.toString());
                        String status =  response.toString();
                        if(status.equals("[\"false\"]")){

                            Toast.makeText(getBaseContext(), "Username or Password wrong", Toast.LENGTH_LONG).show();
                        }
                        else if(status.equals("[\"login\"]"))
                        {
                            loginsuccessfull(username);
                        }




                        //pDialog.dismiss();

                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.getMessage());
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                //pDialog.dismiss();

            }
        });
        mRequestQueue.add(jsonObjReq1);

    }

    public void loginsuccessfull(String username)
    {
        Toast.makeText(getBaseContext(), "Login Successful", Toast.LENGTH_LONG).show();

        Model.getInstance().setUser(username);
        Intent intent = new Intent(getBaseContext(), EventOverviewActivity.class);
        startActivity(intent);
    }





}

