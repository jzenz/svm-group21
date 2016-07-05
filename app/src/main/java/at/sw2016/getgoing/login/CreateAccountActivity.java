package at.sw2016.getgoing.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import at.sw2016.getgoing.EventOverviewActivity;
import at.sw2016.getgoing.Model;
import at.sw2016.getgoing.R;

/**
 * Created by peisn_000 on 16.06.2016.
 */
public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button create = (Button) findViewById(R.id.create_button);
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAccount(v);
            }
        });


    }

    @Override
    public void onClick(View v) {

    }

    public void createAccount(View v)
    {
        String username =  ((EditText) findViewById(R.id.username)).getText().toString();
        String password =  ((EditText) findViewById(R.id.pw1)).getText().toString();
        String password_confirm =  ((EditText) findViewById(R.id.pw2)).getText().toString();

        if(!username.isEmpty() && !password.isEmpty() && !password_confirm.isEmpty() )
        {

            if(password.equals(password_confirm))
            {
                if(!Model.getInstance().getUser(username))
                {
                    Model.getInstance().createUser(username, password);
                    Toast.makeText(getBaseContext(), "User " +username + " created", Toast.LENGTH_LONG).show();
                    Model.getInstance().setUser(username);
                    Intent intent = new Intent(getBaseContext(), EventOverviewActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Username already exists", Toast.LENGTH_LONG).show();
                }
            }
            else
            {

                Toast.makeText(getBaseContext(), "Password does not match the confirm password.", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(getBaseContext(), "Please insert username and password", Toast.LENGTH_LONG).show();
        }


    }
}
