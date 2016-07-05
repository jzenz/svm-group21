package at.sw2016.getgoing.login;


import android.content.Intent;
import android.view.View;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            if (Model.getInstance().checkUserPW(username.getText().toString(), password.getText().toString()))
            {
                Toast.makeText(getBaseContext(), "Login Successful", Toast.LENGTH_LONG).show();

                Model.getInstance().setUser(username.getText().toString());
                Intent intent = new Intent(getBaseContext(), EventOverviewActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getBaseContext(), "Username or Password wrong", Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            Toast.makeText(getBaseContext(), "Please insert username and password", Toast.LENGTH_LONG).show();
        }

    }





}

